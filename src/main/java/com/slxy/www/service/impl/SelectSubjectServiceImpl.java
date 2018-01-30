package com.slxy.www.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.utils.SelectMapStructMapper;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectSubject;
import com.slxy.www.mapper.SelectSubjectMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.dto.SelectSubjectDto;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.enums.EnumSubType;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectSubjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-25
 */
@Service
public class SelectSubjectServiceImpl extends ServiceImpl<SelectSubjectMapper, SelectSubject> implements ISelectSubjectService {

    @Autowired
    private SelectSubjectMapper selectSubjectMapper;
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    /**
     * 论文列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @Override
    public ModelAndView subList(ModelAndView modelAndView, SelectSubjectVo vo) {
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getSubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        for (SelectSubjectDto dto:subjectDtos){
            dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
            SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
            dto.setSubTeaName(userBase.getUserName());
            SelectUserBase tea = selectUserBaseMapper.selectById(dto.getTeaId());
            teaSet.add(tea);
        }
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("page",page);
        modelAndView.addObject("subType",EnumSubType.toMap());
        modelAndView.addObject("teaSet",teaSet);
        return modelAndView;
    }

    /***
     * 异步返回论文页面
     * @param vo
     * @return
     */
    @Override
    public String subListAjax(SelectSubjectVo vo) {

        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getSubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        for (SelectSubjectDto dto:subjectDtos){
            dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
            SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
            dto.setSubTeaName(userBase.getUserName());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("subjectList",subjectDtos);
        map.put("page",page);
        String object = JSONObject.toJSONString(map);
        return object;
    }

    /**
     * 论文审核
     * @param vo
     * @return
     */
    @Override
    public String subAudited(SelectSubjectVo vo) {
        SelectSubject selectSubject = selectSubjectMapper.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectSubject)){
            return Constant.ERROR;
        }
        selectSubject.setAdmAuditId(vo.getAdmAuditId());
        if (selectSubject.getAdmAuditState().equals(EnumSubState.Untreated.getValue())){
            //第一次审核
            if (vo.getAdmAuditState().equals(EnumSubState.FAIL.getValue())){
                selectSubject.setAdmAuditContent(vo.getAdmAuditContent());
                selectSubject.setAdmAuditState(vo.getAdmAuditState());
            }else{
                selectSubject.setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
                selectSubject.setAdmAuditState(vo.getAdmAuditState());
            }
            return this.updateById(selectSubject)?Constant.SUCCESS:Constant.ERROR;
        }
        //第二次
        if (selectSubject.getAdmAuditState().equals(EnumSubState.FAIL.getValue())){
            selectSubject.setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
            selectSubject.setAdmAuditState(vo.getAdmAuditState());
        }
        return this.updateById(selectSubject)?Constant.SUCCESS:Constant.ERROR;
    }

    /**
     * 论文详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @Override
    public ModelAndView subDetail(ModelAndView modelAndView, SelectSubjectVo vo) {
        SelectSubject selectSubject = this.selectById(vo.getId());
        if (!ObjectUtils.isEmpty(selectSubject)){
            SelectSubjectDto selectSubjectDto = SelectMapStructMapper.INSTANCE.SelectSubjectPoToDto(selectSubject);
            //题目类型
            selectSubjectDto.setTypeName(EnumSubType.toMap().get(selectSubjectDto.getSubType()));
            //发布教师
            SelectUserBase userBase = selectUserBaseMapper.selectById(selectSubjectDto.getTeaId());
            selectSubjectDto.setSubTeaName(userBase.getUserName());
            //审核状态
            selectSubjectDto.setSubState(EnumSubState.toMap().get(selectSubjectDto.getAdmAuditState()));
            //审核人
            if (!ObjectUtils.isEmpty(selectSubjectDto.getAdmAuditId())){
                userBase = selectUserBaseMapper.selectById(selectSubjectDto.getAdmAuditId());
                selectSubjectDto.setAdmAuditName(userBase.getUserName());
            }
            modelAndView.addObject("sub",selectSubjectDto);
        }
        return modelAndView;
    }

    @Override
    public String subSuccessAll(Integer[] selectedIDs) {
        for (Integer id:selectedIDs){
            SelectSubject selectSubject = new SelectSubject().setId(id)
                    .setAdmAuditState(EnumSubState.SUCCESS.getValue())
                    .setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
            if (!this.updateById(selectSubject)){
                return Constant.ERROR;
            }
        }
        return Constant.SUCCESS;
    }

}

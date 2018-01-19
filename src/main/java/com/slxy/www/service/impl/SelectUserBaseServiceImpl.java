package com.slxy.www.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.utils.SelectMapStructMapper;
import com.slxy.www.mapper.SelectMajorMapper;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.slxy.www.model.SelectUserBaseVo;
import com.slxy.www.model.dto.SelectUserBaseDto;
import com.slxy.www.model.enums.*;
import com.slxy.www.service.ISelectUserBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
@Service
public class SelectUserBaseServiceImpl extends ServiceImpl<SelectUserBaseMapper, SelectUserBase> implements ISelectUserBaseService{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private SelectMajorMapper selectMajorMapper;

    @Override
    public ModelAndView userList(ModelAndView  modelAndView ,SelectUserBaseVo userBaseVo) {
        Page<SelectUserBaseDto> page = new Page<>(userBaseVo.getPage(),userBaseVo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,userBaseVo);
        List<SelectUserBaseDto> userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasesPo2Dto(userList);
        setUserDto(userBaseDto);
        page.setRecords(userBaseDto);
        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        List<SelectUserBase> majorList = selectUserBaseMapper.selectStuMajor();
        List<SelectUserBase> classList = selectUserBaseMapper.selectStuClass();
        modelAndView.addObject("userList", userBaseDto);
        modelAndView.addObject("page",page);
        modelAndView.addObject("yearList",yearList);
        modelAndView.addObject("majorList",majorList);
        modelAndView.addObject("classList",classList);
        return modelAndView;
    }

    /**
     * 设置用户其他属性
     * @param userBaseDto
     */
    private void setUserDto(List<SelectUserBaseDto> userBaseDto) {
        for (SelectUserBaseDto dto:userBaseDto){
            dto.setSex(EnumUserSex.toMap().get(dto.getUserSex()));
            if (!ObjectUtils.isEmpty(dto.getTeaPosition())){
                dto.setTeaPositionZ(EnumTeaPosition.toMap().get(dto.getTeaPosition()));
            }
            if (!ObjectUtils.isEmpty(dto.getTeaEducation())){
                dto.setTeaEducationZ(EnumTeaEducation.toMap().get(dto.getTeaEducation()));
            }
        }
    }

    /***
     * 异步生成学生列表
     * @param userBaseVo
     * @return
     */
    @Override
    public String stuListAjax(SelectUserBaseVo userBaseVo) {
        Page<SelectUserBaseDto> page = new Page<>(userBaseVo.getPage(),userBaseVo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,userBaseVo);
        List<SelectUserBaseDto> userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasesPo2Dto(userList);
        setUserDto(userBaseDto);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("stuList",userBaseDto);
        String object = JSONObject.toJSONString(map);
        return object;
    }

    /**
     * 学生启用禁用
     * 存在选题记录不可禁用
     * 专业禁用不可启用
     * @param userBaseVo
     * @return
     */
    @Override
    public String stuAble(SelectUserBaseVo userBaseVo) {
        SelectUserBase userBase = this.selectById(userBaseVo.getId());
        SelectMajor selectMajor = selectMajorMapper.selectOne(new SelectMajor().setMajName(userBase.getStuMajorName()));
        //判断所属专业
        if (ObjectUtils.isEmpty(selectMajor)||selectMajor.getMajStatus().equals(EnumEnOrDis.DISABLED.getValue())){
            logger.info(Constant.STU_ABLE_ERROR_MAJ_DISABLE);
            return Constant.STU_ABLE_ERROR_MAJ_DISABLE;
        }
        //todo 判断选题记录是否完成

        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBaseVo.getId())
                .setUserStatus(userBaseVo.getUserStatus());
        return this.updateById(selectUserBase) ? Constant.SUCCESS : Constant.ERROR;
    }

    /**
     * 删除学生记录
     * 存在选题记录不可删除
     * @param userBaseVo
     * @return
     */
    @Override
    public String stuDelete(SelectUserBaseVo userBaseVo) {
        //todo 存在未完成选题记录不可删除
        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBaseVo.getId())
                .setUserStatus(userBaseVo.getUserStatus());
        return this.deleteById(selectUserBase) ? Constant.SUCCESS : Constant.ERROR;
    }

    /***
     * 批量删除学生
     * @param selectedIDs
     * @return
     */
    @Override
    public String stuDeleteAll(Integer[] selectedIDs) {
        //todo 存在未完成的选题记录不可删除
        for (Integer id: selectedIDs){
            SelectUserBase selectUserBase = new SelectUserBase().setId(id);

        }
        return this.deleteBatchIds(Arrays.asList(selectedIDs))?Constant.SUCCESS:Constant.ERROR;
    }

    /**
     * 初始化编辑页面
     * 返回学生信息
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    @Override
    public ModelAndView stuInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo) {
        SelectUserBase userBase = this.selectById(userBaseVo.getId());
        if (!ObjectUtils.isEmpty(userBase)){
            modelAndView.addObject("user",userBase);
        }
        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        List<SelectUserBase> majorList = selectUserBaseMapper.selectStuMajor();
        List<SelectUserBase> classList = selectUserBaseMapper.selectStuClass();
        modelAndView.addObject("yearList",yearList);
        modelAndView.addObject("majorList",majorList);
        modelAndView.addObject("classList",classList);
        return modelAndView;
    }

    /**
     * 学生修改
     * @param userBase
     * @return
     */
    @Override
    public String stuUpdate(SelectUserBase userBase) {
        //校验重复
        String msg = checkCodeAndName(userBase);
        if (StringUtils.isEmpty(msg)) return msg;
        return this.updateById(userBase)?Constant.SUCCESS:Constant.ERROR;
    }


    /**
     * 学生添加
     * @param user
     * @return
     */
    @Override
    public String stuAdd(SelectUserBase user) {
        if (ObjectUtils.isEmpty(user)){
            return  Constant.ERROR;
        }
        String msg = checkCodeAndName(user);
        if (StringUtils.isEmpty(msg)) return msg;
        user.setGmtCreate(new Date())
                .setUserType(EnumUserType.STUDENT.getValue())
                .setUserPassword(Constant.USER_PASSWORD);
        return this.insert(user)?Constant.SUCCESS:Constant.ERROR;
    }



    /**
     * 校验账号 用户名是否重复
     * @param user
     * @return
     */
    private String checkCodeAndName(SelectUserBase user) {
        //校验重复性 code 和 name
        SelectUserBase userBase = new SelectUserBase().setUserCode(user.getUserCode());
        userBase = selectUserBaseMapper.selectOne(userBase);
        if (!ObjectUtils.isEmpty(userBase)){
            //账号重复
            logger.info("用户账号重复");
            return Constant.STU_ADD_ERROR_CODE_EXIST;
        }
        userBase = new SelectUserBase().setUserName(user.getUserName());
        userBase = selectUserBaseMapper.selectOne(userBase);
        if (!ObjectUtils.isEmpty(userBase)){
            logger.info("姓名重复");
            return  Constant.STU_ADD_ERROR_NAME_EXIST;
        }
        return null;
    }
}

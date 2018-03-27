package com.slxy.www.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.utils.SelectMapStructMapper;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.mapper.SelectMajorMapper;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.*;
import com.slxy.www.mapper.SelectSubjectMapper;
import com.slxy.www.model.dto.SelectSubjectDto;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.enums.EnumSubSelectStatus;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.enums.EnumSubType;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.service.ISelectSubjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.slxy.www.service.ISelectTopicService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SelectSubjectMapper selectSubjectMapper;
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private SelectDepartmentMapper selectDepartmentMapper;
    @Autowired
    private SelectMajorMapper selectMajorMapper;
    @Autowired
    private ISelectTopicService selectTopicService;



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
        this.setSubDto(subjectDtos, teaSet);
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("depList",depList);
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
            //面向系别
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(dto.getForDepId());
            dto.setForDepName(selectDepartment.getDepName());
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
            //选题状态
            selectSubjectDto.setSubSelectStatusName(EnumSubSelectStatus.toMap().get(selectSubjectDto.getSubSelectStatus()));
            modelAndView.addObject("sub",selectSubjectDto);
        }
        return modelAndView;
    }

    /**
     * 批量通过
     * @param selectedIDs
     * @return
     */
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


    /**
     * 教师：我的题目
     * @param modelAndView
     * @param vo
     * @return
     */
    @Override
    public ModelAndView mySubList(ModelAndView modelAndView, SelectSubjectVo vo) {
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getMySubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        setSubDto(subjectDtos, teaSet);
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("depList",depList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("subType",EnumSubType.toMap());
        modelAndView.addObject("teaSet",teaSet);
        return modelAndView;
    }


    /**
     * 设置dto属性
     * @param subjectDtos
     * @param teaSet
     */
    private void setSubDto(List<SelectSubjectDto> subjectDtos, Set<SelectUserBase> teaSet) {
        for (SelectSubjectDto dto:subjectDtos){
            //类型名
            dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
            //教师名
            SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
            dto.setSubTeaName(userBase.getUserName());
            SelectUserBase tea = selectUserBaseMapper.selectById(dto.getTeaId());
            teaSet.add(tea);
            //面向系别
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(dto.getForDepId());
            dto.setForDepName(selectDepartment.getDepName());
            //选题状态
            dto.setSubSelectStatusName(EnumSubSelectStatus.toMap().get(dto.getSubSelectStatus()));
        }
    }

    @Override
    public String mySubListAjax(SelectSubjectVo vo) {
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getMySubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        setSubDto(subjectDtos, teaSet);
        Map<String,Object> map = new HashMap<>();
        map.put("subjectList",subjectDtos);
        map.put("page",page);
        String object = JSONObject.toJSONString(map);
        return object;
    }

    /***
     * 论文添加初始化
     * @param modelAndView
     * @return
     */
    @Override
    public ModelAndView initSubAdd(ModelAndView modelAndView) {
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        if (!CollectionUtils.isEmpty(depList)){
            modelAndView.addObject("depList",depList);
        }
        modelAndView.addObject("subType",EnumSubType.toMap());
        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        modelAndView.addObject("yearList",yearList);
        return modelAndView;
    }

    /***
     * 添加题目
     * @param vo
     * @return
     */
    @Override
    public String subAdd(MultipartFile file,SelectSubjectVo vo, HttpServletRequest request) {
        //没有文件也可以添加
        if (StringUtils.isEmpty(vo.getSubName())){
            return Constant.PARAM_ERROR;
        }
        //校验重复性
        SelectSubject selectSubject = this.selectOne(new EntityWrapper<SelectSubject>().and("sub_name={0}",vo.getSubName()));
        if (!ObjectUtils.isEmpty(selectSubject)){
            return Constant.SUB_ADD_NAME_EXIST;
        }
        SelectSubject subject = SelectMapStructMapper.INSTANCE.SelectSubjectVoToPo(vo);
        subject.setGmtCreate(new Date());
        //保存文件
        if (!ObjectUtils.isEmpty(file)){
//            String fileDir =request.getServletContext().getRealPath("");
//            String demoDir = "downFile";
            String fileDir = "C:/Users/Administrator/Desktop/online/";
            String demoDir = "demo";
            String demoPath = demoDir + File.separator;
            String fileName = file.getOriginalFilename();
            File outFile = new File(fileDir + demoPath);
            //保存路径字段
            subject.setSubFile(demoDir+"/"+fileName);

            if (!outFile.exists()) {
                outFile.mkdirs();
            }
            try{
                InputStream in = file.getInputStream();
                OutputStream ot = new FileOutputStream(fileDir + demoPath + fileName);
                byte[] buffer = new byte[1024];
                int len;
                while ((-1 != (len = in.read(buffer)))) {
                    ot.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return this.insert(subject)?Constant.SUCCESS:Constant.ERROR;
    }


    /***
     * 学生可见题目列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @Override
    public ModelAndView stuSubList(ModelAndView modelAndView, SelectSubjectVo vo) {
        SelectUserBase userBase = selectUserBaseMapper.selectById(vo.getSelectId());
        if (ObjectUtils.isEmpty(userBase)){
            logger.info(Constant.PARAM_ERROR);
            return modelAndView;
        }
        //系别
        SelectMajor major = selectMajorMapper.selectById(userBase.getStuMajorId());
        if (ObjectUtils.isEmpty(major)){
            logger.info(Constant.PARAM_ERROR);
            return modelAndView;
        }
        vo.setForDepId(major.getDepId());
        //届别
        vo.setSubYear(userBase.getStuYear());
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        vo.setSubSelectStatus(EnumSubSelectStatus.Untreated.getValue());
       return this.mySubList(modelAndView,vo);

    }

    @Override
    public String stuSubListAjax(SelectSubjectVo vo) {
        SelectUserBase userBase = selectUserBaseMapper.selectById(vo.getSelectId());
        if (ObjectUtils.isEmpty(userBase)){
            logger.info(Constant.PARAM_ERROR);
            return Constant.PARAM_ERROR;
        }
        //系别
        SelectMajor major = selectMajorMapper.selectById(userBase.getStuMajorId());
        if (ObjectUtils.isEmpty(major)){
            logger.info(Constant.PARAM_ERROR);
            return Constant.PARAM_ERROR;
        }
        vo.setForDepId(major.getDepId());
        //届别
        vo.setSubYear(userBase.getStuYear());
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        vo.setSubSelectStatus(EnumSubSelectStatus.Untreated.getValue());
        return this.mySubListAjax(vo);
    }

    /**
     * 选题
     * @param vo
     * @return
     */
    @Override
    @Transactional
    public String stuSelect(SelectSubjectVo vo) {

        //1、添加选题记录
        if (ObjectUtils.isEmpty(vo)||StringUtils.isEmpty(vo.getSelectReason())){
            return Constant.PARAM_ERROR;
        }
        SelectSubject selectSubject = selectSubjectMapper.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectSubject)){
            return Constant.NULL_ERROR;
        }
        if (!selectSubject.getSubSelectStatus().equals(EnumSubSelectStatus.Untreated.getValue())){
            logger.info("该题目已经被选，请重新选择");
            return Constant.SELECT_ERROR_SELECTED;
        }

        //先判断是否已经选过题目
        SelectTopic selectTopic = new SelectTopic()
                .setStuId(vo.getSelectId());
        selectTopic = selectTopicService.selectOne(new EntityWrapper<SelectTopic>(selectTopic));
        if (!ObjectUtils.isEmpty(selectTopic)){
            logger.info("您已选过题目，请勿重复选择！");
            return Constant.SELECT_ERROR_REPEAT;
        }
        SelectTopic topic = new SelectTopic()
                .setSubId(selectSubject.getId())
                .setTeaId(selectSubject.getTeaId())
                .setStuId(vo.getSelectId())
                .setTopicYear(selectSubject.getSubYear())
                .setStuSelectReason(vo.getSelectReason())
                .setGmtCreate(new Date());
        selectTopicService.insert(topic);

        //2、修改选题状态
        SelectSubject subject = new SelectSubject()
                .setId(selectSubject.getId())
                .setSubSelectStatus(EnumSubSelectStatus.FAIL.getValue());
        this.updateById(subject);

        return Constant.SUCCESS;
    }

    @Override
    public void downSubFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        OutputStream outputStream = null;
        InputStream inputStream=null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream;charset=UTF-8");// 设置文件输出类型
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));//设置下载的文件名
//            String baseAbsoluteFilePath=request.getServletContext().getRealPath("");//获取的是项目在磁盘中的绝对路径，最后包括"\"
//
//            String fileRelativePath="WEB-INF/file/"+fileName;//文件相对于webRoot的路径

//            inputStream=new FileInputStream(baseAbsoluteFilePath+fileRelativePath);


            String fileRelativePath="C:/Users/Administrator/Desktop/online/"+fileName;//文件相对于webRoot的路径
            inputStream=new FileInputStream(fileRelativePath);
            byte[] buff=new byte[1024];
            Integer readLength=0;
            while((readLength=inputStream.read(buff,0,buff.length))>0){
                outputStream.write(buff, 0, readLength);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

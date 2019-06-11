package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.ExcelUtils;
import com.slxy.www.common.SelectMapStructMapper;
import com.slxy.www.dao.*;
import com.slxy.www.domain.dto.SelectSubjectDto;
import com.slxy.www.domain.po.*;
import com.slxy.www.domain.vo.SelectSubjectVo;
import com.slxy.www.domain.vo.SelectTopicVo;
import com.slxy.www.enums.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * SelectSubject 表数据服务层接口实现类
 *
 */
@Service
public class SelectSubjectService extends  ServiceImpl <ISelectSubjectMapper, SelectSubject>  {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private ISelectSubjectMapper selectSubjectMapper;
    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private ISelectDepartmentMapper selectDepartmentMapper;
    @Autowired
    private ISelectMajorMapper selectMajorMapper;
    @Autowired
    private SelectTopicService selectTopicService;
    @Autowired
    private ISelectProcessControlMapper selectProcessControlMapper;
    @Autowired
    private SelectProcessControlService selectProcessControlService;
    @Autowired
    private SelectJavaMailService selectJavaMailService;



    /**
     * 论文列表
     * @param modelAndView
     * @param vo
     * @return
     */

    public ModelAndView subList(ModelAndView modelAndView, SelectSubjectVo vo) {
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<>(new SelectDepartment().setDepStatus(EnumEnOrDis.ENABLED.getValue()).setId(vo.getForDepId())));
        if (!ObjectUtils.isEmpty(vo.getSelectId())){
            //根据专业找系别
            SelectUserBase userBase = selectUserBaseMapper.selectById(vo.getSelectId());
            SelectMajor major = selectMajorMapper.selectById(userBase.getStuMajorId());
            vo.setForDepId(major.getDepId());
            depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>()
                    .and("id = ?", major.getDepId()));
        }
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getSubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        this.setSubDto(subjectDtos, teaSet);
        List<SelectUserBase> teas = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>().and("user_type=?",EnumUserType.TEACHER.getValue()));
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("depList",depList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("subType", EnumSubType.toMap());
        modelAndView.addObject("teaSet",teas);
        return modelAndView;
    }

    /***
     * 异步返回论文页面
     * @param vo
     * @return
     */

    public String subListAjax(SelectSubjectVo vo) {
        if (!ObjectUtils.isEmpty(vo.getSelectId())){
            //根据专业找系别
            SelectUserBase userBase = selectUserBaseMapper.selectById(vo.getSelectId());

            if (!ObjectUtils.isEmpty(userBase)&&userBase.getUserType().equals(EnumUserType.TEACHER.getValue())){
                //要查的系别是不是自己所属系别
                if (!ObjectUtils.isEmpty(userBase.getTeaDepId())&&!ObjectUtils.isEmpty(vo.getForDepId())&&
                        !vo.getForDepId().equals(userBase.getTeaDepId())){
                    Map<String,Object> map = new HashMap<>();
                    map.put("subjectList",null);
                    return JSONObject.toJSONString(map);
                }
                vo.setForDepId(userBase.getTeaDepId());
            }else if (!ObjectUtils.isEmpty(userBase)&&userBase.getUserType().equals(EnumUserType.STUDENT.getValue())){
                SelectMajor major = selectMajorMapper.selectById(userBase.getStuMajorId());
                //要查的系别是不是自己所属系别
                if (!ObjectUtils.isEmpty(vo.getForDepId())&&!vo.getForDepId().equals(major.getDepId())){
                    Map<String,Object> map = new HashMap<>();
                    map.put("subjectList",null);
                    return JSONObject.toJSONString(map);
                }
                vo.setForDepId(major.getDepId());
            }

        }
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getSubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        for (SelectSubjectDto dto:subjectDtos){
            dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
            SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
            //教师名、电话
            dto.setSubTeaName(userBase.getUserName()).setTeaPhone(userBase.getUserPhone());
            //面向系别
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(dto.getForDepId());
            dto.setForDepName(selectDepartment.getDepName());
            dto.setSubSelectStatusName(EnumSubSelectStatus.toMap().get(dto.getSubSelectStatus()));
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

    public String subAudited(SelectSubjectVo vo) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.auditSub.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        SelectSubject selectSubject = selectSubjectMapper.selectById(vo.getId());
        SelectUserBase userBase = selectUserBaseMapper.selectById(selectSubject.getTeaId());
        if (ObjectUtils.isEmpty(selectSubject) || ObjectUtils.isEmpty(userBase)){
            return JSONObject.toJSONString(Constant.ERROR);
        }
        selectSubject.setAdmAuditId(vo.getAdmAuditId());
        if (selectSubject.getAdmAuditState().equals(EnumSubState.Untreated.getValue())){
            //第一次审核
            if (vo.getAdmAuditState().equals(EnumSubState.FAIL.getValue())){
                selectSubject.setAdmAuditContent(vo.getAdmAuditContent());
                selectSubject.setAdmAuditState(vo.getAdmAuditState());
                selectJavaMailService.sendHtmlMail(userBase.getUserMail(),userBase.getUserName(),selectSubject.getSubName(),"题目审核失败");
            }else{
                selectSubject.setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
                selectSubject.setAdmAuditState(vo.getAdmAuditState());
            }
            return this.updateById(selectSubject)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
        }
        //第二次
        if (selectSubject.getAdmAuditState().equals(EnumSubState.FAIL.getValue())&&vo.getAdmAuditState().equals(EnumSubState.SUCCESS.getValue())){
            selectSubject.setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
            selectSubject.setAdmAuditState(vo.getAdmAuditState());
        }

        return this.updateById(selectSubject)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 论文详情
     * @param modelAndView
     * @param vo
     * @return
     */

    public ModelAndView subDetail(ModelAndView modelAndView, SelectSubjectVo vo) {
        SelectSubject selectSubject = this.selectById(vo.getId());
        if (!ObjectUtils.isEmpty(selectSubject)){
            SelectSubjectDto selectSubjectDto = SelectMapStructMapper.INSTANCE.SelectSubjectPoToDto(selectSubject);
            //题目类型
            selectSubjectDto.setTypeName(EnumSubType.toMap().get(selectSubjectDto.getSubType()));
            //发布教师
            SelectUserBase userBase = selectUserBaseMapper.selectById(selectSubjectDto.getTeaId());
            selectSubjectDto.setSubTeaName(userBase.getUserName());
            //面向系别名称
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(selectSubjectDto.getForDepId());
            selectSubjectDto.setForDepName(selectDepartment.getDepName())
                    .setFinalTotalScore(new BigDecimal(selectSubjectDto.getFinalTotalScore().toString()).setScale(2, RoundingMode.UP).doubleValue());
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
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = ?", EnumEnOrDis.ENABLED.getValue()));
        if (!CollectionUtils.isEmpty(depList)){
            modelAndView.addObject("depList",depList);
        }
        modelAndView.addObject("subType",EnumSubType.toMap());
        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        modelAndView.addObject("yearList",yearList);
        return modelAndView;
    }

    /**
     * 批量通过
     * @param selectedIDs
     * @return
     */

    public String subSuccessAll(Integer[] selectedIDs) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.auditSub.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        for (Integer id:selectedIDs){
            SelectSubject selectSubject = new SelectSubject().setId(id)
                    .setAdmAuditState(EnumSubState.SUCCESS.getValue())
                    .setAdmAuditContent(Constant.AUDIT_SUCCESS_REASON);
            if (!this.updateById(selectSubject)){
                return JSONObject.toJSONString(Constant.ERROR);
            }
        }
        return JSONObject.toJSONString(Constant.SUCCESS);
    }


    /**
     * 教师：我的题目
     * @param modelAndView
     * @param vo
     * @return
     */

    public ModelAndView mySubList(ModelAndView modelAndView, SelectSubjectVo vo) {
        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.getMySubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        setSubDto(subjectDtos, teaSet);
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = ?", EnumEnOrDis.ENABLED.getValue()));
        List<SelectUserBase> teas = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>().and("user_type=?",EnumUserType.TEACHER.getValue()));
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("depList",depList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("subType",EnumSubType.toMap());
        modelAndView.addObject("teaSet",teas);
        return modelAndView;
    }


    /**
     * 设置dto属性
     * @param subjectDtos
     * @param teaSet
     */
    private void setSubDto(List<SelectSubjectDto> subjectDtos, Set<SelectUserBase> teaSet) {
        Set<String> codes = new HashSet<>();
        for (SelectSubjectDto dto:subjectDtos){
            //类型名
            dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
            //教师名、电话
            SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
            dto.setSubTeaName(userBase.getUserName()).setTeaPhone(userBase.getUserPhone());
            SelectUserBase tea = selectUserBaseMapper.selectById(dto.getTeaId());


            if (!codes.contains(tea.getUserCode())&&teaSet!=null){
                teaSet.add(tea);
            }

            codes.add(tea.getUserCode());


            //面向系别
            SelectDepartment selectDepartment = selectDepartmentMapper.selectById(dto.getForDepId());
            dto.setForDepName(selectDepartment.getDepName());
            //选题状态
            dto.setSubSelectStatusName(EnumSubSelectStatus.toMap().get(dto.getSubSelectStatus()));

        }
    }


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

    public ModelAndView initSubAdd(ModelAndView modelAndView) {
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>().and("dep_status = ?", EnumEnOrDis.ENABLED.getValue()));
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

    public String subAdd(MultipartFile file, SelectSubjectVo vo, HttpServletRequest request) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.subAdd.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        //没有文件也可以添加
        if (StringUtils.isEmpty(vo.getSubName())){
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        //校验重复性
        SelectSubject selectSubject = this.selectOne(new EntityWrapper<SelectSubject>().and("sub_name=?",vo.getSubName()));
        if (!ObjectUtils.isEmpty(selectSubject)){
            return JSONObject.toJSONString(Constant.SUB_ADD_NAME_EXIST);
        }
        SelectSubject subject = SelectMapStructMapper.INSTANCE.SelectSubjectVoToPo(vo);
        subject.setGmtCreate(new Date());
        //保存文件
        if (!ObjectUtils.isEmpty(file)&&!ObjectUtils.isEmpty(file.getOriginalFilename())){
//            String fileDir =request.getServletContext().getRealPath("");
//            String demoDir = "downFile";
            String fileDir = Constant.FILE_DIR;
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


        return this.insert(subject)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }


    /***
     * 学生可见题目列表
     * @param modelAndView
     * @param vo
     * @return
     */

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


    public String stuSubListAjax(SelectSubjectVo vo) {
        SelectUserBase userBase = selectUserBaseMapper.selectById(vo.getSelectId());
        if (ObjectUtils.isEmpty(userBase)){
            logger.info(Constant.PARAM_ERROR);
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        //系别
        SelectMajor major = selectMajorMapper.selectById(userBase.getStuMajorId());
        if (ObjectUtils.isEmpty(major)){
            logger.info(Constant.PARAM_ERROR);
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
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

    @Transactional
    public String stuSelect(SelectSubjectVo vo) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.select.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        //1、添加选题记录
        if (ObjectUtils.isEmpty(vo)||StringUtils.isEmpty(vo.getSelectReason())){
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        SelectSubject selectSubject = selectSubjectMapper.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectSubject)){
            return JSONObject.toJSONString(Constant.NULL_ERROR);
        }
        if (!selectSubject.getSubSelectStatus().equals(EnumSubSelectStatus.Untreated.getValue())){
            logger.info("该题目已经被选，请重新选择");
            return JSONObject.toJSONString(Constant.SELECT_ERROR_SELECTED);
        }
        //获取教师信息
        SelectUserBase teaInfo = selectUserBaseMapper.selectById(selectSubject.getTeaId());
        //先判断是否已经选过题目
        SelectTopic selectTopic = new SelectTopic()
                .setStuId(vo.getSelectId())
                .setDelState(EnumEnOrDis.DISABLED.getValue());
        selectTopic = selectTopicService.selectOne(new EntityWrapper<SelectTopic>(selectTopic));
        if (!ObjectUtils.isEmpty(selectTopic)){
            if(selectTopic.getTeaAuditState().equals(EnumSubState.FAIL.getValue())){
                logger.info("您存在未过审核的选题记录，请删除后重新选题！");
                return JSONObject.toJSONString(Constant.SELECT_AGAIN);
            }
            logger.info("您已选过题目，请勿重复选择！");
            return JSONObject.toJSONString(Constant.SELECT_ERROR_REPEAT);
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
        //3、邮件通知教师
        selectJavaMailService.sendHtmlMail(teaInfo.getUserMail(),teaInfo.getUserName(),selectSubject.getSubName(),"通知教师审核");
        return JSONObject.toJSONString(Constant.SUCCESS);
    }


    public void downSubFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        OutputStream outputStream = null;
        InputStream inputStream=null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream;charset=UTF-8");// 设置文件输出类型
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.substring(5).getBytes("utf-8"), "ISO8859-1"));//设置下载的文件名
//            String baseAbsoluteFilePath=request.getServletContext().getRealPath("");//获取的是项目在磁盘中的绝对路径，最后包括"\"
//
//            String fileRelativePath="WEB-INF/file/"+fileName;//文件相对于webRoot的路径

//            inputStream=new FileInputStream(baseAbsoluteFilePath+fileRelativePath);


            String fileRelativePath=Constant.FILE_DIR+fileName;//文件相对于webRoot的路径
            inputStream=new FileInputStream(fileRelativePath);
            byte[] buff=new byte[1024];
            Integer readLength=0;
            while((readLength=inputStream.read(buff,0,buff.length))>0){
                outputStream.write(buff, 0, readLength);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            //
            e.printStackTrace();
        }
    }


    public XSSFWorkbook exportExcelInfo(SelectSubjectVo vo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
//        vo.setSubSelectStatus(3);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(selectSubjectMapper.countSubByPage(vo));
        subjectDtos.stream()
                .map(subjectDto ->
                        subjectDto.setTypeName(EnumSubType.toMap().get(subjectDto.getSubType()))
                                .setAdmAuditName(EnumSubState.toMap().get(subjectDto.getAdmAuditState()))
                ).collect(Collectors.toList());
        setSubDto(subjectDtos,null);
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("题目名称","subName",0));
        excel.add(new ExcelBean("发布教师","subTeaName",0));
        excel.add(new ExcelBean("教师电话","teaPhone",0));
        excel.add(new ExcelBean("题目类型","typeName",0));
        excel.add(new ExcelBean("题目届别(级)","subYear",0));
        excel.add(new ExcelBean("审核状态","admAuditName",0));
        excel.add(new ExcelBean("面向系别","forDepName",0));
        excel.add(new ExcelBean("创建时间","gmtCreate",0));
        map.put(0, excel);
        String sheetName = "题目列表";
        //调用ExcelUtils的方法
        xssfWorkbook = ExcelUtils.createExcelFile(SelectSubjectDto.class, subjectDtos, map, sheetName);
        return xssfWorkbook;
    }


    public String checkSubName(SelectSubject selectSubject) {
        List<SelectSubject> subjects = selectSubjectMapper.selectList(new EntityWrapper<>(new SelectSubject().setSubName(selectSubject.getSubName())));

        Map<String,Boolean> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(subjects)){
            map.put("valid",false);
        }else {
            map.put("valid",true);
        }

        return JSONObject.toJSONString(map);
    }

    /***
     * 删除题目
     * @param vo
     * @return
     */
    @Transactional
    public String delSub(SelectSubjectVo vo) {

        //检测题目状态，是否处在 已被选 状态
        SelectSubject selectSubject = this.selectById(vo.getId());
        if (EnumSubSelectStatus.SUCCESS.getValue().equals(selectSubject.getSubSelectStatus())){
            return JSONObject.toJSONString(Constant.SUB_DEL_ERROR);
        }

        SelectTopic selectTopic = selectTopicService.selectOne(new EntityWrapper<>(new SelectTopic().setSubId(selectSubject.getId())));
        if (!ObjectUtils.isEmpty(selectTopic)){
            //存在选题记录----删除一切
            deleteFile(Constant.FILE_DIR+selectTopic.getTaskFile());
            deleteFile(Constant.FILE_DIR+selectTopic.getOpeningReport());
            deleteFile(Constant.FILE_DIR+selectTopic.getDissertation());
            selectTopicService.deleteById(selectTopic);
        }

        //删除题目的信息、选题信息、文件
        deleteFile(Constant.FILE_DIR+selectSubject.getSubFile());
        return this.deleteById(selectSubject) ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 删除文件
     * @param sPath 路径+文件名
     * @return
     */
    public  boolean deleteFile(String sPath) {
        Boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }


    /***
     * 管理员修改题目
     * @param vo
     * @return
     */
    public String subUpdate(MultipartFile file, SelectSubjectVo vo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                if (userBase.getUserType()>1){
                    //流程检测
                    String msg = selectProcessControlService.testPc(EnumProControl.subAdd.getValue());
                    if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
                        return msg;
                    }
                }
            }
        }
        SelectSubject selectSubject = selectSubjectMapper.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectSubject)){
            return JSONObject.toJSONString(Constant.ERROR);
        }

        selectSubject = SelectMapStructMapper.INSTANCE.SelectSubjectVoToPo(vo);
        //保存文件
        if (!ObjectUtils.isEmpty(file)&&!ObjectUtils.isEmpty(file.getOriginalFilename())){

            String fileDir = Constant.FILE_DIR;
            String demoDir = "demo";
            String demoPath = demoDir + File.separator;
            String fileName = file.getOriginalFilename();
            File outFile = new File(fileDir + demoPath);
            //保存路径字段
            selectSubject.setSubFile(demoDir+"/"+fileName);

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
        return this.updateById(selectSubject) ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 自动结题：所有被选的题目自动结题
     */
    public void autoEnd() {
        selectSubjectMapper.autoUpdateStatus();
    }

    public String updateSubFile(MultipartFile file, int i, HttpServletRequest request) {
        SelectSubject selectSubject = selectSubjectMapper.selectById(i);
        deleteFile(Constant.FILE_DIR+selectSubject.getSubFile());
        SelectSubjectVo vo = SelectMapStructMapper.INSTANCE.SelectSubjectPoToVo(selectSubject);
        return subUpdate(file,vo,request);
    }

    public ModelAndView countSubList(ModelAndView modelAndView, SelectSubjectVo vo) {
        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<>(new SelectDepartment().setDepStatus(EnumEnOrDis.ENABLED.getValue()).setId(vo.getForDepId())));

        Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
        List<SelectSubject> subjectList = selectSubjectMapper.countSubByPage(vo,page);
        List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
        Set<SelectUserBase> teaSet = new HashSet<>();
        this.setSubDto(subjectDtos, teaSet);
        List<SelectUserBase> teas = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>().and("user_type=?",EnumUserType.TEACHER.getValue()));
        modelAndView.addObject("subjectList",subjectDtos);
        modelAndView.addObject("depList",depList);
        modelAndView.addObject("page",page);
        modelAndView.addObject("subType", EnumSubType.toMap());
        modelAndView.addObject("teaSet",teas);
        return modelAndView;
    }


    public String subCountListAjax(SelectSubjectVo vo) {
            Page<SelectSubject> page = new Page<>(vo.getPage(),vo.getPageSize());
            List<SelectSubject> subjectList = selectSubjectMapper.countSubByPage(vo,page);
            List<SelectSubjectDto> subjectDtos = SelectMapStructMapper.INSTANCE.SelectSubjectsPoToDto(subjectList);
            for (SelectSubjectDto dto:subjectDtos){
                dto.setTypeName(EnumSubType.toMap().get(dto.getSubType()));
                SelectUserBase userBase = selectUserBaseMapper.selectById(dto.getTeaId());
                //教师名、电话
                dto.setSubTeaName(userBase.getUserName()).setTeaPhone(userBase.getUserPhone());
                //面向系别
                SelectDepartment selectDepartment = selectDepartmentMapper.selectById(dto.getForDepId());
                dto.setForDepName(selectDepartment.getDepName());
                if (dto.getSubSelectStatus().equals(EnumSubSelectStatus.OVER.getValue())){
                    dto.setSubSelectStatusName("已结题");
                }else {
                    dto.setSubSelectStatusName("未结题");
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("subjectList",subjectDtos);
            map.put("page",page);
            String object = JSONObject.toJSONString(map);
            return object;

    }
}

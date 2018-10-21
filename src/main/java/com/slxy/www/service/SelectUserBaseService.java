package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.ExcelUtil;
import com.slxy.www.common.SelectMapStructMapper;
import com.slxy.www.dao.*;
import com.slxy.www.domain.dto.SelectUserBaseDto;
import com.slxy.www.domain.po.*;
import com.slxy.www.domain.vo.ImportStuVo;
import com.slxy.www.domain.vo.ImportTeaVo;
import com.slxy.www.domain.vo.SelectUserBaseVo;
import com.slxy.www.enums.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * SelectUserBase 表数据服务层接口实现类
 *
 */
@Service
public class SelectUserBaseService extends  ServiceImpl <ISelectUserBaseMapper, SelectUserBase>  {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private ISelectMajorMapper selectMajorMapper;
    @Autowired
    private ISelectDepartmentMapper selectDepartmentMapper;
    @Autowired
    private ISelectTopicMapper selectTopicMapper;
    @Autowired
    private ISelectSubjectMapper selectSubjectMapper;



    
    public ModelAndView userList(ModelAndView  modelAndView , SelectUserBaseVo userBaseVo) {
        Page<SelectUserBaseDto> page = new Page<>(userBaseVo.getPage(),userBaseVo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,userBaseVo);
        List<SelectUserBaseDto> userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasesPo2Dto(userList);
        setUserDto(userBaseDto);
        page.setRecords(userBaseDto);
        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        List<SelectMajor> majorList = selectMajorMapper.selectList(new EntityWrapper<SelectMajor>()
                .and("maj_status=?", EnumEnOrDis.ENABLED.getValue()));
        if (!ObjectUtils.isEmpty(userBaseVo.getMajorIds())){
            majorList = selectMajorMapper.selectList(new EntityWrapper<SelectMajor>()
                    .and("maj_status=?", EnumEnOrDis.ENABLED.getValue()).in("id",userBaseVo.getMajorIds()));
        }

        List<SelectUserBase> classList = selectUserBaseMapper.selectStuClass();
        List<SelectUserBase> depList = selectUserBaseMapper.selectTeaDep();

        List<SelectDepartment> teaDepList = selectDepartmentMapper.selectTeaDep();
        if (!ObjectUtils.isEmpty(userBaseVo.getTeaDepId())){
            teaDepList = selectDepartmentMapper.selectList(new EntityWrapper<SelectDepartment>()
                    .and("id = ?" ,userBaseVo.getTeaDepId()));
        }
        modelAndView.addObject("userList", userBaseDto);
        modelAndView.addObject("page",page);
        modelAndView.addObject("yearList",yearList);
        modelAndView.addObject("majorList",majorList);
        modelAndView.addObject("classList",classList);
        modelAndView.addObject("depList",depList);
        modelAndView.addObject("teaDepList",teaDepList);
        modelAndView.addObject("teaPosition", EnumTeaPosition.toMap());
        modelAndView.addObject("teaEducation", EnumTeaEducation.toMap());
        return modelAndView;
    }

    /**
     * 设置用户其他属性
     * @param userBaseDto
     */
    private void setUserDto(List<SelectUserBaseDto> userBaseDto) {
        for (SelectUserBaseDto dto:userBaseDto){
            //性别
            dto.setSex(EnumUserSex.toMap().get(dto.getUserSex()));
            if (!ObjectUtils.isEmpty(dto.getStuMajorId())){
                //学生专业
                SelectMajor major = selectMajorMapper.selectById(dto.getStuMajorId());
                if (!ObjectUtils.isEmpty(major)){
                    dto.setStuMajorName(major.getMajName());
                }
            }
            if (!ObjectUtils.isEmpty(dto.getTeaDepId())){
                //教师专业
                SelectDepartment department = selectDepartmentMapper.selectById(dto.getTeaDepId());
                if (!ObjectUtils.isEmpty(department)){
                    dto.setTeaDepName(department.getDepName());
                }
            }
            if (!ObjectUtils.isEmpty(dto.getTeaPosition())){
                //职称
                dto.setTeaPositionZ(EnumTeaPosition.toMap().get(dto.getTeaPosition()));
            }
            if (!ObjectUtils.isEmpty(dto.getTeaEducation())){
                //学历
                dto.setTeaEducationZ(EnumTeaEducation.toMap().get(dto.getTeaEducation()));
            }
        }
    }

    /**
     * 设置单个用户其他属性
     * @param dto
     */
    private void setUserDto(SelectUserBaseDto dto) {

        dto.setSex(EnumUserSex.toMap().get(dto.getUserSex()));
        if (!ObjectUtils.isEmpty(dto.getTeaPosition())){
            //职称
            dto.setTeaPositionZ(EnumTeaPosition.toMap().get(dto.getTeaPosition()));
        }
        if (!ObjectUtils.isEmpty(dto.getTeaEducation())){
            //学历
            dto.setTeaEducationZ(EnumTeaEducation.toMap().get(dto.getTeaEducation()));
        }
        if (!ObjectUtils.isEmpty(dto.getStuMajorId())){
            //学生专业
            SelectMajor major = selectMajorMapper.selectById(dto.getStuMajorId());
            if (!ObjectUtils.isEmpty(major)){
                dto.setStuMajorName(major.getMajName());
            }
        }
        if (!ObjectUtils.isEmpty(dto.getTeaDepId())){
            //教师专业
            SelectDepartment department = selectDepartmentMapper.selectById(dto.getTeaDepId());
            if (!ObjectUtils.isEmpty(department)){
                dto.setTeaDepName(department.getDepName());
            }
        }
    }

    /***
     * 异步生成学生列表
     * @param userBaseVo
     * @return
     */
    
    public String stuListAjax(SelectUserBaseVo userBaseVo) {
        Page<SelectUserBaseDto> page = new Page<>(userBaseVo.getPage(),userBaseVo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,userBaseVo);
        List<SelectUserBaseDto> userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasesPo2Dto(userList);
        setUserDto(userBaseDto);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("stuList",userBaseDto);
        return JSONObject.toJSONString(map);
//        return map;
    }

    /**
     * 学生启用禁用
     * 存在选题记录不可禁用
     * 专业禁用不可启用
     * @param userBaseVo
     * @return
     */
    
    public String stuAble(SelectUserBaseVo userBaseVo) {

        if (userBaseVo.getUserStatus().equals(EnumEnOrDis.ENABLED.getValue())){
            //启用，判断所属专业是否被禁用
            SelectUserBase userBase = this.selectById(userBaseVo.getId());
            SelectMajor selectMajor = selectMajorMapper.selectById(userBase.getStuMajorId());
            if (ObjectUtils.isEmpty(selectMajor)||selectMajor.getMajStatus().equals(EnumEnOrDis.DISABLED.getValue())){
                logger.info(Constant.STU_ABLE_ERROR_MAJ_DISABLE);
                return JSONObject.toJSONString(Constant.STU_ABLE_ERROR_MAJ_DISABLE);
            }
        }else {
            //禁用：判断选题记录是否完成
            SelectTopic selectTopic = selectTopicMapper.selectOne(new SelectTopic()
                    .setStuId(userBaseVo.getId())
                    .setDelState(EnumYesOrNo.NO.getValue()));
            if (!ObjectUtils.isEmpty(selectTopic)){
                if (!selectTopic.getTeaAuditState().equals(EnumSubState.OVER.getValue())){
                    return JSONObject.toJSONString(Constant.STU_DISABLE_ERROR_TOPIC);
                }
            }
        }
        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBaseVo.getId())
                .setUserStatus(userBaseVo.getUserStatus());
        return this.updateById(selectUserBase) ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 删除学生记录
     * 存在选题记录不可删除
     * @param userBaseVo
     * @return
     */
    @Transactional
    public String stuDelete(SelectUserBaseVo userBaseVo) {
        //存在选题记录不可删除
        SelectTopic selectTopic = selectTopicMapper.selectOne(new SelectTopic().setStuId(userBaseVo.getId())
            .setDelState(EnumEnOrDis.DISABLED.getValue()));
        if (!ObjectUtils.isEmpty(selectTopic)){
            return JSONObject.toJSONString(Constant.STU_DEL_ERROR_EXIST_SELECT);
        }
        //不存在则删除：1删除假删除过的记录，2删除学生
        selectTopic = selectTopicMapper.selectOne(new SelectTopic().setStuId(userBaseVo.getId())
                .setDelState(EnumEnOrDis.ENABLED.getValue()));
        if (!ObjectUtils.isEmpty(selectTopic)){
            selectTopicMapper.deleteById(selectTopic);
        }

        SelectUserBase selectUserBase = new SelectUserBase()
                .setId(userBaseVo.getId());
        return this.deleteById(selectUserBase) ? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 批量删除学生
     * @param selectedIDs
     * @return
     */
    
    public String stuDeleteAll(Integer[] selectedIDs) {
        //存在选题记录不可删除
        for (Integer id: selectedIDs){
            SelectUserBase selectUserBase = new SelectUserBase().setId(id);
            //存在选题记录不可删除
            SelectTopic selectTopic = selectTopicMapper.selectOne(new SelectTopic().setStuId(id)
                    .setDelState(EnumEnOrDis.DISABLED.getValue()));
            if (!ObjectUtils.isEmpty(selectTopic)){
                return JSONObject.toJSONString(Constant.STU_DEL_ERROR_EXIST_SELECT_NAME+selectUserBase.getUserName());
            }
            //不存在则删除：1删除假删除过的记录，2删除学生
            selectTopic = selectTopicMapper.selectOne(new SelectTopic().setStuId(id)
                    .setDelState(EnumEnOrDis.ENABLED.getValue()));
            if (!ObjectUtils.isEmpty(selectTopic)){
                selectTopicMapper.deleteById(selectTopic);
            }
        }
        return this.deleteBatchIds(Arrays.asList(selectedIDs))?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }



    /**
     * 初始化编辑页面
     * 返回学生信息
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    
    public ModelAndView stuInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo) {
        SelectUserBase userBase = this.selectById(userBaseVo.getId());
        if (!ObjectUtils.isEmpty(userBase)){
            modelAndView.addObject("user",userBase);
        }
//        List<SelectUserBase> yearList = selectUserBaseMapper.selectStuYear();
        List<SelectUserBase> yearList = new ArrayList<>();
        if (CollectionUtils.isEmpty(yearList)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            Integer year  = Integer.parseInt(sdf.format(date));
            for (int i = -5; i<1 ;i++){
                yearList.add(new SelectUserBase().setStuYear(Integer.toString(year+i)));
            }
        }
        List<SelectMajor> majorList = selectMajorMapper.selectList(new EntityWrapper<SelectMajor>(new SelectMajor().setMajStatus(EnumEnOrDis.ENABLED.getValue())));
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
    
    public String stuUpdate(SelectUserBase userBase) {
        //校验重复


        return this.updateById(userBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }


    /**
     * 学生添加
     * @param user
     * @return
     */
    
    public String stuAdd(SelectUserBase user) {
        if (ObjectUtils.isEmpty(user)){
            return  JSONObject.toJSONString(Constant.ERROR);
        }
        String msg = checkCodeAndName(user);
        if (!StringUtils.isEmpty(msg)) return msg;
        user.setGmtCreate(new Date())
                .setUserType(EnumUserType.STUDENT.getValue())
                .setUserPassword(Constant.USER_PASSWORD);
        return this.insert(user)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    public void down(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception{
        OutputStream outputStream = null;
        InputStream inputStream=null;
        try {
            outputStream = response.getOutputStream();
            response.setContentType("application/octet-stream;charset=UTF-8");// 设置文件输出类型
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));//设置下载的文件名
            String baseAbsoluteFilePath=request.getServletContext().getRealPath("");//获取的是项目在磁盘中的绝对路径，最后包括"\"

            String fileRelativePath="WEB-INF/file/"+fileName;//文件相对于webRoot的路径

            inputStream=new FileInputStream(baseAbsoluteFilePath+fileRelativePath);
            byte[] buff=new byte[1024];
            Integer readLength=0;
            while((readLength=inputStream.read(buff,0,buff.length))>0){
                outputStream.write(buff, 0, readLength);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
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
            return JSONObject.toJSONString(Constant.STU_ADD_ERROR_CODE_EXIST);
        }
//        userBase = new SelectUserBase().setUserName(user.getUserName());
//        userBase = selectUserBaseMapper.selectOne(userBase);
//        if (!ObjectUtils.isEmpty(userBase)){
//            logger.info("姓名重复");
//            return  Constant.STU_ADD_ERROR_NAME_EXIST;
//        }
        return null;
    }


    /***
     * 学生导入
     * @param request
     * @return
     */
    
    public String stuUpload(HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile importFile = multipartRequest.getFile("fileField");
        if (importFile == null || !"select_students.xls".equals(importFile.getOriginalFilename())) {
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_NAME_ERROR);
        }
        // Excel的表头与文字对应，获取Excel表头
        LinkedHashMap<String, String> map = new LinkedHashMap<>(9);
        map.put("账号", "userCode");
        map.put("姓名", "userName");
        map.put("性别", "userSex");
        map.put("邮箱", "userMail");
        map.put("电话", "userPhone");
        map.put("qq", "userQq");
        map.put("专业", "stuMajorName");
        map.put("班级", "stuClass");
        map.put("届别", "stuYear");

        // 调用Excel共用类，转化成List
        List<ImportStuVo> importVOS;
        try {
            importVOS = ExcelUtil.excelToList(importFile.getInputStream(), "import", ImportStuVo.class, map, new String[]{});
        } catch (Exception e) {
            logger.info("ERROR -> 学生导入 : " + e.getMessage());
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_ERROR);
        }

        //导入失败记录
        List<String> importErrorList = new ArrayList<>();
        int importSuccessCount = 0;

        for (ImportStuVo importStuVo : importVOS) {
            //检查专业是否存在，不存在则不可导入
            SelectMajor selectMajor = new SelectMajor()
                    .setMajName(importStuVo.getStuMajorName());
            selectMajor = selectMajorMapper.selectOne(selectMajor);
            if (selectMajor == null) {
                importErrorList.add(importStuVo.getUserName());
                logger.info("专业不存在，添加失败 : " + importStuVo.toString());
                continue;
            }
            //学生初始化
            try {
                if (!this.initStu(importStuVo)) {
                    logger.info("学生导入失败 : " + importStuVo.getUserName());
                    importErrorList.add(importStuVo.getUserName());
                    continue;
                }

            } catch (Exception e) {
                logger.info("学生导入失败 : " + importStuVo.getUserName() + " Cause :" + e.getMessage());
                importErrorList.add(importStuVo.getUserName());
                continue;

            }
            //记录成功导入车辆数
            importSuccessCount++;
            logger.info("学生导入成功 : " + importStuVo.getUserName() + " , 本次导入学生数 : " + importVOS.size() + " , 已导入学生数 : " + importSuccessCount);
        }
        logger.info("本次导入学生数 : " + importVOS.size() + " , 导入成功 : " + importSuccessCount + " , 导入失败 : " + importErrorList.size());

        return JSONObject.toJSONString("导入成功 : " + importSuccessCount + " , 导入失败 : " + importErrorList.size() + (importErrorList.size() <= 0 ? "" : " , 失败学生姓名 : " + importErrorList.toString()));
    }


    /**
     * 学生导入初始化
     * @param importStuVo
     * @return
     */
    private boolean initStu(ImportStuVo importStuVo) {
        //TODO 账号格式验证

        //查看是否重复
        SelectUserBase selectUserBase = new SelectUserBase()
                .setUserCode(importStuVo.getUserCode());
        selectUserBase = selectUserBaseMapper.selectOne(selectUserBase);
        if (!ObjectUtils.isEmpty(selectUserBase)){
            logger.info("------------》学生重复！");
            return false;
        }
        //获取学生专业
        SelectMajor major = selectMajorMapper.selectOne(new SelectMajor().setMajName(importStuVo.getStuMajorName()));
        if (ObjectUtils.isEmpty(major)){
            logger.info("专业不存在");
            return false;
        }
        SelectUserBase userBase = new SelectUserBase()
                .setUserName(importStuVo.getUserName())
                .setUserCode(importStuVo.getUserCode())
                .setUserSex(EnumUserSex.toMap2().get(importStuVo.getUserSex()))
                .setUserMail(importStuVo.getUserMail())
                .setUserPhone(importStuVo.getUserPhone())
                .setUserQq(importStuVo.getUserQq())
                .setStuMajorId(major.getId())
                .setStuClass(importStuVo.getStuClass())
                .setStuYear(importStuVo.getStuYear())
                .setUserPassword(Constant.USER_PASSWORD)
                .setUserType(EnumUserType.STUDENT.getValue())
                .setUserStatus(EnumEnOrDis.ENABLED.getValue())
                .setGmtCreate(new Date());
        if (!this.insert(userBase)){
            logger.info("添加失败 : " + importStuVo.getUserName());
            return false;
        }
        return true;
    }


    /**
     *
     * 根据专业查询班级数
     * @param userBase
     * @return
     */
    
    public String initClass(SelectUserBase userBase) {
        if (ObjectUtils.isEmpty(userBase.getStuMajorId())){
            return JSONObject.toJSONString(Constant.PARAM_ERROR);
        }
        SelectMajor selectMajor = selectMajorMapper.selectById(userBase.getStuMajorId());
        if (ObjectUtils.isEmpty(selectMajor)){
            return JSONObject.toJSONString(Constant.NULL_ERROR);
        }
        return selectMajor.getMajClassNum().toString();
    }






/***************************************************** Tea ********************************************************************/
/***************************************************** Tea ********************************************************************/
/***************************************************** Tea ********************************************************************/


    /**
     * 异步生成教师列表
     * @param userBaseVo
     * @return
     */
    
    public String teaListAjax(SelectUserBaseVo userBaseVo) {
        Page<SelectUserBaseDto> page = new Page<>(userBaseVo.getPage(),userBaseVo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,userBaseVo);
        List<SelectUserBaseDto> userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasesPo2Dto(userList);
        setUserDto(userBaseDto);
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("teaList",userBaseDto);
        String object = JSONObject.toJSONString(map);
        return object;
    }

    /***
     * 教师启用禁用
     * @param userBase
     * @return
     */
    
    public String teaAble(SelectUserBase userBase) {
        //有题目信息的不可禁用/系别禁用时教师不可启用
        SelectUserBase selectUserBase = selectUserBaseMapper.selectById(userBase.getId());
        if (ObjectUtils.isEmpty(selectUserBase)){
            return JSONObject.toJSONString(Constant.ERROR);
        }
        if(userBase.getUserStatus().equals(EnumEnOrDis.DISABLED.getValue())){
            //有题目信息的不可禁用
            List<SelectSubject> selectSubjects = selectSubjectMapper.selectList(new EntityWrapper<>(new SelectSubject().setTeaId(userBase.getId())));
            if (CollectionUtils.isEmpty(selectSubjects)){
                logger.info(Constant.TEA_DEL_ERROR_EXIST_SELECT);
                return JSONObject.toJSONString(Constant.TEA_DEL_ERROR_EXIST_SELECT);
            }
        }else {
            //系别禁用时教师不可启用
            SelectDepartment department = selectDepartmentMapper.selectById(selectUserBase.getTeaDepId());
            if (department.getDepStatus().equals(EnumEnOrDis.DISABLED.getValue())){
                return JSONObject.toJSONString(Constant.TEA_DEL_ERROR_EXIST_SELECT);
            }
        }


        return this.updateById(userBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 教师添加、编辑初始化
     * @param modelAndView
     * @param userBaseVo
     * @return
     */
    
    public ModelAndView teaInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo) {
        SelectUserBase userBase = this.selectById(userBaseVo.getId());
        if (!ObjectUtils.isEmpty(userBase)){
            SelectUserBaseDto userBaseDto = SelectMapStructMapper.INSTANCE.SelectUserBasePo2Dto(userBase);
            setUserDto(userBaseDto);
            modelAndView.addObject("user",userBaseDto);
        }
        List<SelectDepartment> teaDepList = selectDepartmentMapper.selectTeaDep();
        modelAndView.addObject("teaPosition",EnumTeaPosition.toMap());
        modelAndView.addObject("teaEducation",EnumTeaEducation.toMap());
        modelAndView.addObject("teaDepList",teaDepList);
        return modelAndView;
    }

    /***
     * 教师添加
     * @param userBase
     * @return
     */
    
    public String teaAdd(SelectUserBase userBase) {
        //校验重复性
        String msg = checkCodeAndName(userBase);
        if (!StringUtils.isEmpty(msg)) return msg;
        userBase.setGmtCreate(new Date())
                .setUserType(EnumUserType.TEACHER.getValue())
                .setUserPassword(Constant.USER_PASSWORD);
        return this.insert(userBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 教师编辑
     * @param userBase
     * @return
     */
    
    public String teaUpdate(SelectUserBase userBase) {
//        String msg = checkCodeAndName(userBase);
//        if (StringUtils.isEmpty(msg)) return msg;
        return this.updateById(userBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 删除教师
     * @param userBase
     * @return
     */
    
    public String teaDelete(SelectUserBase userBase) {
        //是否存在题目信息
        List<SelectSubject> selectSubjects = selectSubjectMapper.selectList(new EntityWrapper<>(new SelectSubject().setTeaId(userBase.getId())));
        if (CollectionUtils.isEmpty(selectSubjects)){
            logger.info(Constant.TEA_DEL_ERROR_EXIST_SELECT);
            return JSONObject.toJSONString(Constant.TEA_DEL_ERROR_EXIST_SELECT);
        }
        return this.deleteById(userBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 批量删除教师
     * @param selectedIDs
     * @return
     */
    
    public String teaDeleteAll(Integer[] selectedIDs) {
        //存在题目不可删除
        for (Integer i: selectedIDs) {
            List<SelectSubject> selectSubjects = selectSubjectMapper.selectList(new EntityWrapper<>(new SelectSubject().setTeaId(i)));
            if (CollectionUtils.isEmpty(selectSubjects)){
                logger.info(Constant.TEA_DEL_ERROR_EXIST_SELECT);
                return JSONObject.toJSONString(Constant.TEA_DEL_ERROR_EXIST_SELECT_NAME);
            }
        }
        return this.deleteBatchIds(Arrays.asList(selectedIDs))?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }


    /**
     * 教师导入
     * @param request
     * @return
     */
    
    public String teaUpload(HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile importFile = multipartRequest.getFile("fileField");
        if (importFile == null || !"select_teachers.xls".equals(importFile.getOriginalFilename())) {
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_NAME_ERROR);
        }
        // Excel的表头与文字对应，获取Excel表头
        LinkedHashMap<String, String> map = new LinkedHashMap<>(10);
        map.put("账号", "userCode");
        map.put("姓名", "userName");
        map.put("性别", "userSex");
        map.put("邮箱", "userMail");
        map.put("电话", "userPhone");
        map.put("qq", "userQq");
        map.put("职称", "teaPosition");
        map.put("专业", "teaMajorName");
        map.put("学历", "teaEducation");
        map.put("系别", "teaDepName");

        // 调用Excel共用类，转化成List
        List<ImportTeaVo> importVOS;
        try {
            importVOS = ExcelUtil.excelToList(importFile.getInputStream(), "import", ImportTeaVo.class, map, new String[]{});
        } catch (Exception e) {
            logger.info("ERROR -> 教师导入 : " + e.getMessage());
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_ERROR);
        }

        //导入失败记录
        List<String> importErrorList = new ArrayList<>();
        int importSuccessCount = 0;

        for (ImportTeaVo importTeaVo : importVOS) {
            //检查系别是否存在，不存在则不可导入
            SelectDepartment selectDepartment = new SelectDepartment()
                    .setDepName(importTeaVo.getTeaDepName());
            selectDepartment = selectDepartmentMapper.selectOne(selectDepartment);
            if (selectDepartment == null) {
                importErrorList.add(importTeaVo.getUserName());
                logger.info("系别不存在，添加失败 : " + importTeaVo.toString());
                continue;
            }
            //车辆初始化
            try {
                if (!this.initTea(importTeaVo)) {
                    logger.info("教师导入失败 : " + importTeaVo.getUserName());
                    importErrorList.add(importTeaVo.getUserName());
                    continue;
                }

            } catch (Exception e) {
                logger.info("教师导入失败 : " + importTeaVo.getUserName() + " Cause :" + e.getMessage());
                importErrorList.add(importTeaVo.getUserName());
                continue;

            }
            //记录成功导入车辆数
            importSuccessCount++;
            logger.info("教师导入成功 : " + importTeaVo.getUserName() + " , 本次导入教师数 : " + importVOS.size() + " , 已导入教师数 : " + importSuccessCount);
        }
        logger.info("本次导入教师数 : " + importVOS.size() + " , 导入成功 : " + importSuccessCount + " , 导入失败 : " + importErrorList.size());

        return JSONObject.toJSONString("导入成功 : " + importSuccessCount + " , 导入失败 : " + importErrorList.size() + (importErrorList.size() <= 0 ? "" : " , 失败教师姓名 : " + importErrorList.toString()));
    }


    private boolean initTea(ImportTeaVo importTeaVo) {
        //TODO 格式校验

        //重复性校验
        SelectUserBase selectUserBase = new SelectUserBase()
                .setUserCode(importTeaVo.getUserCode());
        selectUserBase = selectUserBaseMapper.selectOne(selectUserBase);
        if (!ObjectUtils.isEmpty(selectUserBase)){
            logger.info("------------》教师重复！");
            return false;
        }
        SelectDepartment department = selectDepartmentMapper.selectOne(new SelectDepartment().setDepName(importTeaVo.getTeaDepName()));
        if (ObjectUtils.isEmpty(department)){
            logger.info("系别不存在");
            return  false;
        }
        SelectUserBase userBase = new SelectUserBase()
                .setUserName(importTeaVo.getUserName())
                .setUserCode(importTeaVo.getUserCode())
                .setUserSex(EnumUserSex.toMap2().get(importTeaVo.getUserSex()))
                .setUserMail(importTeaVo.getUserMail())
                .setUserPhone(importTeaVo.getUserPhone())
                .setUserQq(importTeaVo.getUserQq())
                .setTeaPosition(EnumTeaPosition.toMap2().get(importTeaVo.getTeaPosition()))
                .setTeaMajorName(importTeaVo.getTeaMajorName())
                .setTeaEducation(EnumTeaEducation.toMap2().get(importTeaVo.getTeaEducation()))
                .setTeaDepId(department.getId())
                .setUserPassword(Constant.USER_PASSWORD)
                .setUserType(EnumUserType.TEACHER.getValue())
                .setUserStatus(EnumEnOrDis.ENABLED.getValue())
                .setGmtCreate(new Date());
        if (!this.insert(userBase)){
            logger.info("添加失败 : " + importTeaVo.getUserName());
            return false;
        }
        return true;
    }





    /***
     * 管理员添加
     * @param user
     * @return
     */
    
    public String admAdd(SelectUserBase user) {
        if (ObjectUtils.isEmpty(user)){
            return  JSONObject.toJSONString(Constant.ERROR);
        }
        String msg = checkCodeAndName(user);
        if (!StringUtils.isEmpty(msg)) return msg;
        user.setGmtCreate(new Date())
                .setUserType(EnumUserType.ADMIN.getValue())
                .setUserPassword(Constant.USER_PASSWORD);
        return this.insert(user)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    
    public String admDeleteAll(Integer[] selectedIDs) {
        return this.deleteBatchIds(Arrays.asList(selectedIDs))?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    
    public String admDelete(SelectUserBaseVo userBaseVo) {
        return this.deleteById(userBaseVo.getId())?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    
    public String admAble(SelectUserBaseVo userBaseVo) {
        SelectUserBase selectUserBase = new SelectUserBase().setId(userBaseVo.getId()).setUserStatus(userBaseVo.getUserStatus());

        return this.updateById(selectUserBase)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /**
     * 账号重复检测
     * @param changePs
     * @return
     */
    public String checkCode(ChangePs changePs) {
        List<SelectUserBase> userBases = selectUserBaseMapper.selectList(new EntityWrapper<>(new SelectUserBase().setUserCode(changePs.getUserCode())));
//        List<SelectUserBase> userBases = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
//                .and("user_code=?",changePs.getUserCode())
//                .and("user_type=?",changePs.getUserType())
//        );
//        if (changePs.getUserType().equals(EnumUserType.ADMIN.getValue())||changePs.getUserType().equals(EnumUserType.ADMIN0.getValue())){
//            userBases = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
//                    .and("user_code=?",changePs.getUserCode())
//                    .in("user_type",new Integer[]{EnumUserType.ADMIN.getValue(),EnumUserType.ADMIN0.getValue()})
//            );
//        }
        Map<String,Boolean> map = new HashMap<>();
        if (!CollectionUtils.isEmpty(userBases)){
            map.put("valid",false);
        }else {
            map.put("valid",true);
        }

        return JSONObject.toJSONString(map);
    }

    public String stuDisAbleAll(Integer[] selectedIDs,Integer state) {
        List<SelectUserBase> userBases = new ArrayList<>();
        if (EnumEnOrDis.ENABLED.getValue().equals(state)){
            for (Integer id:selectedIDs){
                //启用，判断所属专业是否被禁用
                SelectUserBase userBase = this.selectById(id);
                SelectMajor selectMajor = selectMajorMapper.selectById(userBase.getStuMajorId());
                if (ObjectUtils.isEmpty(selectMajor)||selectMajor.getMajStatus().equals(EnumEnOrDis.DISABLED.getValue())){
                    logger.info(Constant.STU_ABLE_ERROR_MAJ_DISABLE);
                    return JSONObject.toJSONString(Constant.STU_ABLE_ERROR_MAJ_DISABLE+"  学生："+userBase.getUserName());
                }
                userBases.add(userBase.setUserStatus(EnumEnOrDis.ENABLED.getValue()));
            }
        }else {
            for (Integer id:selectedIDs){
                //禁用：判断选题记录是否完成
                SelectUserBase userBase = this.selectById(id);
                SelectTopic selectTopic = selectTopicMapper.selectOne(new SelectTopic()
                        .setStuId(id)
                        .setDelState(EnumYesOrNo.NO.getValue()));
                if (!ObjectUtils.isEmpty(selectTopic)){
                    if (!selectTopic.getTeaAuditState().equals(EnumSubState.OVER.getValue())){
                        return JSONObject.toJSONString(Constant.STU_DISABLE_ERROR_TOPIC+"  学生："+userBase.getUserName());
                    }
                }
                userBases.add(userBase.setUserStatus(EnumEnOrDis.DISABLED.getValue()));
            }
        }
        return this.updateBatchById(userBases)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }
}

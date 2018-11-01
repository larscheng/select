package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.ExcelUtil;
import com.slxy.www.common.ExcelUtils;
import com.slxy.www.common.SelectMapStructMapper;
import com.slxy.www.dao.*;
import com.slxy.www.domain.dto.SelectTopicDto;
import com.slxy.www.domain.po.*;
import com.slxy.www.domain.vo.ImportScoreVo;
import com.slxy.www.domain.vo.ImportStuVo;
import com.slxy.www.domain.vo.SelectTopicVo;
import com.slxy.www.enums.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * SelectTopic 表数据服务层接口实现类
 *
 */
@Service
public class SelectTopicService extends  ServiceImpl <ISelectTopicMapper, SelectTopic>  {


    @Autowired
    private ISelectTopicMapper selectTopicMapper;
    @Autowired
    private ISelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private ISelectSubjectMapper selectSubjectMapper;
    @Autowired
    private SelectScorePerService selectScorePerService;
    @Autowired
    private SelectProcessControlService selectProcessControlService;
    @Autowired
    private SelectJavaMailService selectJavaMailService;
    @Autowired
    private ISelectDepartmentMapper selectDepartmentMapper;

    @Autowired
    private ISelectMajorMapper selectMajorMapper;

    public ModelAndView topicList(ModelAndView modelAndView, SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());

        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);

        if (!ObjectUtils.isEmpty(vo.getCountType())&&vo.getCountType()==1){
            list = selectTopicMapper.countTopicByPage(page, vo);
        }
        list.stream().map(a-> a.setDepName(selectDepartmentMapper.selectById(a.getForDepId()).getDepName())
                .setMajorName(getMajorByUserId(a.getStuId()))
        ).collect(Collectors.toList());
        page.setRecords(list);
        List<SelectUserBase> teaList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = ?", EnumUserType.TEACHER.getValue()).and("user_status = ?", EnumEnOrDis.ENABLED.getValue()));
        List<SelectUserBase> stuList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = ?", EnumUserType.STUDENT.getValue()).and("user_status = ?", EnumEnOrDis.ENABLED.getValue()));

        List<SelectDepartment> depList = selectDepartmentMapper.selectList(new EntityWrapper<>(new SelectDepartment().setDepStatus(EnumEnOrDis.ENABLED.getValue()).setId(vo.getForDepId())));

        modelAndView.addObject("page", page);
        modelAndView.addObject("topicList", list);
        modelAndView.addObject("depList", depList);
        modelAndView.addObject("teaList", teaList);
        modelAndView.addObject("stuList", stuList);
        return modelAndView;
    }

    public String getMajorByUserId(Integer id){
        SelectUserBase userBase = selectUserBaseMapper.selectById(id);
        if (ObjectUtils.isEmpty(userBase)||ObjectUtils.isEmpty(userBase.getStuMajorId())){
            return null;
        }
        return selectMajorMapper.selectById(userBase.getStuMajorId()).getMajName();
    }

    /**
     * 异步生成学生选题列表
     * @param vo
     * @return
     */

    public String stuTopicAjaxList(SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);
//        page.setRecords(list);
        list.stream().map(a-> a.setDepName(selectDepartmentMapper.selectById(a.getForDepId()).getDepName())
                .setMajorName(getMajorByUserId(a.getStuId()))).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("topicList",list);
        map.put("page",page);
        String object = JSONObject.toJSONString(map);
        return object;
    }


    public ModelAndView topicDetails(ModelAndView modelAndView, SelectTopicVo vo) {
        SelectTopic topic = this.selectById(vo.getId());
        if (!ObjectUtils.isEmpty(topic)){
            SelectTopicDto dto = SelectMapStructMapper.INSTANCE.SelectTopicPoToDto(topic);
            //题目名、各项得分
            SelectSubject selectSubject = selectSubjectMapper.selectById(topic.getSubId());
            if (!ObjectUtils.isEmpty(selectSubject)){
                dto.setSubName(selectSubject.getSubName())
                        .setTutorScore(selectSubject.getTutorScore())
                        .setJudgeScore(selectSubject.getJudgeScore())
                        .setDefenceScore(selectSubject.getDefenceScore())
                        .setFinalTotalScore(selectSubject.getFinalTotalScore());
                SelectUserBase teacher = selectUserBaseMapper.selectById(selectSubject.getTeaId());
                //教师名
                dto.setTeaName(teacher.getUserName());
            }
            //学生名
            SelectUserBase selectUserBase = selectUserBaseMapper.selectById(topic.getStuId());
            if (!ObjectUtils.isEmpty(selectUserBase)){
                dto.setStuName(selectUserBase.getUserName());
            }

            modelAndView.addObject("topicDetails",dto);
        }

        return modelAndView;
    }


    /***
     * 教师审核选题
     * @param vo
     * @return
     */

    @Transactional
    public String topicAudited(SelectTopicVo vo) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.teaAudit.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        SelectTopic topic = this.selectById(vo.getId());
        if (ObjectUtils.isEmpty(topic)){
            return JSONObject.toJSONString(Constant.SELECT_ERROR_NOT_EXIST);
        }
        SelectTopic selectTopic = new SelectTopic()
                .setId(vo.getId());
        SelectSubject selectSubject = new SelectSubject()
                .setId(topic.getSubId());
        //获取学生信息
        SelectUserBase stuInfo = selectUserBaseMapper.selectById(topic.getStuId());
        SelectSubject subInfo = selectSubjectMapper.selectById(topic.getSubId());
        //判断操作，通过or不通过
        if (EnumSubState.SUCCESS.getValue().equals(vo.getTeaAuditState())){
            //通过
            selectTopic.setTeaAuditState(EnumSubState.SUCCESS.getValue())
                    .setTeaAuditContent(Constant.SELECT_SUCCESS_REASON);
            //更新论文状态---》已被选
            selectSubject.setSubSelectStatus(EnumSubSelectStatus.SUCCESS.getValue());
            //邮件通知学生选题成功
            selectJavaMailService.sendHtmlMail(stuInfo.getUserMail(),stuInfo.getUserName(),subInfo.getSubName(),"选题成功");
        }else{
            //不通过
            selectTopic.setTeaAuditState(EnumSubState.FAIL.getValue())
                    .setTeaAuditContent(vo.getTeaAuditContent());
            //更新论文状态---》未选
            selectSubject.setSubSelectStatus(EnumSubSelectStatus.Untreated.getValue());
            //邮件通知学生选题失败
            logger.info("邮件通知");
            selectJavaMailService.sendHtmlMail(stuInfo.getUserMail(),stuInfo.getUserName(),subInfo.getSubName(),"选题失败");
        }
        selectSubjectMapper.updateById(selectSubject);
        return this.updateById(selectTopic)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 学生上传选题附件
     * @param file
     * @param id
     * @param request
     * @param type
     * @return
     */

    public String uploadTaskBook(MultipartFile file, Integer id, HttpServletRequest request, Integer type) {
        SelectTopic topic = this.selectById(id);
        if (ObjectUtils.isEmpty(topic)){
            return JSONObject.toJSONString(Constant.SELECT_ERROR_NOT_EXIST);
        }
        if (!EnumSubState.SUCCESS.getValue().equals(topic.getTeaAuditState())){
            return JSONObject.toJSONString(Constant.SELECT_ERROR_NOT_AUDIT_SUCCESS);
        }
        SelectTopic selectTopic = new SelectTopic().setId(topic.getId());
        if (!ObjectUtils.isEmpty(file)){
//            String fileDir =request.getServletContext().getRealPath("");
//            String demoDir = "downFile";
            String fileDir = Constant.FILE_DIR;
            String demoDir = "demo";
            String demoPath = demoDir + File.separator;
            String fileName = file.getOriginalFilename();
            File outFile = new File(fileDir + demoPath);
            //保存路径字段
            if (type == 1){
                selectTopic.setTaskFile(demoDir+"/"+fileName);
            }else if (type == 2){
                selectTopic.setOpeningReport(demoDir+"/"+fileName);
            }else {
                selectTopic.setDissertation(demoDir+"/"+fileName);
            }


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
                return this.updateById(selectTopic)?JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 删除选题记录
     * @param id
     * @return
     */

    public String topicDel(int id) {
        SelectTopic topic = this.selectById(id);
        if (ObjectUtils.isEmpty(topic)) {
            return JSONObject.toJSONString(Constant.SELECT_ERROR_NOT_EXIST);
        }
        SelectTopic selectTopic = new SelectTopic()
                .setId(id)
                .setDelState(EnumEnOrDis.ENABLED.getValue());
        return this.updateById(selectTopic) ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }


    /***
     * 上传评分
     * @param vo
     * @return
     */

    public String uploadScore(SelectTopicVo vo) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.scoreCount.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        List<SelectScorePer> selectScorePers = selectScorePerService.selectList(new EntityWrapper<SelectScorePer>());
        SelectTopic selectTopic = this.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectTopic)){
            return JSONObject.toJSONString(Constant.ERROR);
        }
        SelectSubject subject = selectSubjectMapper.selectById(selectTopic.getSubId());
        SelectSubject selectSubject = new SelectSubject();
        if (!ObjectUtils.isEmpty(vo.getTutorScore())){
            //教师操作
            selectSubject.setId(selectTopic.getSubId())
                    .setTutorScore(vo.getTutorScore())
                    .setJudgeScore(subject.getJudgeScore())
                    .setDefenceScore(subject.getDefenceScore());
        }else {
            selectSubject.setId(selectTopic.getSubId())
                    .setTutorScore(subject.getTutorScore())
                    .setJudgeScore(ObjectUtils.isEmpty(vo.getJudgeScore())?0.0:vo.getJudgeScore())
                    .setDefenceScore(ObjectUtils.isEmpty(vo.getDefenceScore())?0.0:vo.getDefenceScore());
        }

        Double finalTotalScore = selectSubject.getTutorScore()*(selectScorePers.get(0).getScorePer()/100.00)+
                selectSubject.getJudgeScore()*(selectScorePers.get(1).getScorePer()/100.00)+
                selectSubject.getDefenceScore()*(selectScorePers.get(2).getScorePer()/100.00);
        selectSubject.setFinalTotalScore(finalTotalScore);

        return selectSubjectMapper.updateById(selectSubject) > 0 ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }

    /***
     * 导出选题记录
     * @param vo
     * @return
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws IntrospectionException
     * @throws ParseException
     * @throws IllegalAccessException
     */

    public XSSFWorkbook exportExcelInfo(SelectTopicVo vo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
        vo.setDelState(0);
        List<SelectTopicDto> selectTopics = selectTopicMapper.countTopicByPage(vo);
        selectTopics.stream()
                .map(selectTopicDto ->selectTopicDto.setTeaAuditStateName(EnumSubState.toMap().get(selectTopicDto.getTeaAuditState()))
                .setDepName(selectDepartmentMapper.selectById(selectTopicDto.getForDepId()).getDepName())
                .setMajorName(getMajorByUserId(selectTopicDto.getStuId())))
                .collect(Collectors.toList());
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("题目名称","subName",0));
        excel.add(new ExcelBean("发布教师","teaName",0));
        excel.add(new ExcelBean("教师电话","teaPhone",0));
        excel.add(new ExcelBean("选题学生","stuName",0));
        excel.add(new ExcelBean("学生学号","userCode",0));
        excel.add(new ExcelBean("学生电话","stuPhone",0));
        excel.add(new ExcelBean("审核状态","teaAuditStateName",0));
        excel.add(new ExcelBean("题目届别(级)","topicYear",0));
        excel.add(new ExcelBean("系别","depName",0));
        excel.add(new ExcelBean("专业","majorName",0));
        map.put(0, excel);
        String sheetName = "月份收入";
        //调用ExcelUtils的方法
        xssfWorkbook = ExcelUtils.createExcelFile(SelectTopicDto.class, selectTopics, map, sheetName);
        return xssfWorkbook;
    }


    /**
     * 导出成绩记录
     * @return
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws IntrospectionException
     * @throws ParseException
     * @throws IllegalAccessException
     */

    public XSSFWorkbook exportExcelScoreInfo(SelectTopicVo vo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
        vo.setDelState(0);
        List<SelectTopicDto> selectTopics = selectTopicMapper.countTopicByPage(vo);
        selectTopics.stream()
                .map(selectTopicDto ->selectTopicDto.setTeaAuditStateName(EnumSubState.toMap().get(selectTopicDto.getTeaAuditState()))
                        .setDepName(selectDepartmentMapper.selectById(selectTopicDto.getForDepId()).getDepName())
                        .setMajorName(getMajorByUserId(selectTopicDto.getStuId())))
                .collect(Collectors.toList());
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("题目名称","subName",0));
        excel.add(new ExcelBean("发布教师","teaName",0));
        excel.add(new ExcelBean("教师电话","teaPhone",0));
        excel.add(new ExcelBean("选题学生","stuName",0));
        excel.add(new ExcelBean("学生学号","userName",0));
        excel.add(new ExcelBean("学生电话","stuPhone",0));
        excel.add(new ExcelBean("指导老师评分","tutorScore",0));
        excel.add(new ExcelBean("评阅老师评分","judgeScore",0));
        excel.add(new ExcelBean("答辩的分","defenceScore",0));
        excel.add(new ExcelBean("最终得分","finalTotalScore",0));
        excel.add(new ExcelBean("题目届别(级)","topicYear",0));
        excel.add(new ExcelBean("系别","depName",0));
        excel.add(new ExcelBean("专业","majorName",0));
        map.put(0, excel);
        String sheetName = "月份收入";
        //调用ExcelUtils的方法
        xssfWorkbook = ExcelUtils.createExcelFile(SelectTopicDto.class, selectTopics, map, sheetName);
        return xssfWorkbook;
    }

    /***
     * 删除选题记录
     * 已被选未结题的选题记录不可删除
     * @param vo
     * @return
     */
    public String delTopic(SelectTopicVo vo) {
        //检测状态
        SelectTopic topic = this.selectById(vo.getId());
        SelectSubject selectSubject  = selectSubjectMapper.selectById(topic.getSubId());
        if (EnumSubSelectStatus.SUCCESS.getValue().equals(selectSubject.getSubSelectStatus())){
            return JSONObject.toJSONString(Constant.SELECT_DEL_ERROR);
        }

        SelectTopic selectTopic = this.selectById(vo.getId());
        //删除记录，删除文件
        deleteFile(Constant.FILE_DIR+selectTopic.getTaskFile());
        deleteFile(Constant.FILE_DIR+selectTopic.getOpeningReport());
        deleteFile(Constant.FILE_DIR+selectTopic.getDissertation());
        return this.deleteById(selectTopic) ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);

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

    /**
     * 批量评分
     * @param request
     * @return
     */
    public String scoreUpload(HttpServletRequest request) {
        //流程检测
        String msg = selectProcessControlService.testPc(EnumProControl.scoreCount.getValue());
        if (!msg.equalsIgnoreCase(JSONObject.toJSONString(Constant.SUCCESS))){
            return msg;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile importFile = multipartRequest.getFile("fileField");
        if (importFile == null || !"select_scores.xls".equals(importFile.getOriginalFilename())) {
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_NAME_ERROR);
        }
        // Excel的表头与文字对应，获取Excel表头
        LinkedHashMap<String, String> map = new LinkedHashMap<>(7);
        map.put("题目名称", "subName");
        map.put("教师名称", "teaName");
        map.put("学生名称", "stuName");
        map.put("题目届别", "subYear");
        map.put("指导老师评分", "tutorScore");
        map.put("评阅老师评分", "judgeScore");
        map.put("答辩得分", "defenceScore");

        // 调用Excel共用类，转化成List
        List<ImportScoreVo> importVOS;
        try {
            importVOS = ExcelUtil.excelToList(importFile.getInputStream(), "import", ImportScoreVo.class, map, new String[]{});
        } catch (Exception e) {
            logger.info("ERROR -> 成绩导入 : " + e.getMessage());
            return JSONObject.toJSONString(Constant.STU_IMPORT_ERROR_FILE_ERROR);
        }

        //导入失败记录
        List<String> importErrorList = new ArrayList<>();
        int importSuccessCount = 0;

        for (ImportScoreVo importScoreVo : importVOS) {
            //检查该选题 不存在则不可导入
            List<SelectTopic> selectTopics = selectTopicMapper.selectByInfo(importScoreVo);
            if (selectTopics.size()==1){
                importScoreVo.setSubId(selectTopics.get(0).getSubId());
            }
//            SelectMajor selectMajor = new SelectMajor()
//                    .setMajName(importScoreVo.getStuMajorName());
//            selectMajor = selectMajorMapper.selectOne(selectMajor);
//            if (selectMajor == null) {
//                importErrorList.add(importScoreVo.getUserName());
//                logger.info("上传失败 : " + importScoreVo.toString());
//                continue;
//            }
            //学生初始化
            try {
                if (!this.initScore(importScoreVo)) {
                    logger.info("成绩上传失败 : " + importScoreVo.getSubName());
                    importErrorList.add(importScoreVo.getSubName());
                    continue;
                }

            } catch (Exception e) {
                logger.info("成绩上传失败 : " + importScoreVo.getSubName() + " Cause :" + e.getMessage());
                importErrorList.add(importScoreVo.getSubName());
                continue;

            }
            //记录成功导入成绩的人数
            importSuccessCount++;
            logger.info("成绩上传成功 : " + importScoreVo.getSubName() + " , 本次评分数 : " + importVOS.size() + " , 已导入成绩数 : " + importSuccessCount);
        }
        logger.info("本次导入成绩数 : " + importVOS.size() + " , 导入成功 : " + importSuccessCount + " , 导入失败 : " + importErrorList.size());

        return JSONObject.toJSONString("评分成功 : " + importSuccessCount + " , 评分失败 : " + importErrorList.size() + (importErrorList.size() <= 0 ? "" : " , 失败题目名 : " + importErrorList.toString()));
    }

    private boolean initScore(ImportScoreVo importScoreVo) {
        //校验分数格式
        if(importScoreVo.getDefenceScore()>100.0||importScoreVo.getJudgeScore()>100.0||importScoreVo.getJudgeScore()>100.0){
            logger.info("分数值超过上限"+importScoreVo.getSubName());
            return false;
        }
        List<SelectScorePer> selectScorePers = selectScorePerService.selectList(new EntityWrapper<SelectScorePer>());
        Double finalTotalScore = importScoreVo.getTutorScore()*(selectScorePers.get(0).getScorePer()/100.00)+
                importScoreVo.getJudgeScore()*(selectScorePers.get(1).getScorePer()/100.00)+
                importScoreVo.getDefenceScore()*(selectScorePers.get(2).getScorePer()/100.00);
        SelectSubject selectSubject = new SelectSubject()
                .setId(importScoreVo.getSubId())
                .setJudgeScore(importScoreVo.getJudgeScore())
                .setDefenceScore(importScoreVo.getDefenceScore())
                .setTutorScore(importScoreVo.getTutorScore())
                .setFinalTotalScore(finalTotalScore);
        //评分同时，该选题状态变更为结题
        selectSubject.setSubSelectStatus(EnumSubSelectStatus.OVER.getValue());
        if (selectSubjectMapper.updateById(selectSubject)<0){
            logger.info("添加失败 : " + importScoreVo.getSubName());
            return false;
        }
        return true;
    }


    /**
     * 修改上传的文件：1删除原来的文件，2保存新文件，3保存新路径
     * @param file
     * @param id
     * @param type
     * @return
     */
    public String updateFile(MultipartFile file, int id,HttpServletRequest request, int type) {
        //删除原文件
        SelectTopic selectTopic = selectTopicMapper.selectById(id);
        if (type == 1){
            deleteFile(Constant.FILE_DIR+selectTopic.getTaskFile());
        }else if (type == 2){
            deleteFile(Constant.FILE_DIR+selectTopic.getOpeningReport());
        }else {
            deleteFile(Constant.FILE_DIR+selectTopic.getDissertation());
        }
        //保存新文件
        return uploadTaskBook(file,id,request,type);
    }

    /***
     * adm撤销题目
     * @param selectedIDs
     * @return
     */
    public String selectRevoke(Integer[] selectedIDs) {
        for (Integer id : selectedIDs){
            SelectTopic selectTopic = selectTopicMapper.selectById(id);
            SelectSubject selectSubject = selectSubjectMapper.selectById(selectTopic.getSubId());
            if (selectSubject.getSubSelectStatus().equals(EnumSubSelectStatus.OVER.getValue())){
                return JSONObject.toJSONString(Constant.SELECT_REV_ERROR+selectSubject.getSubName());
            }
            //删除记录，删除文件
            deleteFile(Constant.FILE_DIR+selectTopic.getTaskFile());
            deleteFile(Constant.FILE_DIR+selectTopic.getOpeningReport());
            deleteFile(Constant.FILE_DIR+selectTopic.getDissertation());
            SelectTopic topic = new SelectTopic()
                    .setId(id)
                    .setDelState(EnumEnOrDis.ENABLED.getValue());
            this.updateById(topic);
            SelectSubject subject = new SelectSubject()
                    .setId(selectSubject.getId())
                    .setSubSelectStatus(EnumSubSelectStatus.Untreated.getValue());
            selectSubjectMapper.updateById(subject);
        }
        return JSONObject.toJSONString(Constant.SUCCESS);
    }

    public String topicCountAjaxList(SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.countTopicByPage(page, vo);
        list.stream().map(a-> a.setDepName(selectDepartmentMapper.selectById(a.getForDepId()).getDepName())
                .setMajorName(getMajorByUserId(a.getStuId()))
        ).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("topicList",list);
        map.put("page",page);
        String object = JSONObject.toJSONString(map);
        return object;
    }


    public String setSubState(Integer state){
        if (state.equals(EnumSubSelectStatus.OVER.getValue())){
            return "已结题";
        }else {
            return "未结题";
        }
    }
}


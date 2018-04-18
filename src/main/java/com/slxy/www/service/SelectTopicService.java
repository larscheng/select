package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.ExcelUtils;
import com.slxy.www.common.SelectMapStructMapper;
import com.slxy.www.dao.ISelectSubjectMapper;
import com.slxy.www.dao.ISelectUserBaseMapper;
import com.slxy.www.domain.dto.SelectTopicDto;
import com.slxy.www.domain.po.*;
import com.slxy.www.domain.vo.SelectTopicVo;
import com.slxy.www.enums.EnumEnOrDis;
import com.slxy.www.enums.EnumSubSelectStatus;
import com.slxy.www.enums.EnumSubState;
import com.slxy.www.enums.EnumUserType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectTopicMapper;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
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



    public ModelAndView topicList(ModelAndView modelAndView, SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);

        page.setRecords(list);
        List<SelectUserBase> teaList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = ?", EnumUserType.TEACHER.getValue()).and("user_status = ?", EnumEnOrDis.ENABLED.getValue()));
        List<SelectUserBase> stuList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = ?", EnumUserType.STUDENT.getValue()).and("user_status = ?", EnumEnOrDis.ENABLED.getValue()));
        modelAndView.addObject("page", page);
        modelAndView.addObject("topicList", list);
        modelAndView.addObject("teaList", teaList);
        modelAndView.addObject("stuList", stuList);
        return modelAndView;
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
        SelectTopic topic = this.selectById(vo.getId());
        if (ObjectUtils.isEmpty(topic)){
            return JSONObject.toJSONString(Constant.SELECT_ERROR_NOT_EXIST);
        }
        SelectTopic selectTopic = new SelectTopic()
                .setId(vo.getId());
        SelectSubject selectSubject = new SelectSubject()
                .setId(topic.getSubId());
        //判断操作，通过or不通过
        if (EnumSubState.SUCCESS.getValue().equals(vo.getTeaAuditState())){
            //通过
            selectTopic.setTeaAuditState(EnumSubState.SUCCESS.getValue())
                    .setTeaAuditContent(Constant.SELECT_SUCCESS_REASON);
            //更新论文状态---》已被选
            selectSubject.setSubSelectStatus(EnumSubSelectStatus.SUCCESS.getValue());
        }else{
            //不通过
            selectTopic.setTeaAuditState(EnumSubState.FAIL.getValue())
                    .setTeaAuditContent(vo.getTeaAuditContent());
            //更新论文状态---》未选
            selectSubject.setSubSelectStatus(EnumSubSelectStatus.Untreated.getValue());
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
        List<SelectTopicDto> selectTopics = selectTopicMapper.selectAllTopic();
        selectTopics.stream()
                .map(selectTopicDto ->selectTopicDto.setTeaAuditStateName(EnumSubState.toMap().get(selectTopicDto.getTeaAuditState())))
                .collect(Collectors.toList());
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("题目名称","subName",0));
        excel.add(new ExcelBean("发布教师","teaName",0));
        excel.add(new ExcelBean("教师电话","teaPhone",0));
        excel.add(new ExcelBean("选题学生","stuName",0));
        excel.add(new ExcelBean("学生电话","stuPhone",0));
        excel.add(new ExcelBean("审核状态","teaAuditStateName",0));
        excel.add(new ExcelBean("题目届别(级)","topicYear",0));
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

    public XSSFWorkbook exportExcelScoreInfo() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        //根据条件查询数据，把数据装载到一个list中
        List<SelectTopicDto> selectTopics = selectTopicMapper.selectAllTopic();
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("题目名称","subName",0));
        excel.add(new ExcelBean("发布教师","teaName",0));
        excel.add(new ExcelBean("教师电话","teaPhone",0));
        excel.add(new ExcelBean("选题学生","stuName",0));
        excel.add(new ExcelBean("学生电话","stuPhone",0));
        excel.add(new ExcelBean("指导老师评分","tutorScore",0));
        excel.add(new ExcelBean("评阅老师评分","judgeScore",0));
        excel.add(new ExcelBean("答辩的分","defenceScore",0));
        excel.add(new ExcelBean("最终得分","finalTotalScore",0));
        excel.add(new ExcelBean("题目届别(级)","topicYear",0));
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
}


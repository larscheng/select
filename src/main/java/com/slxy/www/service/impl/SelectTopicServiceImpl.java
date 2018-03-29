package com.slxy.www.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.common.Constant;
import com.slxy.www.common.ExcelUtil;
import com.slxy.www.common.ExcelUtils;
import com.slxy.www.common.utils.SelectMapStructMapper;
import com.slxy.www.mapper.SelectMajorMapper;
import com.slxy.www.mapper.SelectSubjectMapper;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectMajor;
import com.slxy.www.model.SelectSubject;
import com.slxy.www.model.SelectTopic;
import com.slxy.www.mapper.SelectTopicMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.dto.SelectMajorDTO;
import com.slxy.www.model.dto.SelectTopicDto;
import com.slxy.www.model.enums.EnumEnOrDis;
import com.slxy.www.model.enums.EnumSubSelectStatus;
import com.slxy.www.model.enums.EnumSubState;
import com.slxy.www.model.enums.EnumUserType;
import com.slxy.www.model.po.ExcelBean;
import com.slxy.www.model.po.SelectScorePer;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import com.slxy.www.service.ISelectScorePerService;
import com.slxy.www.service.ISelectTopicService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
@Service
public class SelectTopicServiceImpl extends ServiceImpl<SelectTopicMapper, SelectTopic> implements ISelectTopicService {

    @Autowired
    private SelectTopicMapper selectTopicMapper;
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    @Autowired
    private SelectSubjectMapper selectSubjectMapper;
    @Autowired
    private ISelectScorePerService selectScorePerService;


    @Override
    public ModelAndView topicList(ModelAndView modelAndView, SelectTopicVo vo) {
        Page<SelectTopicDto> page = new Page<>(vo.getPage(), vo.getPageSize());
        List<SelectTopicDto> list = selectTopicMapper.getTopicByPage(page, vo);

        page.setRecords(list);
        List<SelectUserBase> teaList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = {0}", EnumUserType.TEACHER.getValue()).and("user_status = {0}", EnumEnOrDis.ENABLED.getValue()));
        List<SelectUserBase> stuList = selectUserBaseMapper.selectList(new EntityWrapper<SelectUserBase>()
                .and("user_type = {0}", EnumUserType.STUDENT.getValue()).and("user_status = {0}", EnumEnOrDis.ENABLED.getValue()));
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
    @Override
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

    @Override
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
    @Override
    @Transactional
    public String topicAudited(SelectTopicVo vo) {
        SelectTopic topic = this.selectById(vo.getId());
        if (ObjectUtils.isEmpty(topic)){
            return Constant.SELECT_ERROR_NOT_EXIST;
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
        return this.updateById(selectTopic)?Constant.SUCCESS:Constant.ERROR;
    }

    /***
     * 学生上传选题附件
     * @param file
     * @param id
     * @param request
     * @param type
     * @return
     */
    @Override
    public String uploadTaskBook(MultipartFile file, Integer id, HttpServletRequest request,Integer type) {
        SelectTopic topic = this.selectById(id);
        if (ObjectUtils.isEmpty(topic)){
            return Constant.SELECT_ERROR_NOT_EXIST;
        }
        if (!EnumSubState.SUCCESS.getValue().equals(topic.getTeaAuditState())){
            return Constant.SELECT_ERROR_NOT_AUDIT_SUCCESS;
        }
        SelectTopic selectTopic = new SelectTopic().setId(topic.getId());
        if (!ObjectUtils.isEmpty(file)){
//            String fileDir =request.getServletContext().getRealPath("");
//            String demoDir = "downFile";
            String fileDir = "C:/Users/Administrator/Desktop/online/";
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.updateById(selectTopic)?Constant.SUCCESS:Constant.ERROR;
    }

    /***
     * 删除选题记录
     * @param id
     * @return
     */
    @Override
    public String topicDel(int id) {
        SelectTopic topic = this.selectById(id);
        if (ObjectUtils.isEmpty(topic)) {
            return Constant.SELECT_ERROR_NOT_EXIST;
        }
        SelectTopic selectTopic = new SelectTopic()
                .setId(id)
                .setDelState(EnumEnOrDis.ENABLED.getValue());
        return this.updateById(selectTopic) ? Constant.SUCCESS : Constant.ERROR;
    }


    /***
     * 上传评分
     * @param vo
     * @return
     */
    @Override
    public String uploadScore(SelectTopicVo vo) {
        List<SelectScorePer> selectScorePers = selectScorePerService.selectList(new EntityWrapper<SelectScorePer>());
        SelectTopic selectTopic = this.selectById(vo.getId());
        if (ObjectUtils.isEmpty(selectTopic)){
            return Constant.ERROR;
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

        return selectSubjectMapper.updateById(selectSubject) > 0 ? Constant.SUCCESS : Constant.ERROR;
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
    @Override
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
    @Override
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


}

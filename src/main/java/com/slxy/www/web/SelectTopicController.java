package com.slxy.www.web;

import com.slxy.www.common.Constant;
import com.slxy.www.domain.vo.SelectTopicVo;
import com.slxy.www.enums.EnumSubSelectStatus;
import com.slxy.www.enums.EnumYesOrNo;
import com.slxy.www.service.SelectTopicService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
@Controller
@RequestMapping("/selectTopic")
public class SelectTopicController {
    @Autowired
    private SelectTopicService selectTopicService;

    /**
     * 选题信息列表 ，根据vo不同权限不同
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicList")
    public ModelAndView stuList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/topicList");
        vo.setDelState(EnumYesOrNo.NO.getValue());
        return selectTopicService.topicList(modelAndView,vo);
    }



    /***
     * 异步生成学生选题信息列表
     * @param vo
     * @return
     */
    @RequestMapping("/stuTopicAjaxList")
    @ResponseBody
    public String stuTopicAjaxList(SelectTopicVo vo) {
        return selectTopicService.stuTopicAjaxList(vo);
    }

    /**
     * 教师查看全部选题记录
     * @param modelAndView
     * @param vo
     * @return
     */
//    @RequestMapping("/teaTopicList")
//    public ModelAndView teaTopicList(ModelAndView  modelAndView, SelectTopicVo vo) {
//        modelAndView.setViewName("/topicModule/teaTopicList");
//        return selectTopicService.topicList(modelAndView,vo);
//    }
    /**
     * 待审核选题列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/noTopicList")
    public ModelAndView noTopicList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/noTopicList");
        return selectTopicService.topicList(modelAndView,vo);
    }

    /**
     * 选题详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicDetails")
    public ModelAndView topicDetails(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/topicDetails");
        return selectTopicService.topicDetails(modelAndView,vo);
    }

    /****
     * 教师审核选题
     * @param vo
     * @return
     */
    @RequestMapping("/topicAudited")
    @ResponseBody
    public String topicAudited(SelectTopicVo vo) {
        return selectTopicService.topicAudited(vo);
    }

    /***
     * 学生上传题目任务书
     * @param file
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/uploadTaskBook")
    @ResponseBody
    public String uploadTaskBook(@RequestParam("taskFile") MultipartFile file, @RequestParam("id")String id, HttpServletRequest request) {
        return selectTopicService.uploadTaskBook(file,Integer.parseInt(id),request,1);
    }

    /***
     * 学生上传题目开题报告
     * @param file
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/uploadOpeningReport")
    @ResponseBody
    public String uploadOpeningReport(@RequestParam("openingReport") MultipartFile file, @RequestParam("id")String id, HttpServletRequest request) {
        return selectTopicService.uploadTaskBook(file,Integer.parseInt(id),request,2);
    }

    /**
     * 学生上传题目毕业论文
     * @param file
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/uploadDissertation")
    @ResponseBody
    public String uploadDissertation(@RequestParam("dissertation") MultipartFile file, @RequestParam("id")String id, HttpServletRequest request) {
        return selectTopicService.uploadTaskBook(file,Integer.parseInt(id),request,3);
    }


    /***
     * 删除选题记录
     * @param id
     * @return
     */
    @RequestMapping("/topicDel")
    @ResponseBody
    public String topicDel(@RequestParam("id")String id) {
        return selectTopicService.topicDel(Integer.parseInt(id));
    }


    /**
     * 报表统计：成绩统计
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicScoreList")
    public ModelAndView topicScoreList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("CountModule/topicScoreList");
        vo.setTeaAuditState(EnumSubSelectStatus.SUCCESS.getValue());
        return selectTopicService.topicList(modelAndView,vo);
    }

    /**
     * 报表统计：选题记录
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicCountList")
    public ModelAndView topicCountList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("CountModule/topicCountList");
        return selectTopicService.topicList(modelAndView,vo);
    }


    /***
     * 导出选题报表
     * @param request
     * @param response
     * @param vo
     * @return
     * @throws ClassNotFoundException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws ParseException
     * @throws InvocationTargetException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/export")
    @ResponseBody
    public String export(HttpServletRequest request, HttpServletResponse response, SelectTopicVo vo) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {
        String fileName = "选题记录";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset(); //清除buffer缓存
        Map<String, Object> map = new HashMap<String, Object>();
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=GB2312");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = null;
        //导出Excel对象
        workbook = selectTopicService.exportExcelInfo(vo);
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Constant.SUCCESS;
    }



    @RequestMapping("/exportScore")
    @ResponseBody
    public String exportScore(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {
        String fileName = "选题成绩记录";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset(); //清除buffer缓存
        Map<String, Object> map = new HashMap<String, Object>();
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=GB2312");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = null;
        //导出Excel对象
        workbook = selectTopicService.exportExcelScoreInfo();
        OutputStream output;
        checkException(response, workbook);
        return Constant.SUCCESS;
    }

    private void checkException(HttpServletResponse response, XSSFWorkbook workbook) {
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 成绩上传列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicUploadList")
    public ModelAndView topicUploadList(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("scoreModule/scoreUploadList");
        vo.setTeaAuditState(EnumSubSelectStatus.SUCCESS.getValue());
        return selectTopicService.topicList(modelAndView,vo);
    }


    /**
     * 成绩上传初始化
     * @param modelAndView
     * @param vo
     * @return
     */
    @RequestMapping("/topicInitUpload")
    public ModelAndView topicInitUpload(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("scoreModule/scoreUpload");
        return selectTopicService.topicDetails(modelAndView,vo);
    }



    /**
     * 成绩上传初始化
     * @param vo
     * @return
     */
    @RequestMapping("/uploadScore")
    @ResponseBody
    public String uploadScore(SelectTopicVo vo) {

        return selectTopicService.uploadScore(vo);
    }

}


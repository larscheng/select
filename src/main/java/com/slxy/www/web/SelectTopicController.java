package com.slxy.www.web;

import com.slxy.www.common.Constant;
import com.slxy.www.domain.po.SelectUserBase;
import com.slxy.www.domain.vo.SelectTopicVo;
import com.slxy.www.enums.EnumEnOrDis;
import com.slxy.www.enums.EnumSubSelectStatus;
import com.slxy.www.enums.EnumUserType;
import com.slxy.www.enums.EnumYesOrNo;
import com.slxy.www.filter.LoginRequired;
import com.slxy.www.service.SelectTopicService;
import com.slxy.www.service.SelectUserBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@Api(tags = "选题管理", description = "选题模块功能")
@Controller
@RequestMapping("/selectTopic")
public class SelectTopicController {
    @Autowired
    private SelectTopicService selectTopicService;
    @Autowired
    private SelectUserBaseService selectUserBaseService;

    /**
     * 选题信息列表 ，根据vo不同权限不同
     * @param modelAndView
     * @param vo
     * @return
     */

    @ApiOperation(value = "获取选题信息列表", notes = "")
    @RequestMapping(value = "/topicList",method = RequestMethod.GET)
    public ModelAndView stuList(ModelAndView  modelAndView, SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                if (EnumUserType.STUDENT.getValue().equals(userBase.getUserType())){
                    vo.setStuId(userBase.getId());
                }
            }
        }
        modelAndView.setViewName("topicModule/topicList");
        vo.setDelState(EnumYesOrNo.NO.getValue());
        return selectTopicService.topicList(modelAndView,vo);
    }

    @ApiOperation(value = "获取被选列表", notes = "")
    @RequestMapping(value = "/selectedList",method = RequestMethod.GET)
    public ModelAndView selectedList(ModelAndView  modelAndView, SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                vo.setTeaId(userBase.getId());
            }
        }
        modelAndView.setViewName("topicModule/topicSelectedList");
        vo.setDelState(EnumYesOrNo.NO.getValue());
        return selectTopicService.topicList(modelAndView,vo);
    }

    /***
     * 异步生成学生选题信息列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "异步生成学生选题信息列表", notes = "")
    @RequestMapping(value = "/stuTopicAjaxList",method = RequestMethod.POST)
    @ResponseBody
    public String stuTopicAjaxList(SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                if (EnumUserType.TEACHER.getValue().equals(userBase.getUserType())){
                    vo.setTeaId(userBase.getId());
                }else if (EnumUserType.STUDENT.getValue().equals(userBase.getUserType())){
                    vo.setStuId(userBase.getId());
                }
            }
        }
        return selectTopicService.stuTopicAjaxList(vo);
    }


    @ApiOperation(value = "异步生成待审核选题列表", notes = "")
    @RequestMapping(value = "/noTopicAjaxList",method = RequestMethod.POST)
    @ResponseBody
    public String noTopicAjaxList(SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                vo.setTeaId(userBase.getId()).setTeaAuditState(EnumEnOrDis.DISABLED.getValue());
            }
        }
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
    @ApiOperation(value = "待审核选题列表", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/noTopicList",method = RequestMethod.GET)
    public ModelAndView noTopicList(ModelAndView  modelAndView, SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                vo.setTeaId(userBase.getId()).setTeaAuditState(EnumEnOrDis.DISABLED.getValue());
            }
        }
        modelAndView.setViewName("topicModule/noTopicList");
        return selectTopicService.topicList(modelAndView,vo);
    }

    /**
     * 选题详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "选题详情", notes = "")
    @RequestMapping(value = "/topicDetails",method = RequestMethod.GET)
    public ModelAndView topicDetails(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("topicModule/topicDetails");
        return selectTopicService.topicDetails(modelAndView,vo);
    }

    /****
     * 教师审核选题
     * @param vo
     * @return
     */
    @ApiOperation(value = "教师审核选题", notes = "")
    @RequestMapping(value = "/topicAudited",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生上传题目任务书", notes = "")
    @RequestMapping(value = "/uploadTaskBook",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生上传题目开题报告", notes = "")
    @RequestMapping(value = "/uploadOpeningReport",method = RequestMethod.POST)
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
    @ApiOperation(value = "学生上传题目毕业论文", notes = "")
    @RequestMapping(value = "/uploadDissertation",method = RequestMethod.POST)
    @ResponseBody
    public String uploadDissertation(@RequestParam("dissertation") MultipartFile file, @RequestParam("id")String id, HttpServletRequest request) {
        return selectTopicService.uploadTaskBook(file,Integer.parseInt(id),request,3);
    }


    /***
     * 删除选题记录
     * @param id
     * @return
     */
    @ApiOperation(value = "删除选题记录", notes = "假删除")
    @RequestMapping(value = "/topicDel",method = RequestMethod.GET)
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
    @ApiOperation(value = "报表统计：成绩列表获取", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/topicScoreList",method = RequestMethod.GET)
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
    @ApiOperation(value = "报表统计：选题记录获取", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/topicCountList",method = RequestMethod.GET)
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
    @ApiOperation(value = "导出选题记录", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/export",method = RequestMethod.GET)
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


    @ApiOperation(value = "导出成绩记录", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/exportScore",method = RequestMethod.GET)
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
    @ApiOperation(value = "成绩上传列表", notes = "")
//    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/topicUploadList",method = RequestMethod.GET)
    public ModelAndView topicUploadList(ModelAndView  modelAndView, SelectTopicVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!ObjectUtils.isEmpty(session)){
            SelectUserBase userBase = (SelectUserBase) session.getAttribute("sessionUser");
            if (!ObjectUtils.isEmpty(userBase)){
                if (userBase.getUserType().equals(EnumUserType.TEACHER.getValue())){
                    vo.setTeaId(userBase.getId());
                }else if (userBase.getUserType().equals(EnumUserType.STUDENT.getValue())){
                    vo.setStuId(userBase.getId());
                }

            }
        }
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
    @ApiOperation(value = "成绩上传初始化", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/topicInitUpload",method = RequestMethod.GET)
    public ModelAndView topicInitUpload(ModelAndView  modelAndView, SelectTopicVo vo) {
        modelAndView.setViewName("scoreModule/scoreUpload");
        return selectTopicService.topicDetails(modelAndView,vo);
    }



    /**
     * 成绩上传
     * @param vo
     * @return
     */
    @ApiOperation(value = "成绩上传", notes = "")
    @LoginRequired(value = "admTea")
    @RequestMapping(value = "/uploadScore",method = RequestMethod.POST)
    @ResponseBody
    public String uploadScore(SelectTopicVo vo) {

        return selectTopicService.uploadScore(vo);
    }


    @ApiOperation(value = "删除选题", notes = "")
    @RequestMapping(value = "/delTopic",method = RequestMethod.POST)
    @ResponseBody
    public String delTopic(SelectTopicVo vo) {
        return selectTopicService.delTopic(vo);
    }

    /**
     * 成绩批量上传模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "成绩批量上传模板下载", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/scoreFileDown",method = RequestMethod.GET)
    public void stuFileDown(HttpServletRequest request,HttpServletResponse response) throws Exception {
        String fileName= "select_scores.xls";
        selectUserBaseService.down(request,response,fileName);
    }

    /***
     * 批量上传评分
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "批量上传评分", notes = "")
    @LoginRequired(value = "adm")
    @RequestMapping(value = "/scoreUpload", method = RequestMethod.POST)
    @ResponseBody
    public String scoreUpload(HttpServletRequest request) throws IOException {
        return selectTopicService.scoreUpload(request);
    }

}


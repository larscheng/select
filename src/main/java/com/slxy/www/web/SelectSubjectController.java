package com.slxy.www.web;

import com.slxy.www.common.Constant;
import com.slxy.www.domain.vo.SelectSubjectVo;
import com.slxy.www.enums.EnumSubState;
import com.slxy.www.service.SelectSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 *
 * SelectSubject 控制层
 *
 */

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhengql
 * @since 2018-01-25
 */
@Api(tags = "论文管理", description = "论文模块功能")
@Controller
@RequestMapping("/selectSubject")
public class SelectSubjectController {
    @Autowired
    private SelectSubjectService selectSubjectService;

    /**
     * 未审核列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "获取未审核论文", notes = "")
    @RequestMapping(value = "/unSubList",method = RequestMethod.GET)
    public ModelAndView unSubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/unAuditedList");
        vo.setAdmAuditState(EnumSubState.Untreated.getValue());
        return selectSubjectService.subList(modelAndView,vo);
    }

    /**
     * 已审核列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "获取已审核论文", notes = "")
    @RequestMapping(value = "/subList",method = RequestMethod.GET)
    public ModelAndView subList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/auditedList");
        return selectSubjectService.subList(modelAndView,vo);
    }

    /***
     * 历届论文列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "历届论文列表", notes = "")
    @RequestMapping(value = "/subAllList",method = RequestMethod.GET)
    public ModelAndView subAllList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("CountModule/subAllList");
        return selectSubjectService.subList(modelAndView,vo);
    }

    /***
     * 异步生成未审核论文列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "异步生成未审核列表", notes = "")
    @RequestMapping(value = "/unSubListAjax",method = RequestMethod.POST)
    @ResponseBody
    public String unSubListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.Untreated.getValue());
        return selectSubjectService.subListAjax(vo);
    }

    /***
     * 异步生成论文列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "异步生成论文列表", notes = "")
    @RequestMapping(value = "/subListAjax",method = RequestMethod.POST)
    @ResponseBody
    public String subListAjax(SelectSubjectVo vo) {
        return selectSubjectService.subListAjax(vo);
    }


    /**
     * 审核
     * @param vo
     * @return
     */
    @ApiOperation(value = "审核论文", notes = "")
    @RequestMapping(value = "/subAudited",method = RequestMethod.POST)
    @ResponseBody
    public String subAudited(SelectSubjectVo vo) {
        return selectSubjectService.subAudited(vo);
    }

    /***
     * 批量通过
     * @param selectedIDs
     * @return
     */
    @ApiOperation(value = "批量审核通过", notes = "")
    @RequestMapping(value = "/subSuccessAll",method = RequestMethod.POST)
    @ResponseBody
    public String subSuccessAll(Integer[] selectedIDs) {
        return selectSubjectService.subSuccessAll(selectedIDs);
    }


    /**
     * 未审核详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "题目详情（未审核）", notes = "")
    @RequestMapping(value = "/subDetail",method = RequestMethod.GET)
    public ModelAndView subDetail(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/subDetails");
        return selectSubjectService.subDetail(modelAndView,vo);
    }

    /***
     * 已审核详情
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "查看题目详情（已审核）", notes = "")
    @RequestMapping(value = "/subjectDetail",method = RequestMethod.GET)
    public ModelAndView subjectDetail(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/subjectDetails");
        return selectSubjectService.subDetail(modelAndView,vo);
    }



    //****************************************************教师**********************************************************//
    //****************************************************教师**********************************************************//
    //****************************************************教师**********************************************************//

    /**
     * 教师个人题目列表
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "获得我的题目（教师）", notes = "")
    @RequestMapping(value = "/mySubList",method = RequestMethod.GET)
    public ModelAndView mySubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/mySubList");
        return selectSubjectService.mySubList(modelAndView,vo);
    }

    /**
     * 异步生成我的题目列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "异步生成我的题目列表（教师）", notes = "")
    @RequestMapping(value = "/mySubListAjax",method = RequestMethod.POST)
    @ResponseBody
    public String mySubListAjax(SelectSubjectVo vo) {
        return selectSubjectService.mySubListAjax(vo);
    }

    /***
     * 题目添加初始化
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "题目添加初始化", notes = "")
    @RequestMapping(value = "/initSubAdd",method = RequestMethod.GET)
    public ModelAndView initSubAdd(ModelAndView  modelAndView) {
        modelAndView.setViewName("subjectModule/subAdd");
        return selectSubjectService.initSubAdd(modelAndView);
    }

    /**
     * 题目添加
     * @param vo
     * @return
     */
    @ApiOperation(value = "题目添加", notes = "")
    @RequestMapping(value = "/subAdd",method = RequestMethod.POST)
    @ResponseBody
    public String subAdd(@RequestParam("subFile") MultipartFile file, SelectSubjectVo vo, HttpServletRequest request) {
        return selectSubjectService.subAdd(file,vo,request);
    }


    /***
     * 所有通过审核的题目列表
     * @param modelAndView
     * @return
     */
    @ApiOperation(value = "通过审核的题目列表", notes = "")
    @RequestMapping(value = "/optionalList",method = RequestMethod.GET)
    public ModelAndView optionalList(ModelAndView  modelAndView,SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/optionalList");
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.mySubList(modelAndView,vo);
    }


    /**
     * 异步生成 所有通过审核的题目列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "异步获取所有通过审核的题目列表", notes = "")
    @RequestMapping(value = "/optionalListAjax",method = RequestMethod.POST)
    @ResponseBody
    public String optionalListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.mySubListAjax(vo);
    }


    /**
     * 学生模板下载
     * @param request
     * @param response
     * @throws Exception
     */
    @ApiOperation(value = "学生模板下载", notes = "")
    @RequestMapping(value = "/subFileDown",method = RequestMethod.GET)
    public void stuFileDown(HttpServletRequest request,HttpServletResponse response,String fileName) throws Exception {

        selectSubjectService.downSubFile(request,response,fileName);
    }


    //****************************************************stu**********************************************************//
    //****************************************************stu**********************************************************//
    //****************************************************stu**********************************************************//


    /**
     * 学生可见的题目列表 本系、本届
     * @param modelAndView
     * @param vo
     * @return
     */
    @ApiOperation(value = "获取学生可见的题目列表", notes = "本届、本系")
    @RequestMapping(value = "/stuSubList",method = RequestMethod.GET)
    public ModelAndView stuSubList(ModelAndView  modelAndView, SelectSubjectVo vo) {
        modelAndView.setViewName("subjectModule/stuSubList");
        return selectSubjectService.stuSubList(modelAndView,vo);
    }

    /**
     * 异步生成 所有通过审核的题目列表
     * @param vo
     * @return
     */
    @ApiOperation(value = "获取所有通过审核的题目列表", notes = "")
    @RequestMapping(value = "/stuSubListAjax",method = RequestMethod.POST)
    @ResponseBody
    public String stuSubListAjax(SelectSubjectVo vo) {
        vo.setAdmAuditState(EnumSubState.SUCCESS.getValue());
        return selectSubjectService.stuSubListAjax(vo);
    }

    /**
     * 学生选题：添加选题记录，题目选题状态变【审核中】
     * @param vo
     * @return
     */
    @ApiOperation(value = "学生选题", notes = "")
    @RequestMapping(value = "/stuSelect",method = RequestMethod.POST)
    @ResponseBody
    public String stuSelect(SelectSubjectVo vo) {
        return selectSubjectService.stuSelect(vo);
    }

    @ApiOperation(value = "导出论文题目记录", notes = "")
    @RequestMapping(value = "/export",method = RequestMethod.POST)
    @ResponseBody
    public String export(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException, UnsupportedEncodingException {
        String fileName = "论文题目记录";
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
        workbook = selectSubjectService.exportExcelInfo();
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

}


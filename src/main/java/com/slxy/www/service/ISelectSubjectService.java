package com.slxy.www.service;

import com.slxy.www.model.SelectSubject;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-25
 */
public interface ISelectSubjectService extends IService<SelectSubject> {

    ModelAndView subList(ModelAndView modelAndView, SelectSubjectVo vo);

    String subAudited(SelectSubjectVo vo);

    ModelAndView subDetail(ModelAndView modelAndView, SelectSubjectVo vo);

    String subSuccessAll(Integer[] selectedIDs);

    String subListAjax(SelectSubjectVo vo);

    ModelAndView mySubList(ModelAndView modelAndView, SelectSubjectVo vo);

    String mySubListAjax(SelectSubjectVo vo);

    ModelAndView initSubAdd(ModelAndView modelAndView);

    String subAdd(MultipartFile file,SelectSubjectVo vo, HttpServletRequest request);

    ModelAndView stuSubList(ModelAndView modelAndView, SelectSubjectVo vo);

    String stuSubListAjax(SelectSubjectVo vo);

    String stuSelect(SelectSubjectVo vo);

    void downSubFile(HttpServletRequest request, HttpServletResponse response, String fileName);

    XSSFWorkbook exportExcelInfo() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, java.text.ParseException, IllegalAccessException;

}

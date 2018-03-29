package com.slxy.www.service;

import com.slxy.www.model.SelectTopic;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import com.sun.tools.example.debug.expr.ParseException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql123
 * @since 2018-02-04
 */
public interface ISelectTopicService extends IService<SelectTopic> {

    ModelAndView topicList(ModelAndView modelAndView, SelectTopicVo vo);

    String stuTopicAjaxList(SelectTopicVo vo);

    ModelAndView topicDetails(ModelAndView modelAndView, SelectTopicVo vo);

    String topicAudited(SelectTopicVo vo);

    String uploadTaskBook(MultipartFile file,Integer id, HttpServletRequest request,Integer type);

    String topicDel(int i);

    String uploadScore(SelectTopicVo vo);

    XSSFWorkbook exportExcelInfo(SelectTopicVo vo) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, java.text.ParseException, IllegalAccessException;

    XSSFWorkbook exportExcelScoreInfo() throws InvocationTargetException, ClassNotFoundException, IntrospectionException, java.text.ParseException, IllegalAccessException;


}

package com.slxy.www.service;

import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.vo.SelectUserBaseVo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-06
 */
public interface ISelectUserBaseService extends IService<SelectUserBase> {

    ModelAndView userList(ModelAndView modelAndView, SelectUserBaseVo userBaseVo);

    String stuListAjax(SelectUserBaseVo userBaseVo);

    String stuAble(SelectUserBaseVo userBaseVo);

    String stuDelete(SelectUserBaseVo userBaseVo);

    ModelAndView stuInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo);

    String stuUpdate(SelectUserBase userBase);

    String stuAdd(SelectUserBase userBase);

    String stuDeleteAll(Integer[] selectedIDs);

    String teaListAjax(SelectUserBaseVo userBaseVo);

    String teaAble(SelectUserBase userBase);

    ModelAndView teaInitAddAndUpdate(ModelAndView modelAndView, SelectUserBaseVo userBaseVo);

    String teaAdd(SelectUserBase userBase);

    String teaUpdate(SelectUserBase userBase);

    String teaDelete(SelectUserBase userBase);

    String teaDeleteAll(Integer[] selectedIDs);

    String stuUpload(HttpServletRequest request);

    String teaUpload(HttpServletRequest request);

    String initClass(SelectUserBase userBase);

    void down(HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception;

    String admAdd(SelectUserBase userBase);
}

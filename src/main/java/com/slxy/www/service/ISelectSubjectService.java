package com.slxy.www.service;

import com.slxy.www.model.SelectSubject;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.vo.SelectSubjectVo;
import org.springframework.web.servlet.ModelAndView;

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
}

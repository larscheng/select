package com.slxy.www.service;

import com.slxy.www.model.SelectTopic;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.vo.SelectSubjectVo;
import com.slxy.www.model.vo.SelectTopicVo;
import org.springframework.web.servlet.ModelAndView;

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
}

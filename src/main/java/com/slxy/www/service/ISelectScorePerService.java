package com.slxy.www.service;

import com.slxy.www.model.po.SelectScorePer;
import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.vo.SelectSubjectVo;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-29
 */
public interface ISelectScorePerService extends IService<SelectScorePer> {

    ModelAndView scoreList(ModelAndView modelAndView);

    String delScore(int i);

    String delAllScore(Integer[] selectedIDs);

    String updateScore(SelectScorePer selectScorePer);

    String addScore(SelectScorePer selectScorePer);
}

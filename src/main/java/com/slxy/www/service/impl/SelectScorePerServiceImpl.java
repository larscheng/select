package com.slxy.www.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import com.slxy.www.model.po.SelectScorePer;
import com.slxy.www.mapper.SelectScorePerMapper;
import com.slxy.www.service.ISelectScorePerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-03-29
 */
@Service
public class SelectScorePerServiceImpl extends ServiceImpl<SelectScorePerMapper, SelectScorePer> implements ISelectScorePerService {

    /***
     * 成绩比例列表
     * @param modelAndView
     * @return
     */
    @Override
    public ModelAndView scoreList(ModelAndView modelAndView) {
        List<SelectScorePer> selectScorePers = this.selectList(new EntityWrapper<SelectScorePer>());
        modelAndView.addObject("scoreList",selectScorePers);
        return modelAndView;
    }


    @Override
    public String delScore(int i) {
        return this.deleteById(i) ? Constant.SUCCESS : Constant.ERROR;
    }

    @Override
    public String delAllScore(Integer[] selectedIDs) {
        return this.deleteBatchIds(Arrays.asList(selectedIDs))? Constant.SUCCESS:Constant.ERROR;
    }

    @Override
    public String updateScore(SelectScorePer selectScorePer) {
        return this.updateById(selectScorePer) ? Constant.SUCCESS : Constant.ERROR;
    }

    @Override
    public String addScore(SelectScorePer selectScorePer) {
        SelectScorePer scorePer = this.selectOne(new EntityWrapper<>(new SelectScorePer().setScoreName(selectScorePer.getScoreName())));
        if (!ObjectUtils.isEmpty(scorePer)){
            return Constant.SCORE_INSERT_NAME_REPEAT;
        }
        selectScorePer.setGmtCreate(new Date());
        return this.insert(selectScorePer)?Constant.SUCCESS : Constant.ERROR;
    }
}

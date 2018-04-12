package com.slxy.www.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.slxy.www.common.Constant;
import org.springframework.stereotype.Service;

import com.slxy.www.dao.ISelectScorePerMapper;
import com.slxy.www.domain.po.SelectScorePer;
import com.baomidou.framework.service.impl.ServiceImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * SelectScorePer 表数据服务层接口实现类
 *
 */
@Service
public class SelectScorePerService extends  ServiceImpl <ISelectScorePerMapper, SelectScorePer>  {

    /***
     * 成绩比例列表
     * @param modelAndView
     * @return
     */

    public ModelAndView scoreList(ModelAndView modelAndView) {
        List<SelectScorePer> selectScorePers = this.selectList(new EntityWrapper<SelectScorePer>());
        modelAndView.addObject("scoreList",selectScorePers);
        return modelAndView;
    }



    public String delScore(int i) {
        return this.deleteById(i) ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }


    public String delAllScore(Integer[] selectedIDs) {
        return this.deleteBatchIds(Arrays.asList(selectedIDs))? JSONObject.toJSONString(Constant.SUCCESS):JSONObject.toJSONString(Constant.ERROR);
    }


    public String updateScore(SelectScorePer selectScorePer) {
        return this.updateById(selectScorePer) ? JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }


    public String addScore(SelectScorePer selectScorePer) {
        SelectScorePer scorePer = this.selectOne(new EntityWrapper<>(new SelectScorePer().setScoreName(selectScorePer.getScoreName())));
        if (!ObjectUtils.isEmpty(scorePer)){
            return JSONObject.toJSONString(Constant.SCORE_INSERT_NAME_REPEAT);
        }
        selectScorePer.setGmtCreate(new Date());
        return this.insert(selectScorePer)?JSONObject.toJSONString(Constant.SUCCESS) : JSONObject.toJSONString(Constant.ERROR);
    }
}

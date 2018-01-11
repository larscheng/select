package com.slxy.www.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.slxy.www.model.SelectUserBaseVo;
import com.slxy.www.service.ISelectUserBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-06
 */
@Service
public class SelectUserBaseServiceImpl extends ServiceImpl<SelectUserBaseMapper, SelectUserBase> implements ISelectUserBaseService{
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;


    @Override
    public ModelAndView userList(ModelAndView  modelAndView ,SelectUserBaseVo userBaseVo) {
        modelAndView.setViewName("test");
        SelectUserBaseVo vo = new SelectUserBaseVo();
        Page<SelectUserBase> page = new Page<>(userBaseVo.getPage(),vo.getPageSize());
        List<SelectUserBase> userList = selectUserBaseMapper.getUserByPage(page,vo);
        page.setRecords(userList);
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("page",page);
        return modelAndView;
    }

}

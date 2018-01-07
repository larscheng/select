package com.slxy.www.service.impl;

import com.slxy.www.mapper.SelectUserBaseMapper;
import com.slxy.www.model.SelectUserBase;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SelectUserBaseServiceImpl extends ServiceImpl<SelectUserBaseMapper, SelectUserBase>{
    @Autowired
    private SelectUserBaseMapper selectUserBaseMapper;
    public List<SelectUserBase> selectUserBaseList(){
        return selectUserBaseMapper.selectUserBaseList();
    }
}

package com.slxy.www.service.impl;

import com.slxy.www.model.SelectDepartment;
import com.slxy.www.mapper.SelectDepartmentMapper;
import com.slxy.www.service.ISelectDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengql123
 * @since 2018-01-07
 */
@Service
public class SelectDepartmentServiceImpl extends ServiceImpl<SelectDepartmentMapper, SelectDepartment> implements ISelectDepartmentService {

    @Autowired
    private SelectDepartmentMapper selectDepartmentMapper;
    @Override
    public void userDisable(SelectDepartment selectDepartment) {
        selectDepartmentMapper.userDisable(selectDepartment);
    }
}

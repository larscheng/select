package com.slxy.www.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.slxy.www.model.SelectDepartment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.slxy.www.model.SelectUserBase;
import com.slxy.www.model.vo.SelectDepartmentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
public interface SelectDepartmentMapper extends BaseMapper<SelectDepartment> {

    void userDisable(SelectDepartment selectDepartment);

    List<SelectDepartment> getDepByPage(Page<SelectDepartment> page, SelectDepartmentVo vo);

    List<SelectDepartment> selectTeaDep();
}

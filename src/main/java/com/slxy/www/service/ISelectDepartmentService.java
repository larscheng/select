package com.slxy.www.service;

import com.baomidou.mybatisplus.service.IService;
import com.slxy.www.model.SelectDepartment;
import com.slxy.www.model.vo.SelectDepartmentVo;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhengql
 * @since 2018-01-07
 */
public interface ISelectDepartmentService extends IService<SelectDepartment> {

    ModelAndView depList(ModelAndView modelAndView, SelectDepartmentVo vo) ;

    String depAdd(SelectDepartment selectDepartment);

    String depDisableAndUpdate(SelectDepartment selectDepartment);

    String depDelete(SelectDepartment selectDepartment);

    String depDeleteAll(Integer[] selectedIDs);
}

package com.slxy.www.common;

/**
 * @author zhengql
 * @description 常量类
 * @className Constant
 * @create 2018年01月13日  19:28
 */
public class Constant {
    /**
     * 消息格式： 模块_操作_结果
     */
    //ajax 消息返回
    public static final String SUCCESS = "OK";
    public static final String ERROR = "操作失败！";


    //系别
    public static final String DEP_EXIST = "该系别已存在！";
    public static final String DEP_DISABLE_ERROR = "该系别下有专业正在启用中，不可禁用！";
    public static final String DEP_DELETE_ERROR = "该系别下有专业正在启用中，不可删除!";
    public static final String DEP_DELETE_ERROR_NAME = "该系别下有专业正在启用中，不可删除！系别名：";


    //专业
    public static final String MAJ_ABLE_ERROR = "启用失败！原因：该专业所属系别已被禁用";
    public static final String MAJ_NAME_EXIST = "该专业已存在！";


}


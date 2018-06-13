package com.slxy.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumProControl
 * @Description         : 流程枚举
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumProControl {
    subAdd(1, "上传题目"),
    auditSub(2, "审核论文"),
    select(3, "学生选题"),
    teaAudit(4, "教师审核选题"),
    fileUp(5, "学生上传文档"),
    scoreCount(6, "成绩评定"),
    score(7, "成绩查询"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumProControl> pool = new HashMap<Integer, EnumProControl>();
    static {
        for (EnumProControl et : EnumProControl.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumProControl type : EnumProControl.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumProControl indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumProControl(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}

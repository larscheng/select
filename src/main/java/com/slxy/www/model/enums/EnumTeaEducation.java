package com.slxy.www.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumTeaEducation
 * @Description         : 教师职称枚举类
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumTeaEducation {
    DG(1, "博士"),
    MD(2, "硕士"),
    BA(3, "学士"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumTeaEducation> pool = new HashMap<Integer, EnumTeaEducation>();
    static {
        for (EnumTeaEducation et : EnumTeaEducation.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumTeaEducation type : EnumTeaEducation.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }
    public static Map<String,Integer> toMap2() {
        Map<String,Integer> enumDataMap = new HashMap<String,Integer>();
        for (EnumTeaEducation type : EnumTeaEducation.values()) {
            enumDataMap.put( type.getName(),type.getValue());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumTeaEducation indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumTeaEducation(Integer value, String name) {
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

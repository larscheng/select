package com.slxy.www.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumUserSex
 * @Description         : 用户性别枚举类
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumUserSex {
    male(1, "男"),
    female(2, "女"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumUserSex> pool = new HashMap<Integer, EnumUserSex>();
    static {
        for (EnumUserSex et : EnumUserSex.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumUserSex type : EnumUserSex.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumUserSex indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumUserSex(Integer value, String name) {
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

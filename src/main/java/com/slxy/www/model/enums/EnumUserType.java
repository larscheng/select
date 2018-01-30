package com.slxy.www.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumUserType
 * @Description         : 用户类型枚举类
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumUserType {
    ADMIN(1, "管理员"),
    TEACHER(2, "教师"),
    STUDENT(3, "学生"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumUserType> pool = new HashMap<Integer, EnumUserType>();
    static {
        for (EnumUserType et : EnumUserType.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumUserType type : EnumUserType.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumUserType indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumUserType(Integer value, String name) {
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

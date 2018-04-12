package com.slxy.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumSubType
 * @Description         : 题目类型，1应用型，2理论性'
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumSubType {
    APPLIED(1, "应用型"),
    THEORY(2, "理论性"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumSubType> pool = new HashMap<Integer, EnumSubType>();
    static {
        for (EnumSubType et : EnumSubType.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumSubType type : EnumSubType.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumSubType indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumSubType(Integer value, String name) {
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

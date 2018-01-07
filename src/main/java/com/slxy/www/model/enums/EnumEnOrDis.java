package com.slxy.www.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumYesOrNo
 * @Description         : 公用是否枚举
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumEnOrDis {
    DISABLED(0, "禁用"),
    ENABLED(1, "启用"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumEnOrDis> pool = new HashMap<Integer, EnumEnOrDis>();
    static {
        for (EnumEnOrDis et : EnumEnOrDis.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumEnOrDis type : EnumEnOrDis.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumEnOrDis indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumEnOrDis(Integer value, String name) {
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

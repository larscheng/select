package com.slxy.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumYesOrNo
 * @Description         : 公用是否枚举
 * @Author              : Jason@senthink.com
 * @CreationDate        : 2015年9月18日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumYesOrNo {
	NO(0, "否"),
	YES(1, "是"),
	;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumYesOrNo> pool = new HashMap<Integer, EnumYesOrNo>();
    static {
        for (EnumYesOrNo et : EnumYesOrNo.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumYesOrNo type : EnumYesOrNo.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumYesOrNo indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumYesOrNo(Integer value, String name) {
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

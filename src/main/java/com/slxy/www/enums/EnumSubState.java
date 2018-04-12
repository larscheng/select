package com.slxy.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumSubState
 * @Description         : 论文状态枚举 '审核状态 0未处理，1审核不通过，2审核通过',
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumSubState {
    Untreated(0, "未处理"),
    FAIL(1, "审核不通过"),
    SUCCESS(2, "审核通过"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumSubState> pool = new HashMap<Integer, EnumSubState>();
    static {
        for (EnumSubState et : EnumSubState.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumSubState type : EnumSubState.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumSubState indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumSubState(Integer value, String name) {
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

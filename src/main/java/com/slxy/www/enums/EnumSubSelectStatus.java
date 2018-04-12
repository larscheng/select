package com.slxy.www.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName           : EnumSubSelectStatus
 * @Description         : 论文题目选题状态 0未选 1审核中 2已选,
 * @Author              : zhengql@senthink.com
 * @CreationDate        : 2018年1月7日 下午2:24:25
 * @Version             : v0.0.1
 *
 */
public enum EnumSubSelectStatus {
    Untreated(0, "未选"),
    FAIL(1, "正在审核"),
    SUCCESS(2, "已被选"),
    ;

    private Integer      value;

    private String      name;

    /**
     * 全局索引池
     */
    private static Map<Integer, EnumSubSelectStatus> pool = new HashMap<Integer, EnumSubSelectStatus>();
    static {
        for (EnumSubSelectStatus et : EnumSubSelectStatus.values()) {
            pool.put(et.value, et);
        }
    }

    public static Map<Integer, String> toMap() {
        Map<Integer, String> enumDataMap = new HashMap<Integer, String>();
        for (EnumSubSelectStatus type : EnumSubSelectStatus.values()) {
            enumDataMap.put(type.getValue(), type.getName());
        }
        return enumDataMap;
    }

    /**
     * 根据内容索引
     * @param value
     * @return
     */
    public static EnumSubSelectStatus indexByValue(Integer value) {
        return pool.get(value);
    }

    private EnumSubSelectStatus(Integer value, String name) {
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

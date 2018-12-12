package com.panda.entity.enums;

/**
 * 淘宝评价等级枚举类
 *
 * @author panda
 * @date 2018/11/10
 */
public enum  TaobaoCommentLevelEnum {

    GOOD(1, "好评"),
    NORMAL(0, "中评"),
    BAD(-1, "差评");

    private int key;

    private String value;

    TaobaoCommentLevelEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(int key) {
        String value = null;
        for (TaobaoCommentLevelEnum enumObject : TaobaoCommentLevelEnum.values()) {
            if (key == enumObject.getKey()) {
                value = enumObject.getValue();
                break;
            }
        }
        return value;
    }
}

package com.test.mongodbtest.enums;

public enum SexEnum {
    MALE(1,"男"),
    FEMALE(0, "女");

    private Integer index;
    private String desc;

    public Integer getIndex() {
        return index;
    }

    public String getDesc() {
        return desc;
    }

    SexEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }


}

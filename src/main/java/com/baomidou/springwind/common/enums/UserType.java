package com.baomidou.springwind.common.enums;

import com.baomidou.framework.common.IEnum;

public enum UserType implements IEnum {
    MEMBER(2, "普通用户"), ADMIN(1, "管理员"), SUPER(3, "超级管理员");

    private final int key;
    private final String desc;

    UserType(final int key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String desc() {
        return this.desc;
    }

}

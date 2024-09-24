package com.buercorp.wangyu.model.enums;

public enum CartStatus {

    ONLINE("ONLINE"),
    CHECKOUT("CHECKOUT");

    private String desc;
    public String getDesc() {
        return this.desc;
    }

    private CartStatus(String desc) {
        this.desc = desc;
    }
}

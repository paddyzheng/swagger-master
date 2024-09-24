package com.buercorp.wangyu.model.enums;

public enum Category {

    IT("2001"),
    FOOD("2002");

    private String id;
    public String getId() {
        return this.id;
    }

    private Category(String id) {
        this.id = id;
    }
}

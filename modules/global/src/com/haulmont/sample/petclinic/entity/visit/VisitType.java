package com.haulmont.sample.petclinic.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitType implements EnumClass<String> {

    VISIT("VISIT", 1),
    REGULAR_CHECKUP("REGULAR_CHECKUP",2),
    SURGERY("SURGERY", 3);

    private String id;
    private int code;

    VisitType(String value, int code) {
        this.id = value;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    public static VisitType fromId(String id) {
        for (VisitType at : VisitType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
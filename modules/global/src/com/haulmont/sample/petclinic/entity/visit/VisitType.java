package com.haulmont.sample.petclinic.entity.visit;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VisitType implements EnumClass<String> {

    VISIT("VISIT", 1, false),
    REGULAR_CHECKUP("REGULAR_CHECKUP",2, true),
    SURGERY("SURGERY", 3, false);

    private final boolean toBePayedUpfront;
    private final String id;
    private final int code;

    VisitType(String value, int code, boolean toBePayedUpfront) {
        this.id = value;
        this.code = code;
        this.toBePayedUpfront = toBePayedUpfront;
    }

    public String getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public boolean isToBePayedUpfront() {
        return toBePayedUpfront;
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
package com.trading.enums;

public enum OPERATION_ENUM {
    NEW(1), AMEND(2), CANCEL(3);

    OPERATION_ENUM(Integer operation) {
        this.operation = operation;
    }

    private Integer operation;

    public Integer getOperation() {
        return operation;
    }
}

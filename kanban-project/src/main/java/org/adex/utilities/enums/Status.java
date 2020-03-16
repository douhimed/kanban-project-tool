package org.adex.utilities.enums;

public enum Status {

    STAGING(0, "STAGING"), DONE(2, "DONE"), PROGRESS(1, "IN_PROGRESS"), CANCLED(-1, "CANCLED");


    private final int code;
    private final String status;

    Status(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    
}

package com.example.demo.exception;

public enum ErrorCode {

    USER_EXISTED(1001,"user existed"),
    USER_NOTFOUND(1002,"user not founded"),
    NAME_INVALIDED(1003,"user name at least 5 character"),
    USER_PASSWORD_UNVALID(1004,"user password at least 8 character"),
    USER_NAME_UNVALID(1004,"username at least 5 character"),
    UNCATEGORIZED_EXCEPTION(9999,"uncategorized error");



    private int code;
    private String message;


    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

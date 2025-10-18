package com.example.demo.exception;

public enum ErrorCode {

    USER_EXISTED(1001,"username existed"),
    USER_NOTFOUND(1002,"user not founded"),
    USER_PASSWORD_UNVALIDED(1004,"user password at least 8 character"),
    USER_NAME_UNVALIDED(1004,"username at least 5 character"),
    UNCATEGORIZED_EXCEPTION(9999,"uncategorized error"),
    NOT_AUTHENTICATED(1005,"Not Authenticated"),
    EMAIL_EXISTED(1006,"Email existed"),
    EMAIL_UNVALIDED(1007,"Email unvalided")
    ;



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

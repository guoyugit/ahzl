package com.ahzl.enums;

public enum SendStateEnum {
    NOT_SEND("0", "未发送"),
    SEND_SUCCESS("1", "发送成功"),
    SEND_FAIL("2", "发送失败"),
    RETURN_RESPONSE("3", "已返回结果");

    private String code;
    private String desc;

    private SendStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SendStateEnum codeOf(String code) {
        byte var2 = -1;
        switch (code.hashCode()) {
            case 48:
                if (code.equals("0")) {
                    var2 = 0;
                }
                break;
            case 49:
                if (code.equals("1")) {
                    var2 = 1;
                }
                break;
            case 50:
                if (code.equals("2")) {
                    var2 = 2;
                }
                break;
            case 51:
                if (code.equals("3")) {
                    var2 = 3;
                }
        }

        switch (var2) {
            case 0:
                return NOT_SEND;
            case 1:
                return SEND_SUCCESS;
            case 2:
                return SEND_FAIL;
            case 3:
                return RETURN_RESPONSE;
            default:
                return null;
        }
    }

    public static SendStateEnum descOf(String desc) {
        byte var2 = -1;
        switch (desc.hashCode()) {
            case 26082042:
                if (desc.equals("未发送")) {
                    var2 = 0;
                }
                break;
            case 675452127:
                if (desc.equals("发送成功")) {
                    var2 = 1;
                }
                break;
            case 675396708:
                if (desc.equals("发送失败")) {
                    var2 = 2;
                }
                break;
            case 1855151909:
                if (desc.equals("已返回结果")) {
                    var2 = 3;
                }
        }

        switch (var2) {
            case 0:
                return NOT_SEND;
            case 1:
                return SEND_SUCCESS;
            case 2:
                return SEND_FAIL;
            case 3:
                return RETURN_RESPONSE;
            default:
                return null;
        }
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}

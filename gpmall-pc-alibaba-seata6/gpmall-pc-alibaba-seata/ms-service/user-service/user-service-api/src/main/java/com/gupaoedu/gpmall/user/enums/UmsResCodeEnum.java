package com.gupaoedu.gpmall.user.enums;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
public enum UmsResCodeEnum {

    //004000~004030 为系统公共异常
    SYS_SUCCESS("000000", "成功"),
    SYS_PARAM_NOT_RIGHT("004001", "传入参数值不合法"),
    SYS_PARAM_NOT_NULL("004002", "必要参数不能为空"),
    UPDATE_DATA_FAIL("004003","更新数据失败"),
    SYS_UPDATE_DATA_FAIL("004004", "更新数据失败"),
    QUERY_DATA_NOT_EXIST("004005", "查询数据不存在"),
    REQUEST_CHECK_FAILURE("004006", "请求数据校验失败"),
    STATUS_NOT_RIGHT("004007", "数据状态校验不通过"),
    REQUEST_DATA_NOT_EXIST("004008", "请求提交的数据不存在"),
    USERORPASSWORD_ERRROR("004009","用户名或密码不正确"),
    TOKEN_VERIFY_FAILURE("004010","访问Token验证失败"),
    SYSTEM_EXCEPTION("004999","系统繁忙，请稍候重试");

    private final String code;
    private final String msg;

    UmsResCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 用来描述详细错误信息
     * @param detailedDesc
     * @return
     */
    public String getMsg(String detailedDesc) {
        return msg + ":" + detailedDesc;
    }
}

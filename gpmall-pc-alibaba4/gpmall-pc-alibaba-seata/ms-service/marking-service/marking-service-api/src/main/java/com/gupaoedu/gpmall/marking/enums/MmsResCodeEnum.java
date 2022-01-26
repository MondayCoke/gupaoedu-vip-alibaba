package com.gupaoedu.gpmall.marking.enums;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/
public enum MmsResCodeEnum {

    //004000~004030 为系统公共异常
    SYS_SUCCESS("000000", "成功"),
    SYS_PARAM_NOT_RIGHT("006001", "传入参数值不合法"),
    SYS_PARAM_NOT_NULL("006002", "必要参数不能为空"),
    UPDATE_DATA_FAIL("006003","更新数据失败"),
    SYS_UPDATE_DATA_FAIL("006004", "更新数据失败"),
    QUERY_DATA_NOT_EXIST("006005", "查询数据不存在"),
    REQUEST_CHECK_FAILURE("006006", "请求数据校验失败"),
    STATUS_NOT_RIGHT("006007", "数据状态校验不通过"),
    REQUEST_DATA_NOT_EXIST("006008", "请求提交的数据不存在"),

    LOTTER_NOT_EXIST("006009","指定抽奖活动不存在"),
    LOTTER_FINISH("006010","活动已结束"),
    LOTTER_REPO_NOT_ENOUGHT("006011","当前奖品库存不足"),
    LOTTER_ITEM_NOT_INITIAL("006012","奖项数据未初始化"),
    LOTTER_DRAWING("006013","上一次抽奖还未结束"),

    NOT_ENOUGH_LOTTER_CHANCE("00614","没有抽奖机会"),


    SYSTEM_EXCEPTION("006999","系统繁忙，请稍候重试");

    private final String code;
    private final String msg;

    MmsResCodeEnum(String code, String msg) {
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

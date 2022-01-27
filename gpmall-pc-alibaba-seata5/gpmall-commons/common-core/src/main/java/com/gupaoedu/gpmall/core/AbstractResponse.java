package com.gupaoedu.gpmall.core;

import lombok.Data;

import java.io.Serializable;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * https://ke.gupaoedu.cn
 **/

@Data
public abstract class AbstractResponse implements Serializable {
    private String code;
    private String msg;
}
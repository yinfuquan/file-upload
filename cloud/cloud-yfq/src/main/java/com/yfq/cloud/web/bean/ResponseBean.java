package com.yfq.cloud.web.bean;

import lombok.Data;

@Data
@lombok.experimental.Accessors(chain = true)
public class ResponseBean {

    private static final long serialVersionUID = -5883989358916182252L;

    public static final int OK = 0;
    public static final int ERROR = 500;

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    //签名
    private String signature;

    public ResponseBean() {}

    public ResponseBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

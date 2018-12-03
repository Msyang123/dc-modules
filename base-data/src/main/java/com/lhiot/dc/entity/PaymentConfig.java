package com.lhiot.dc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.entity.type.PayPlatform;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangfeng created in 2018/9/22 10:18
 **/
@Data
@ApiModel
public class PaymentConfig {
    @ApiModelProperty(notes = "配置Id", dataType = "Long")
    private Long id;

    /**
     * 配置名称
     */
    @ApiModelProperty(notes = "配置名称", dataType = "String")
    private String configName;

    /**
     * 应用ID
     */
    @ApiModelProperty(notes = "应用ID", dataType = "String")
    private String appId;

    /**
     * 应用密钥
     */
    @ApiModelProperty(notes = "应用密钥", dataType = "String")
    private String appSecretKey;

    /**
     * 商户ID
     */
    @ApiModelProperty(notes = " 商户ID", dataType = "String")
    private String merchantId;

    /**
     * 商户密钥（微信有，支付宝使用的sdk不需要传）
     */
    @ApiModelProperty(notes = "商户密钥", dataType = "String")
    private String merchantSecretKey;

    /**
     * 第三方密钥（支付宝私钥 / 微信pkcs12文件URL）
     */
    @ApiModelProperty(notes = "第三方密钥（支付宝私钥 / 微信pkcs12文件URL）", dataType = "String")
    private String thirdPartyKey;

    /**
     * 支付完成 - 回调地址
     */
    @ApiModelProperty(notes = "支付完成 - 回调地址", dataType = "String")
    private String payedNotifyUrl;

    /**
     * 退款 - 回调地址
     */
    @ApiModelProperty(notes = "退款 - 回调地址", dataType = "String")
    private String refundNotifyUrl;

    /**
     * 取消支付 - 回调地址
     */
    @ApiModelProperty(notes = "取消支付 - 回调地址", dataType = "String")
    private String cancelNotifyUrl;

    /**
     * 支付平台
     */
    @ApiModelProperty(notes = "支付平台", dataType = "PayPlatform")
    private PayPlatform platform;

    /**
     * 备注
     */
    @ApiModelProperty(notes = "备注", dataType = "String")
    private String remark;

    @JsonIgnore
    @ApiModelProperty(value = "当前页,默认值1")
    private Long page = 1L;

    /**
     * 传入-1可不分页
     */
    @JsonIgnore
    @ApiModelProperty(value = "每页显示条数,默认值10")
    private Long rows = 10L;
}

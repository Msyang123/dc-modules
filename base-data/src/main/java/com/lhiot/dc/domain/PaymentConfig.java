package com.lhiot.dc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhiot.dc.domain.type.PayPlatformType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zhangfeng created in 2018/9/22 10:18
 **/
@Data
@ApiModel
public class PaymentConfig {

    private Long id;

    /**
     *支付商户名称简称
     */
    @ApiModelProperty(notes = "支付商户名称简称", dataType = "String")
    private String alias;

    /**
     *微信支付APPID/支付宝APPID
     */
    @ApiModelProperty(notes = "微信支付APPID/支付宝APPID", dataType = "String")
    private String partnerId;

    /**
     *微信支付秘钥/支付宝公钥
     */
    @ApiModelProperty(notes = "微信支付秘钥/支付宝公钥", dataType = "String")
    private String partnerKey;

    /**
     *支付宝私钥
     */
    @ApiModelProperty(notes = "支付宝私钥", dataType = "String")
    private String privateKey;

    /**
     *微信退款签名包
     */
    @ApiModelProperty(notes = "微信退款签名包", dataType = "String")
    private String pkcs12Url;

    /**
     *支付宝商户帐号
     */
    @ApiModelProperty(notes = "支付宝商户帐号", dataType = "String")
    private String aliSellerId;

    /**
     *支付平台
     */
    @ApiModelProperty(notes = "支付平台", dataType = "PayPlatformType")
    private PayPlatformType payPlatformType;

    /**
     *备注
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

package com.lhiot.dc.base.model;

import com.lhiot.dc.base.model.type.PayPlatFormType;
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
    @ApiModelProperty(value = "支付商户名称简称", dataType = "String")
    private String paymentName;

    /**
     *微信支付APPID/支付宝APPID
     */
    @ApiModelProperty(value = "微信支付APPID/支付宝APPID", dataType = "String")
    private String partnerId;

    /**
     *微信支付秘钥/支付宝公钥
     */
    @ApiModelProperty(value = "微信支付秘钥/支付宝公钥", dataType = "String")
    private String partnerKey;

    /**
     *支付宝私钥
     */
    @ApiModelProperty(value = "支付宝私钥", dataType = "String")
    private String privateKey;

    /**
     *微信退款签名包
     */
    @ApiModelProperty(value = "微信退款签名包", dataType = "String")
    private String pkcs12Url;

    /**
     *支付宝商户帐号
     */
    @ApiModelProperty(value = "支付宝商户帐号", dataType = "String")
    private String aliSellerId;

    /**
     *支付平台
     */
    @ApiModelProperty(value = "支付平台", dataType = "PayPlatFormType")
    private PayPlatFormType payPlatformType;

    /**
     *备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;
}

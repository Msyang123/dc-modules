package com.lhiot.dc.base.api;

import com.lhiot.dc.base.model.PaymentConfig;
import com.lhiot.dc.base.service.PaymentConfigService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @Author zhangfeng created in 2018/9/22 10:11
 **/
@RestController
@Slf4j
@RequestMapping("/payment/config")
public class PaymentConfigApi {

    private PaymentConfigService paymentConfigService;

    public PaymentConfigApi(PaymentConfigService paymentConfigService) {
        this.paymentConfigService = paymentConfigService;
    }

    @ApiOperation("根据支付商户名称简称查询支付配置信息")
    @ApiImplicitParam(paramType = "query",name = "paymentName",value = "支付商户名称简称",dataType = "String",required = true)
    @GetMapping("/by-name")
    public ResponseEntity findByName(@RequestParam("paymentName") String name){
       PaymentConfig paymentConfig =  paymentConfigService.findByName(name);
       if (Objects.isNull(paymentConfig)){
           return ResponseEntity.badRequest().body("没有该名称的配置信息！");
       }
       return ResponseEntity.ok().body(paymentConfig);
    }
}

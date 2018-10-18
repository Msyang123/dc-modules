package com.lhiot.dc.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.domain.PaymentConfig;
import com.lhiot.dc.service.PaymentConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author zhangfeng created in 2018/9/22 10:11
 **/
@RestController
@Slf4j
@Api(description = "支付签名配置接口")
public class PaymentConfigApi {

    private PaymentConfigService paymentConfigService;

    public PaymentConfigApi(PaymentConfigService paymentConfigService) {
        this.paymentConfigService = paymentConfigService;
    }

    @ApiOperation("根据支付商户名称简称查询支付配置信息")
    @ApiImplicitParam(paramType = "query", name = "name", value = "支付商户名称简称", dataType = "String", required = true)
    @GetMapping("/payment-config")
    public ResponseEntity findByName(@RequestParam("name") String name) {
        PaymentConfig paymentConfig = paymentConfigService.findByName(name);
        if (Objects.isNull(paymentConfig)) {
            return ResponseEntity.badRequest().body("没有该名称的配置信息！");
        }
        return ResponseEntity.ok().body(paymentConfig);
    }

    @ApiOperation("查询所有支付配置信息")
    @GetMapping("/all")
    public ResponseEntity all() {
        List<PaymentConfig> paymentConfigList = paymentConfigService.findAll();
        if (Objects.isNull(paymentConfigList) || paymentConfigList.size() == 0) {
            return ResponseEntity.badRequest().body("没有找到配置信息！");
        }
        return ResponseEntity.ok().body(Multiple.of(paymentConfigList));
    }
}

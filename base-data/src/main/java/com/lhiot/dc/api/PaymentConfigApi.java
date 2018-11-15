package com.lhiot.dc.api;

import com.leon.microx.web.result.Multiple;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.PaymentConfig;
import com.lhiot.dc.mapper.PaymentConfigMapper;
import com.lhiot.dc.service.PaymentConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author zhangfeng created in 2018/9/22 10:11
 **/
@RestController
@Slf4j
@Api("支付签名配置接口")
public class PaymentConfigApi {

    private PaymentConfigService paymentConfigService;
    private PaymentConfigMapper paymentConfigMapper;

    public PaymentConfigApi(PaymentConfigService paymentConfigService, PaymentConfigMapper paymentConfigMapper) {
        this.paymentConfigService = paymentConfigService;
        this.paymentConfigMapper = paymentConfigMapper;
    }

    @ApiOperation("添加支付配置信息")
    @PostMapping("/payment-config")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody PaymentConfig config) {
        Tips tips = paymentConfigService.addConfig(config);
        if (tips.err()) {
            return ResponseEntity.badRequest().body("此名称的配置已存在！");
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改支付配置信息")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "支付配置Id", required = true, dataType = "Long")
    @ApiHideBodyProperty("id")
    @PutMapping("payment-config/{id}")
    public ResponseEntity updateConfig(@PathVariable("id") Long id, @RequestBody PaymentConfig config) {
        config.setId(id);
        if (paymentConfigMapper.update(config) < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "根据支付商户名称简称查询支付配置信息", response = PaymentConfig.class)
    @ApiImplicitParam(paramType = "query", name = "name", value = "支付商户名称简称", dataType = "String", required = true)
    @GetMapping("/payment-config")
    public ResponseEntity findByName(@RequestParam("name") String name) {
        PaymentConfig paymentConfig = paymentConfigMapper.selectByName(name);
        if (Objects.isNull(paymentConfig)) {
            return ResponseEntity.badRequest().body("没有该名称的配置信息！");
        }
        return ResponseEntity.ok().body(paymentConfig);
    }

    @ApiOperation(value = "根据ID查询支付配置信息", response = PaymentConfig.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "主键Id", dataType = "Long", required = true)
    @GetMapping("/payment-config/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        PaymentConfig config = paymentConfigMapper.selectById(id);
        if (Objects.isNull(config)) {
            return ResponseEntity.badRequest().body("没有查找到改配置信息");
        }
        return ResponseEntity.ok(config);
    }

    @ApiOperation(value = "查询所有支付配置信息", response = PaymentConfig.class, responseContainer = "Set")
    @GetMapping("/payment-config/all")
    public ResponseEntity all() {
        List<PaymentConfig> paymentConfigList = paymentConfigMapper.selectAll();
        if (CollectionUtils.isEmpty(paymentConfigList)) {
            return ResponseEntity.badRequest().body("没有找到配置信息！");
        }
        return ResponseEntity.ok().body(Multiple.of(paymentConfigList));
    }
}

package com.lhiot.dc.data.api;

import com.lhiot.dc.data.domain.PaymentSign;
import com.lhiot.dc.data.service.PaymentSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lhiot.dc.data.common.PagerResultObject;

/**
* Description:支付签名信息接口类
* @author yijun
* @date 2018/09/20
*/
@Api(description = "支付签名信息接口")
@Slf4j
@RestController
@RequestMapping("/payment-sign")
public class PaymentSignApi {

    private final PaymentSignService paymentSignService;

    @Autowired
    public PaymentSignApi(PaymentSignService paymentSignService) {
        this.paymentSignService = paymentSignService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加支付签名信息")
    @ApiImplicitParam(paramType = "body", name = "paymentSign", value = "要添加的支付签名信息", required = true, dataType = "PaymentSign")
    public ResponseEntity<Integer> create(@RequestBody PaymentSign paymentSign) {
        log.debug("添加支付签名信息\t param:{}",paymentSign);
        
        return ResponseEntity.ok(paymentSignService.create(paymentSign));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新支付签名信息")
    @ApiImplicitParam(paramType = "body", name = "paymentSign", value = "要更新的支付签名信息", required = true, dataType = "PaymentSign")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id,@RequestBody PaymentSign paymentSign) {
        log.debug("根据id更新支付签名信息\t id:{} param:{}",id,paymentSign);
        paymentSign.setId(id);
        
        return ResponseEntity.ok(paymentSignService.updateById(paymentSign));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除支付签名信息")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除支付签名信息的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除支付签名信息\t param:{}",ids);
        
        return ResponseEntity.ok(paymentSignService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询支付签名信息", notes = "根据id查询支付签名信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentSign> findPaymentSign(@PathVariable("id") Long id) {

        return ResponseEntity.ok(paymentSignService.selectById(id));
    }

    @ApiOperation(value = "根据支付商户名称简称查询支付签名信息", notes = "根据支付商户名称简称查询支付签名信息")
    @ApiImplicitParam(paramType = "path", name = "paymentName", value = "支付商户名称简称", required = true, dataType = "String")
    @GetMapping("/by-name/{paymentName}")
    public ResponseEntity<PaymentSign> findPaymentSignByName(@PathVariable("paymentName") String paymentName) {

        return ResponseEntity.ok(paymentSignService.selectByName(paymentName));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询支付签名信息分页列表")
    public ResponseEntity<PagerResultObject<PaymentSign>> pageQuery(PaymentSign paymentSign){
        log.debug("查询支付签名信息分页列表\t param:{}",paymentSign);
        
        return ResponseEntity.ok(paymentSignService.pageList(paymentSign));
    }
    
}

package com.lhiot.dc.base.service;

import com.lhiot.dc.base.mapper.PaymentConfigMapper;
import com.lhiot.dc.base.model.PaymentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author zhangfeng created in 2018/9/22 10:13
 **/
@Service
@Slf4j
@Transactional
public class PaymentConfigService {

    private PaymentConfigMapper paymentConfigMapper;

    public PaymentConfigService(PaymentConfigMapper paymentConfigMapper) {
        this.paymentConfigMapper = paymentConfigMapper;
    }

    @Nullable
    public PaymentConfig findByName(String name){
       return paymentConfigMapper.findByName(name);
    }
}

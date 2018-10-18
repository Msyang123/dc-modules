package com.lhiot.dc.service;

import com.lhiot.dc.mapper.PaymentConfigMapper;
import com.lhiot.dc.domain.PaymentConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
       return paymentConfigMapper.selectByName(name);
    }

    public List<PaymentConfig> findAll(){
        return paymentConfigMapper.findAll();
    }
}

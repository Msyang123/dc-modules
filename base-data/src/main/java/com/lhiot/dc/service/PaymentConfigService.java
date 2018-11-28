package com.lhiot.dc.service;

import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.PaymentConfig;
import com.lhiot.dc.mapper.PaymentConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author zhangfeng created in 2018/9/22 10:13
 **/
@Service
@Slf4j
@Transactional
public class PaymentConfigService {

    private PaymentConfigMapper paymentConfigMapper;

    public PaymentConfigService(PaymentConfigMapper paymentConfigMapper) {
        this.paymentConfigMapper = paymentConfigMapper;
    }

    public Tips addConfig(PaymentConfig config) {
        if (Objects.isNull(config.getConfigName())) {
            return Tips.warn("配置名称不能为空！");
        }
        PaymentConfig searchConfig = paymentConfigMapper.selectByName(config.getConfigName());
        if (Objects.nonNull(searchConfig)) {
            return Tips.warn("此名称的配置已存在！");
        }
        paymentConfigMapper.create(config);
        return Tips.empty();
    }
}

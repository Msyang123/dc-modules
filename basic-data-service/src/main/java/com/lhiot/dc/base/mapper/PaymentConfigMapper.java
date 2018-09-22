package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.PaymentConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangfeng created in 2018/9/22 10:13
 **/
@Mapper
@Repository
public interface PaymentConfigMapper {

    PaymentConfig findByName(String name);
}

package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.PaymentConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaymentConfigMapper {

    /**
     * Description:新增支付签名信息
     *
     * @author yijun
     * 2018/09/20 09:01:25
     */
    int create(PaymentConfig paymentConfig);

    /**
     * Description:根据id修改支付签名信息
     *
     * @author yijun
     * 2018/09/20 09:01:25
     */
    int update(PaymentConfig paymentConfig);

    /**
     * Description:根据id查找支付签名信息
     *
     * @author yijun
     */
    PaymentConfig selectById(Long id);


    /**
     * Description:根据配置名称查找支付签名信息
     *
     * @author yijun
     * 2018/09/20 09:01:25
     */
    PaymentConfig selectByName(@Param("configName") String configName);


    /**
     * 查询所有的签名支付信息
     */
    List<PaymentConfig> selectAll();

}


package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.PaymentConfig;
import org.apache.ibatis.annotations.Mapper;
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
    int updateById(PaymentConfig paymentConfig);

    /**
     * Description:根据ids删除支付签名信息
     *
     * @param ids
     * @return
     * @author yijun
     * @date 2018/09/20 09:01:25
     */
    int deleteByIds(List<String> ids);

    /**
     * Description:根据id查找支付签名信息
     *
     * @author yijun
     */
    PaymentConfig selectById(Long id);


    /**
     * Description:根据支付商户名称简称查找支付签名信息
     *
     * @author yijun
     * 2018/09/20 09:01:25
     */
    PaymentConfig selectByName(String paymentName);


    /**
     * 查询所有的签名支付信息
     */
    List<PaymentConfig> findAll();

    /**
     * Description:查询支付签名信息列表
     *
     * @author yijun
     */
    List<PaymentConfig> pagePaymentConfigs(PaymentConfig paymentConfig);


    /**
     * Description: 查询支付签名信息总记录数
     *
     * @author yijun
     */
    long pagePaymentConfigCounts(PaymentConfig paymentConfig);
}


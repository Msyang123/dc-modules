package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.PaymentSign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:支付签名信息Mapper类
* @author yijun
* @date 2018/09/20
*/
@Mapper
public interface PaymentSignMapper {

    /**
    * Description:新增支付签名信息
    *
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
    int create(PaymentSign paymentSign);

    /**
    * Description:根据id修改支付签名信息
    *
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
    int updateById(PaymentSign paymentSign);

    /**
    * Description:根据ids删除支付签名信息
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
    int deleteByIds(java.util.List<String> ids);

    /**
    * Description:根据id查找支付签名信息
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
    PaymentSign selectById(Long id);


    /**
     * Description:根据支付商户名称简称查找支付签名信息
     *
     * @param paymentName
     * @return
     * @author yijun
     * @date 2018/09/20 09:01:25
     */
    PaymentSign selectByName(String paymentName);

    /**
    * Description:查询支付签名信息列表
    *
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
     List<PaymentSign> pagePaymentSigns(PaymentSign paymentSign);


    /**
    * Description: 查询支付签名信息总记录数
    *
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */
    long pagePaymentSignCounts(PaymentSign paymentSign);
}

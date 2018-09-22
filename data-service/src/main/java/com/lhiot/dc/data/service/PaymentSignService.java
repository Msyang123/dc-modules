package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.PaymentSign;
import com.lhiot.dc.data.mapper.PaymentSignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:支付签名信息服务类
* @author yijun
* @date 2018/09/20
*/
@Service
@Transactional
public class PaymentSignService {

    private final PaymentSignMapper paymentSignMapper;

    @Autowired
    public PaymentSignService(PaymentSignMapper paymentSignMapper) {
        this.paymentSignMapper = paymentSignMapper;
    }

    /** 
    * Description:新增支付签名信息
    *  
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */  
    public int create(PaymentSign paymentSign){
        return this.paymentSignMapper.create(paymentSign);
    }

    /** 
    * Description:根据id修改支付签名信息
    *  
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */ 
    public int updateById(PaymentSign paymentSign){
        return this.paymentSignMapper.updateById(paymentSign);
    }

    /** 
    * Description:根据ids删除支付签名信息
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */ 
    public int deleteByIds(String ids){
        return this.paymentSignMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找支付签名信息
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */ 
    public PaymentSign selectById(Long id){
        return this.paymentSignMapper.selectById(id);
    }

    /**
     * Description:根据支付商户名称简称查找支付签名信息
     *
     * @param paymentName
     * @return
     * @author yijun
     * @date 2018/09/20 09:01:25
     */
    public PaymentSign selectByName(String paymentName){
        return this.paymentSignMapper.selectByName(paymentName);
    }

    /** 
    * Description: 查询支付签名信息总记录数
    *  
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */  
    public long count(PaymentSign paymentSign){
        return this.paymentSignMapper.pagePaymentSignCounts(paymentSign);
    }
    
    /** 
    * Description: 查询支付签名信息分页列表
    *  
    * @param paymentSign
    * @return
    * @author yijun
    * @date 2018/09/20 09:01:25
    */  
    public PagerResultObject<PaymentSign> pageList(PaymentSign paymentSign) {
       long total = 0;
       if (paymentSign.getRows() != null && paymentSign.getRows() > 0) {
           total = this.count(paymentSign);
       }
       return PagerResultObject.of(paymentSign, total,
              this.paymentSignMapper.pagePaymentSigns(paymentSign));
    }
}


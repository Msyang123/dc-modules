package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductSale;
import com.lhiot.dc.data.domain.ProductStandard;
import com.lhiot.dc.data.mapper.ProductSaleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Description:服务类
* @author yijun
* @date 2018/08/09
*/
@Service
@Transactional
public class ProductSaleService {

    private final ProductSaleMapper productSaleMapper;
    private final SqlSession sqlSession;


    @Autowired
    public ProductSaleService(ProductSaleMapper productSaleMapper,SqlSession sqlSession) {
        this.productSaleMapper = productSaleMapper;
        this.sqlSession=sqlSession;
    }

    /** 
    * Description:新增
    *  
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */  
    public int create(ProductSale productSale){
        return this.productSaleMapper.create(productSale);
    }

    /** 
    * Description:根据id修改
    *  
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */ 
    public int updateById(ProductSale productSale){
        return this.productSaleMapper.updateById(productSale);
    }

    /** 
    * Description:根据ids删除
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */ 
    public int deleteByIds(String ids){
        return this.productSaleMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */ 
    public ProductSale selectById(Integer id){
        return this.productSaleMapper.selectById(id);
    }

    /** 
    * Description: 查询总记录数
    *  
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */  
    public long count(ProductSale productSale){
        return this.productSaleMapper.pageProductSaleCounts(productSale);
    }
    
    /** 
    * Description: 查询分页列表
    *  
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */  
    public PagerResultObject<ProductSale> pageList(ProductSale productSale) {
       long total = 0;
       if (productSale.getRows() != null && productSale.getRows() > 0) {
           total = this.count(productSale);
       }
       return PagerResultObject.of(productSale, total,
              this.productSaleMapper.pageProductSales(productSale));
    }

    public Long findSaleCountByProductId(Long productStandardId, String applyType) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("productStandardId",productStandardId);
        param.put("applyType",applyType);
        return this.productSaleMapper.findSaleCountByProductId(param)==null?0L:this.productSaleMapper.findSaleCountByProductId(param);
    }
    public void setSaleCount(List<ProductStandard> list, String applyType) {
        if(!CollectionUtils.isEmpty(list)){
            for(ProductStandard productStandard:list){
                Long prodcutStandarId = productStandard.getId();
                Long saleCount =findSaleCountByProductId(prodcutStandarId,applyType);
                productStandard.setSaleCount(saleCount);
            }
        }
    }

    public void recordSaleCount() {
        try {
            sqlSession.delete("com.lhiot.dc.data.mapper.ProducStandardMapper.deleteProductSale");
        }catch (Exception e){

        }
        try {
            sqlSession.update("com.lhiot.dc.data.mapper.ProducStandardMapper.recordSaleCount");
        }catch (Exception e){
        }
    }
}


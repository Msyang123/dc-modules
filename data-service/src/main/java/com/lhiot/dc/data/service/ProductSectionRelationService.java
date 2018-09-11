package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductSectionRelation;
import com.lhiot.dc.data.mapper.ProductSectionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
* Description:板块商品关联服务类
* @author yijun
* @date 2018/08/24
*/
@Service
@Transactional
public class ProductSectionRelationService {

    private final ProductSectionRelationMapper productSectionRelationMapper;

    @Autowired
    public ProductSectionRelationService(ProductSectionRelationMapper productSectionRelationMapper) {
        this.productSectionRelationMapper = productSectionRelationMapper;
    }

    /** 
    * Description:新增板块商品关联
    *  
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */  
    public int create(ProductSectionRelation productSectionRelation){
        return this.productSectionRelationMapper.create(productSectionRelation);
    }

    /** 
    * Description:根据id修改板块商品关联
    *  
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */ 
    public int updateById(ProductSectionRelation productSectionRelation){
        return this.productSectionRelationMapper.updateById(productSectionRelation);
    }

    /** 
    * Description:根据ids删除板块商品关联
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */ 
    public int deleteByIds(String ids){
        return this.productSectionRelationMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找板块商品关联
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */ 
    public ProductSectionRelation selectById(Long id){
        return this.productSectionRelationMapper.selectById(id);
    }

    /** 
    * Description: 查询板块商品关联总记录数
    *  
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */  
    public long count(ProductSectionRelation productSectionRelation){
        return this.productSectionRelationMapper.pageProductSectionRelationCounts(productSectionRelation);
    }
    
    /** 
    * Description: 查询板块商品关联分页列表
    *  
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */  
    public PagerResultObject<ProductSectionRelation> pageList(ProductSectionRelation productSectionRelation) {
       long total = 0;
       if (productSectionRelation.getRows() != null && productSectionRelation.getRows() > 0) {
           total = this.count(productSectionRelation);
       }
       return PagerResultObject.of(productSectionRelation, total,
              this.productSectionRelationMapper.pageProductSectionRelations(productSectionRelation));
    }

    public void update(ProductSectionRelation newRelation) {
        if(null != newRelation.getProductStandardId() && null != newRelation.getProductSectionId()){
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("productStandardId",newRelation.getProductStandardId());
            param.put("productSectionId",newRelation.getProductSectionId());
            ProductSectionRelation productSectionRelation = productSectionRelationMapper.findByStandardIdAndSectionId(param);
            if(null == productSectionRelation){
                productSectionRelationMapper.create(newRelation);
            }
        }

    }
}


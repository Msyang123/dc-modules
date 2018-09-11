package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductAssortmentRelation;
import com.lhiot.dc.data.mapper.ProductAssortmentRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:套餐商品关联服务类
* @author yijun
* @date 2018/07/24
*/
@Service
@Transactional
public class ProductAssortmentRelationService {

    private final ProductAssortmentRelationMapper productAssortmentRelationMapper;

    @Autowired
    public ProductAssortmentRelationService(ProductAssortmentRelationMapper productAssortmentRelationMapper) {
        this.productAssortmentRelationMapper = productAssortmentRelationMapper;
    }

    /** 
    * Description:新增套餐商品关联
    *  
    * @param productAssortmentRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public int create(ProductAssortmentRelation productAssortmentRelation){
        return this.productAssortmentRelationMapper.create(productAssortmentRelation);
    }

    /** 
    * Description:根据id修改套餐商品关联
    *  
    * @param productAssortmentRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int updateById(ProductAssortmentRelation productAssortmentRelation){
        return this.productAssortmentRelationMapper.updateById(productAssortmentRelation);
    }

    /** 
    * Description:根据ids删除套餐商品关联
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int deleteByIds(String ids){
        return this.productAssortmentRelationMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找套餐商品关联
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public ProductAssortmentRelation selectById(Long id){
        return this.productAssortmentRelationMapper.selectById(id);
    }

    /** 
    * Description: 查询套餐商品关联总记录数
    *  
    * @param productAssortmentRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public long count(ProductAssortmentRelation productAssortmentRelation){
        return this.productAssortmentRelationMapper.pageProductAssortmentRelationCounts(productAssortmentRelation);
    }
    
    /** 
    * Description: 查询套餐商品关联分页列表
    *  
    * @param productAssortmentRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public PagerResultObject<ProductAssortmentRelation> pageList(ProductAssortmentRelation productAssortmentRelation) {
       long total = 0;
       if (productAssortmentRelation.getRows() != null && productAssortmentRelation.getRows() > 0) {
           total = this.count(productAssortmentRelation);
       }
       return PagerResultObject.of(productAssortmentRelation, total,
              this.productAssortmentRelationMapper.pageProductAssortmentRelations(productAssortmentRelation));
    }
}


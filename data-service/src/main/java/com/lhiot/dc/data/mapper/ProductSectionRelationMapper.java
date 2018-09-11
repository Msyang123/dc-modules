package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductSectionRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:板块商品关联Mapper类
* @author yijun
* @date 2018/08/24
*/
@Mapper
public interface ProductSectionRelationMapper {

    /**
    * Description:新增板块商品关联
    *
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
    int create(ProductSectionRelation productSectionRelation);

    /**
    * Description:根据id修改板块商品关联
    *
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
    int updateById(ProductSectionRelation productSectionRelation);

    /**
    * Description:根据ids删除板块商品关联
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找板块商品关联
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
    ProductSectionRelation selectById(Long id);

    /**
    * Description:查询板块商品关联列表
    *
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
     List<ProductSectionRelation> pageProductSectionRelations(ProductSectionRelation productSectionRelation);


    /**
    * Description: 查询板块商品关联总记录数
    *
    * @param productSectionRelation
    * @return
    * @author yijun
    * @date 2018/08/24 10:39:31
    */
    long pageProductSectionRelationCounts(ProductSectionRelation productSectionRelation);


    ProductSectionRelation findByStandardIdAndSectionId(Map<String, Object> param);
}

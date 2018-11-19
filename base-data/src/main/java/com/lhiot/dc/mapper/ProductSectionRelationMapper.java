package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductSectionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xiaojian  created in  2018/11/16 9:09
 */
@Mapper
@Repository
public interface ProductSectionRelationMapper {

    /**
     * 新增商品上架与版块关系记录
     *
     * @param ProductSectionRelation 对象
     * @return 执行结果
     */
    int insert(ProductSectionRelation productSectionRelation);


    /**
     * 删除商品上架与版块关系记录
     *
     * @param relationId
     * @return 执行结果
     */
    int deleteById(Long relationId);


    /**
     * 根据商品上架ID集合 删除商品上架与版块关系记录
     *
     * @param shelfIds
     * @return 执行结果
     */
    int deleteRelationByShelfIds(String shelfIds);


    /**
     * 根据商品版块ID集合 删除商品上架与版块关系记录
     *
     * @param sectionIds
     * @return 执行结果
     */
    int deleteRelationBySectionIds(String sectionIds);


}

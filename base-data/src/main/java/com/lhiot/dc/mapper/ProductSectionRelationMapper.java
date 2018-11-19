package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductSectionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 新增批量商品上架与版块关系
     *
     * @param List<ProductSectionRelation>对象
     * @return 执行结果
     */
    int insertList(List<ProductSectionRelation> list);


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
    int deleteRelationByShelfIds(@Param("shelfIds") String shelfIds);


    /**
     * 根据商品版块ID集合 删除商品上架与版块关系记录
     *
     * @param sectionIds
     * @return 执行结果
     */
    int deleteRelationBySectionIds(@Param("sectionIds") String sectionIds);


    /**
     * 批量删除商品上架与版块关系记录
     *
     * @param sectionId
     * @param shelfIds
     * @return 执行结果
     */
    int deleteRelationList(@Param("sectionId") Long sectionId,@Param("shelfIds") String shelfIds);


}

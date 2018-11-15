package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author xiaojian created in 2018/11/13 12:08
 **/
@Mapper
@Repository
public interface ProductSpecificationMapper {

    /**
     * 新增商品规格
     *
     * @param ProductSpecification对象
     * @return 执行结果
     */
    int insert(ProductSpecification productSpecification);


    /**
     * 修改商品规格
     *
     * @param ProductSpecification对象
     * @return 执行结果
     */
    int updateById(ProductSpecification productSpecification);


    /**
     * 根据ID查找单个商品规格
     *
     * @param Id
     * @return 商品规格对象
     */
    ProductSpecification findById(Long Id);


    /**
     * 根据商品ID查找商品规格集合
     *
     * @param productId 商品id
     * @return 商品规格集合
     */
    List<ProductSpecification> findListByProductId(Long productId);


    /**
     * 删除商品规格
     *
     * @param Id
     * @return 执行结果
     */
    int deleteById(Long Id);


    /**
     * 删除商品规格 根据商品Id
     *
     * @param productId
     * @return 执行结果
     */
    int deleteByProductId(Long productId);


    /**
     * 查找出有商品规格的商品集合
     *
     * @param productList 商品id集合
     * @return 商品名称集合
     */
    List<Map<String, Object>> findSpecificationProductIdList(List<String> productList);


}

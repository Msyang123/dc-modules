package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.ProductSpecification;
import com.lhiot.dc.model.ProductSpecificationParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author xiaojian created in 2018/11/13 12:08
 **/
@Mapper
@Repository
public interface ProductSpecificationMapper {

    /**
     * 新增商品规格
     *
     * @param productSpecification 商品规格对象
     * @return 执行结果
     */
    int insert(ProductSpecification productSpecification);


    /**
     * 修改商品规格
     *
     * @param productSpecification 商品规格对象
     * @return 执行结果
     */
    int updateById(ProductSpecification productSpecification);


    /**
     * 根据ID查找单个商品规格
     *
     * @param id 商品规格ID
     * @return 商品规格对象
     */
    ProductSpecification findById(Long id);


    /**
     * 根据商品Id集合 查找出有商品规格的商品
     *
     * @param productIds 商品id集合
     * @return 商品名称集合
     */
    List<Map<String, Object>> findHaveSpecificationByProductIds(String productIds);


    /**
     * 删除商品规格 根据Id集合
     *
     * @param ids 商品规格Id集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询商品规格信息列表
     *
     * @param param 参数
     * @return 商品规格信息列表
     */
    List<ProductSpecification> findList(ProductSpecificationParam param);

    /**
     * 查询商品规格信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(ProductSpecificationParam param);


}

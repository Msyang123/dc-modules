package com.lhiot.dc.mapper;


import com.lhiot.dc.domain.ProductShelf;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author xiaojian created in 2018/11/14 11:44
 **/
@Mapper
@Repository
public interface ProductShelfMapper {

    /**
     * 新增商品上架
     *
     * @param ProductShelf对象
     * @return 执行结果
     */
    int insert(ProductShelf productShelf);


    /**
     * 修改商品上架
     *
     * @param ProductShelf对象
     * @return 执行结果
     */
    int updateById(ProductShelf productShelf);


    /**
     * 根据ID查找单个商品上架
     *
     * @param shelfId
     * @return 商品上架对象
     */
    ProductShelf findById(Long shelfId);


    /**
     * 删除商品上架
     *
     * @param shelfId
     * @return 执行结果
     */
    int deleteById(Long shelfId);


    /**
     * 根据传入商品规格ID，查询所属的商品上架集合
     *
     * @param specificationId 商品规格id
     * @return 商品上架集合
     */
    List<ProductShelf> findListBySpecificationId(Long specificationId);


    /**
     * 根据传入商品ID，查询所属的商品上架集合
     *
     * @param productId 商品id
     * @return 商品上架集合
     */
    List<ProductShelf> findListByProductId(Long productId);


}

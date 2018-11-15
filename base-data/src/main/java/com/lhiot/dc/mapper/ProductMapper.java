package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xiaojian created in 2018/11/12 17:09
 **/
@Mapper
@Repository
public interface ProductMapper {
    /**
     * 新增商品
     *
     * @param product
     * @return 执行结果
     */
    int insert(Product product);

    /**
     * 修改商品
     *
     * @param product
     * @return 执行结果
     */
    int updateById(Product product);

    /**
     * 根据商品ID查找单个商品
     *
     * @param productId
     * @return 商品对象
     */
    Product findById(Long productId);


    /**
     * 删除商品
     *
     * @param productId
     * @return 执行结果
     */
    int deleteById(Long productId);

    /**
     * 批量删除商品集合
     *
     * @param productIdList
     * @return 执行结果
     */
    int batchDeleteByIds(List<String> productIdList);
}

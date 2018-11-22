package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.Product;
import com.lhiot.dc.model.ProductParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian created in 2018/11/12 17:09
 **/
@Mapper
@Repository
public interface ProductMapper {
    /**
     * 新增商品
     *
     * @param product 商品对象
     * @return 执行结果
     */
    int insert(Product product);

    /**
     * 修改商品
     *
     * @param product 商品对象
     * @return 执行结果
     */
    int updateById(Product product);

    /**
     * 根据商品ID查找单个商品
     *
     * @param productId 商品ID
     * @return 商品对象
     */
    Product findById(Long productId);


    /**
     * 根据商品code查找单个商品
     *
     * @param code 商品code
     * @return 商品对象
     */
    Product findByCode(String code);


    /**
     * 批量删除商品集合
     *
     * @param ids 商品ID集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询商品信息列表
     *
     * @param param 参数
     * @return 商品信息列表
     */
    List<Product> findList(ProductParam param);


    /**
     * 查询商品信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(ProductParam param);
}

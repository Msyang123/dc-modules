package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:商品信息Mapper类
* @author Limiaojun
* @date 2018/06/01
*/
@Mapper
public interface ProductMapper {

    /**
    * Description:新增商品信息
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int create(Product product);

    /**
    * Description:根据id修改商品信息
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int updateById(Product product);

    /**
    * Description:根据id删除商品信息
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:查询商品信息列表
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    List<Product> pageProducts(Product product);

    /**
    * Description: 查询商品信息总记录数
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    long pageProductCounts(Product product);

    /**
     * Description:根据商品分类编码查询，查询当前分类下是否有商品
     *
     * @param codes
     * @return
     * @author Limiaojun
     * @date 2018-06-01 19:46
     */
    int findProductByCode(List<String> codes);

    /**
     * Description:根据id查询商品信息
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:20
     */
    Product selectById(Long id);
}

package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.Product;
import com.lhiot.dc.data.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:商品信息服务类
* @author Limiaojun
* @date 2018/06/01
*/
@Service
@Transactional
public class ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper ) {
        this.productMapper = productMapper;
    }

    /**
    * Description:新增商品信息
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int create(Product product){
        return this.productMapper.create(product);
    }

    /**
    * Description:根据id修改商品信息
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int updateById(Product product){
        return this.productMapper.updateById(product);
    }

    /**
    * Description:根据id删除商品信息
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int deleteByIds(String ids){
        return this.productMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }

    /**
    * Description: 查询商品信息总记录数
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public long count(Product product){
        return this.productMapper.pageProductCounts(product);
    }

    /**
    * Description: 查询商品信息分页列表
    *
    * @param product
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public PagerResultObject<Product> pageList(Product product) {
        long total = 0;
        if (product.getRows() != null && product.getRows() > 0) {
            total = this.count(product);
        }
        return PagerResultObject.of(product, total, this.productMapper.pageProducts(product));
    }

    /**
     * Description:根据id查询商品信息
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:20
     */
    public Product selectById(Long id) {

        return this.productMapper.selectById(id);
    }
}


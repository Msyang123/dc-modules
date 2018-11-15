package com.lhiot.dc.service;

import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.mapper.ProductShelfMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author xiaojian created in 2018/11/14 11:44
 **/
@Service
@Slf4j
@Transactional
public class ProductShelfService {
    private ProductShelfMapper shelfMapper;

    public ProductShelfService(ProductShelfMapper shelfMapper) {
        this.shelfMapper = shelfMapper;
    }

    /**
     * 新增商品上架信息
     *
     * @param ProductShelf对象
     * @return 商品上架Id
     */
    public Long insert(ProductShelf productShelf) {
        shelfMapper.insert(productShelf);
        return productShelf.getId();
    }


    /**
     * 修改商品上架信息
     *
     * @param ProductShelf对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(ProductShelf productShelf) {
        return shelfMapper.updateById(productShelf) > 0;
    }


    /**
     * 根据商品上架ID查找单个商品上架
     *
     * @param shelfId
     * @return 商品上架对象
     */
    public ProductShelf findById(Long shelfId) {
        return shelfMapper.findById(shelfId);
    }


    /**
     * 删除商品上架
     *
     * @param shelfId
     * @return 执行结果 true 或者 false
     */
    public boolean delete(Long shelfId) {
        return shelfMapper.deleteById(shelfId) > 0;
    }


    /**
     * 根据传入商品规格ID，查询所属的商品上架集合
     *
     * @param specificationId
     * @return 所属的商品上架集合
     */
    public List<ProductShelf> findListBySpecificationId(Long specificationId) {
        List<ProductShelf> productShelfList = shelfMapper.findListBySpecificationId(specificationId);
        return productShelfList;
    }


    /**
     * 根据传入商品ID，查询所属的商品上架集合
     *
     * @param productId
     * @return 所属的商品上架集合
     */
    public List<ProductShelf> findListByProductId(Long productId) {
        List<ProductShelf> productShelfList = shelfMapper.findListByProductId(productId);
        return productShelfList;
    }


}

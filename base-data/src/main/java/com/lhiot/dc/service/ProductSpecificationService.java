package com.lhiot.dc.service;

import com.lhiot.dc.domain.ProductSpecification;
import com.lhiot.dc.mapper.ProductSpecificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author xiaojian created in 2018/11/13 15:09
 **/
@Service
@Slf4j
@Transactional
public class ProductSpecificationService {
    private ProductSpecificationMapper specificationMapper;


    public ProductSpecificationService(ProductSpecificationMapper specificationMapper) {
        this.specificationMapper = specificationMapper;
    }

    /**
     * 新增商品规格
     *
     * @param ProductSpecification对象
     * @return 商品规格Id
     */
    public Long addProductSpecification(ProductSpecification productSpecification) {
        specificationMapper.insert(productSpecification);
        return productSpecification.getId();
    }

    /**
     * 修改商品规格
     *
     * @param ProductSpecification对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(ProductSpecification productSpecification) {
        return specificationMapper.updateById(productSpecification) > 0;
    }


    /**
     * 根据商品规格ID查找单个商品规格
     *
     * @param specificationId
     * @return 商品规格对象
     */
    public ProductSpecification findById(Long specificationId) {
        return specificationMapper.findById(specificationId);
    }

    /**
     * 删除商品规格
     *
     * @param specificationId
     * @return 执行结果 true 或者 false
     */
    public boolean delete(Long specificationId) {
        return specificationMapper.deleteById(specificationId) > 0;
    }


    /**
     * 根据商品Id删除所属商品规格集合
     *
     * @param productId
     * @return 执行结果 true 或者 false
     */
    public boolean deleteByProductId(Long productId) {
        return specificationMapper.deleteByProductId(productId) > 0;
    }


    /**
     * 根据传入商品ID，查询所属的商品规格集合
     *
     * @param productIdList
     * @return 所属的商品规格集合
     */
    public List<ProductSpecification> findListByProductId(Long productId) {
        List<ProductSpecification> specificationList = specificationMapper.findListByProductId(productId);
        return specificationList;
    }


    /**
     * 根据传入商品ID集合，查询存在规格的商品
     *
     * @param productIdList
     * @return 存在规格的商品名称集合
     */
    public List<String> findSpecificationProductIdList(List<String> productIdList) {
        List<String> resultList = new ArrayList<>();
        List<Map<String, Object>> productList = specificationMapper.findSpecificationProductIdList(productIdList);
        productList.stream().forEach(product -> resultList.add(product.get("productName").toString()));
        return resultList;
    }


}

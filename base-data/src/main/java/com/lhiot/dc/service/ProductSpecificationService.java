package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.lhiot.dc.entity.ProductSpecification;
import com.lhiot.dc.model.ProductSpecificationParam;
import com.lhiot.dc.mapper.ProductSpecificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

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
        productSpecification.setCreateAt(Date.from(Instant.now()));
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
     * 根据Id集合删除商品规格
     *
     * @param ids
     * @return 执行结果 true 或者 false
     */
    public boolean deleteByIds(String ids) {
        return specificationMapper.deleteByIds(ids) > 0;
    }


    /**
     * 根据传入商品ID集合，查询存在规格的商品
     *
     * @param productIds
     * @return 存在规格的商品名称集合
     */
    public List<String> findHaveSpecificationByProductIds(String productIds) {
        List<String> resultList = new ArrayList<>();
        List<Map<String, Object>> productList = specificationMapper.findHaveSpecificationByProductIds(productIds);
        productList.stream().forEach(product -> resultList.add(product.get("productName").toString()));
        return resultList;
    }


    /**
     * 查询商品规格信息列表
     *
     * @param param 参数
     * @return 分页商品规格信息数据
     */
    public Pages<ProductSpecification> findList(ProductSpecificationParam param) {
        List<ProductSpecification> list = specificationMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? specificationMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

package com.lhiot.dc.service;

import com.lhiot.dc.mapper.ProductSpecificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author zhangfeng created in 2018/9/20 15:09
 **/
@Service
@Slf4j
@Transactional
public class ProductSpecificationService {
    private ProductSpecificationMapper specificationMapper;

    public ProductSpecificationService(ProductSpecificationMapper specificationMapper) {
        this.specificationMapper = specificationMapper;
    }

    public boolean countSpecificationByProductId(Long productId) {
        return specificationMapper.countByProductId(productId) > 0;
    }

    /**
     * 根据传入商品ID集合，查询存在规格的商品
     *
     * @param productIdList
     * @return
     */
    public List<String> findProductIdListByProductId(List<String> productIdList) {
        List<String> resultList = new ArrayList<>();
        List<Map<String,Object>> productList = specificationMapper.findProductIdList(productIdList);
        for (String productId : productIdList) {
            for (Map<String,Object> map : productList) {
                if (Objects.equals(productId, map.get("product_id"))) {
                    resultList.add(map.get("name")+"");
                    break;
                }
            }
        }
        return resultList;
    }
}

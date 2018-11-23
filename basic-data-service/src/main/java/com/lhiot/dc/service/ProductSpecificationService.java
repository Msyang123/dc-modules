package com.lhiot.dc.service;

import com.lhiot.dc.domain.ProductAttachment;
import com.lhiot.dc.domain.ProductResult;
import com.lhiot.dc.mapper.ProductAttachmentMapper;
import com.lhiot.dc.mapper.ProductSpecificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zhangfeng created in 2018/9/20 15:09
 **/
@Service
@Slf4j
@Transactional
public class ProductSpecificationService {
    private ProductSpecificationMapper specificationMapper;
    private ProductAttachmentMapper productAttachmentMapper;


    public ProductSpecificationService(ProductSpecificationMapper specificationMapper, ProductAttachmentMapper productAttachmentMapper) {
        this.specificationMapper = specificationMapper;
        this.productAttachmentMapper = productAttachmentMapper;
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
        List<Map<String, Object>> productList = specificationMapper.findProductIdList(productIdList);
        for (String productId : productIdList) {
            for (Map<String, Object> map : productList) {
                if (Objects.equals(productId, map.get("product_id"))) {
                    resultList.add(map.get("name") + "");
                    break;
                }
            }
        }
        return resultList;
    }

    /**
     * 拼装基础规格信息和商品附件信息
     *
     * @param productResultList 商品信息
     * @return List<ProductResult>
     */
    public List<ProductResult> assemblyProductList(List<ProductResult> productResultList) {
        List<String> productIdList = productResultList.parallelStream().map(ProductResult::getProductId).map(String::valueOf).collect(Collectors.toList());
        List<ProductAttachment> attachmentList = productAttachmentMapper.findAttachmentByProductIdList(productIdList);
        List<Map<String, Object>> list = specificationMapper.findBaseSpecificationByProductIds(productIdList);
        productResultList.forEach(productResult -> {
            list.stream().filter(map -> Objects.equals(map.get("productId"), productResult.getProductId()))
                    .forEach(map -> {
                        productResult.setBaseBarcode((String) map.get("baseBarcode"));
                        productResult.setBaseUnit((String) map.get("baseUnit"));
                    });
            attachmentList.stream().filter(attachment -> Objects.equals(attachment.getProductId(), productResult.getProductId()))
                    .forEach(attachment -> {
                        switch (attachment.getAttachmentType()) {
                            case LARGE_IMAGE:
                                productResult.setLargeImage(attachment.getUrl());
                                break;
                            case SMALL_IMAGE:
                                productResult.setSmallImage(attachment.getUrl());
                                break;
                            case PRIMARY_IMAGE:
                                productResult.setImage(attachment.getUrl());
                                break;
                            default:
                                break;
                        }
                    });

        });
        return productResultList;
    }
}

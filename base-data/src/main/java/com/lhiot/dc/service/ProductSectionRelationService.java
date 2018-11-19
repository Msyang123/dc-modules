package com.lhiot.dc.service;

import com.lhiot.dc.domain.ProductSectionRelation;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaojian  created in  2018/11/16 9:18
 */
@Service
@Slf4j
@Transactional
public class ProductSectionRelationService {
    private ProductSectionRelationMapper relationMapper;

    public ProductSectionRelationService(ProductSectionRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }


    /**
     * 新增商品上架与版块关系
     *
     * @param ProductSectionRelation对象
     * @return 新增商品版块Id
     */
    public Long addRelation(ProductSectionRelation productSectionRelation) {
        relationMapper.insert(productSectionRelation);
        return productSectionRelation.getId();
    }


    /**
     * 删除商品上架与版块关系
     *
     * @param relationId
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelation(Long relationId) {
        return relationMapper.deleteById(relationId) > 0;
    }


}

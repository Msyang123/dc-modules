package com.lhiot.dc.service;

import com.leon.microx.util.StringUtils;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ProductSectionRelation;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * @param productSectionRelation 商品上架与版块关系对象
     * @return 商品上架与版块关系Id
     */
    public Tips addRelation(ProductSectionRelation productSectionRelation) {
        //先做幂等验证
        List<ProductSectionRelation> poList=relationMapper.selectRelationList(productSectionRelation.getSectionId(),productSectionRelation.getShelfId()+"");
        if(!poList.isEmpty()){
            return Tips.warn("商品上架与版块关系重复，添加失败.");
        }
        relationMapper.insert(productSectionRelation);
        return Tips.info(productSectionRelation.getId() + "");
    }

    /**
     * 新增批量商品上架与版块关系
     *
     * @param sectionId 版块ID
     * @param shelfIds  商品上架ID集合
     * @return  Tips信息  执行结果
     */
    public Tips addRelationList(Long sectionId, String shelfIds) {
        //先做幂等验证
        List<ProductSectionRelation> poList=relationMapper.selectRelationList(sectionId,shelfIds);
        if(!poList.isEmpty()){
            return Tips.warn("商品上架与版块关系重复，添加失败.");
        }

        List<ProductSectionRelation> psrList = new ArrayList<>();
        String[] shelfIdArrays = StringUtils.tokenizeToStringArray(shelfIds, ",");
        List<String> shelfIdList = Stream.of(shelfIdArrays).collect(Collectors.toList());
        ProductSectionRelation productSectionRelation;
        for (String shelfId : shelfIdList) {
            productSectionRelation = new ProductSectionRelation();
            productSectionRelation.setSectionId(sectionId);
            productSectionRelation.setShelfId(Long.valueOf(shelfId));
            psrList.add(productSectionRelation);
        }
        int result = relationMapper.insertList(psrList);
        return result > 0 ? Tips.info("添加成功") : Tips.warn("批量添加版块与商品上架关系失败！");
    }


    /**
     * 删除商品上架与版块关系
     *
     * @param relationId 关系ID
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelation(Long relationId) {
        return relationMapper.deleteById(relationId) > 0;
    }


    /**
     * 批量删除商品上架与版块关系
     *
     * @param sectionId 商品版块ID
     * @param shelfIds  商品上架ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelationList(Long sectionId, String shelfIds) {
        return relationMapper.deleteRelationList(sectionId, shelfIds) > 0;
    }


}

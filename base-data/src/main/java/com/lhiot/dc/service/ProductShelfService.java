package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ProductShelf;
import com.lhiot.dc.entity.ProductSpecification;
import com.lhiot.dc.mapper.ProductSpecificationMapper;
import com.lhiot.dc.model.ProductShelfParam;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import com.lhiot.dc.mapper.ProductShelfMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author xiaojian created in 2018/11/14 11:44
 **/
@Service
@Slf4j
@Transactional
public class ProductShelfService {
    private ProductShelfMapper shelfMapper;
    private ProductSectionRelationMapper relationMapper;
    private ProductSpecificationMapper specificationMapper;

    public ProductShelfService(ProductShelfMapper shelfMapper, ProductSectionRelationMapper relationMapper, ProductSpecificationMapper specificationMapper) {
        this.shelfMapper = shelfMapper;
        this.relationMapper = relationMapper;
        this.specificationMapper = specificationMapper;
    }

    /**
     * 新增商品上架信息
     *
     * @param productShelf 商品上架对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips insert(ProductShelf productShelf) {
        productShelf.setCreateAt(Date.from(Instant.now()));
        shelfMapper.insert(productShelf);
        return Tips.info(productShelf.getId() + "");
    }


    /**
     * 修改商品上架信息
     *
     * @param productShelf 商品上架对象
     * @return Tips信息 执行结果
     */
    public Tips update(ProductShelf productShelf) {
        int result = shelfMapper.updateById(productShelf);
        return result > 0 ? Tips.info("修改成功") : Tips.warn("修改信息失败！");
    }


    /**
     * 根据商品上架ID查找单个商品上架
     *
     * @param shelfId        商品上架ID
     * @param includeProduct 是否加载商品信息
     * @return 商品上架对象
     */
    public ProductShelf findById(Long shelfId, Boolean includeProduct) {
        ProductShelf productShelf = shelfMapper.findById(shelfId);
        if (Objects.nonNull(productShelf) && includeProduct && Objects.nonNull(productShelf.getSpecificationId())) {
            productShelf.setProductSpecification(specificationMapper.findById(productShelf.getSpecificationId()));
        }
        return productShelf;
    }


    /**
     * 根据Id集合批量删除商品上架
     *
     * @param ids 商品上架ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        //先删除商品上架和商品版块的关系记录
        relationMapper.deleteRelationByShelfIds(ids);
        return shelfMapper.deleteByIds(ids) > 0;
    }


    /**
     * 根据传入商品规格ID集合，查询所属的商品上架集合
     *
     * @param specificationIds 商品规格ID集合
     * @return 所属的商品上架集合
     */
    public List<ProductShelf> findListBySpecificationIds(String specificationIds) {
        return shelfMapper.findListBySpecificationIds(specificationIds);
    }


    /**
     * 根据参数，查询所属的商品上架集合
     *
     * @param param 参数
     * @return 上架信息集合
     */
    public List<ProductShelf> findListByParam(ProductShelfParam param) {
        List<ProductShelf> list = shelfMapper.findList(param);
        if (Objects.nonNull(param.getIncludeProduct()) && param.getIncludeProduct()) {
            List<Long> specificationIdList = new ArrayList<>();
            list.forEach(productShelf -> {
                if (Objects.nonNull(productShelf.getSpecificationId())) {
                    specificationIdList.add(productShelf.getSpecificationId());
                }
            });
            if (!specificationIdList.isEmpty()) {
                List<ProductSpecification> specificationList = specificationMapper.findListByIdList(specificationIdList);
                Map<Long, ProductSpecification> specificationMap = specificationList.stream().collect(Collectors.toMap(ProductSpecification::getId, productSpecification -> productSpecification));
                list = list.stream().peek(productShelf -> {
                    if (Objects.nonNull(productShelf.getSpecificationId()) && specificationMap.containsKey(productShelf.getSpecificationId())) {
                        productShelf.setProductSpecification(specificationMap.get(productShelf.getSpecificationId()));
                    }
                }).collect(Collectors.toList());
            }
        }
        return list;
    }


    /**
     * 查询商品上架信息列表
     *
     * @param param 参数
     * @return 分页上架信息数据
     */
    public Pages<ProductShelf> findList(ProductShelfParam param) {
        List<ProductShelf> list = this.findListByParam(param);
        int total = param.getPageFlag() ? shelfMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

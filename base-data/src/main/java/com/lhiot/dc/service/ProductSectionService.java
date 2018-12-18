package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ProductSection;
import com.lhiot.dc.model.ProductSectionParam;
import com.lhiot.dc.mapper.ProductSectionMapper;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import com.lhiot.dc.model.ProductShelfParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaojian  created in  2018/11/15 16:44
 */
@Service
@Slf4j
@Transactional
public class ProductSectionService {

    private ProductSectionMapper sectionMapper;
    private ProductSectionRelationMapper relationMapper;
    private ProductShelfService productShelfService;

    public ProductSectionService(ProductSectionMapper sectionMapper, ProductSectionRelationMapper relationMapper, ProductShelfService productShelfService) {
        this.sectionMapper = sectionMapper;
        this.relationMapper = relationMapper;
        this.productShelfService = productShelfService;
    }

    /**
     * 新增商品版块
     *
     * @param productSection 商品版块对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips addSection(ProductSection productSection) {
        if (Objects.isNull(productSection.getSectionName())) {
            return Tips.warn("商品版块名称为空，添加失败.");
        }
        // 幂等添加
        List<ProductSection> po = sectionMapper.findListByParentIdAndSectionName(productSection.getParentId(), productSection.getSectionName());
        if (!po.isEmpty()) {
            return Tips.warn("商品版块重复，添加失败.");
        }
        productSection.setCreateAt(Date.from(Instant.now()));
        sectionMapper.insert(productSection);
        return Tips.info(productSection.getId() + "");
    }


    /**
     * 修改商品版块信息
     *
     * @param productSection 商品版块对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(ProductSection productSection) {
        return sectionMapper.updateById(productSection) > 0;
    }


    /**
     * 根据商品版块ID查找单个商品版块
     *
     * @param sectionId         商品版块ID
     * @param includeShelves    是否加载版块下商品上架信息
     * @param includeShelvesQty 加载商品上架最大条数(includeShelves为true起作用，为空则加载所有)
     * @param includeProduct    是否加载商品信息(includeShelves为true起作用，为空则加载所有)
     * @return 商品版块对象
     */
    public ProductSection findById(Long sectionId, Boolean includeShelves, Long includeShelvesQty, Boolean includeProduct) {
        ProductSection productSection = sectionMapper.findById(sectionId);
        if (Objects.nonNull(productSection) && Objects.nonNull(includeShelves) && includeShelves) {
            ProductShelfParam shelfParam = new ProductShelfParam();
            shelfParam.setSectionId(productSection.getId());
            if (Objects.nonNull(includeShelvesQty)) {
                shelfParam.setPage(1);
                shelfParam.setRows(includeShelvesQty.intValue());
            }
            if (Objects.nonNull(includeProduct) && includeProduct) {
                shelfParam.setIncludeProduct(Boolean.TRUE);
            }
            productSection.setProductShelfList(productShelfService.findListByParam(shelfParam));
        }
        return productSection;
    }


    /**
     * 根据Id集合批量删除商品版块信息
     *
     * @param ids Id集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        //根据商品版块ID集合删除相关关系
        relationMapper.deleteRelationBySectionIds(ids);
        return sectionMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询商品版块信息列表
     *
     * @param param 参数
     * @return 分页商品版块信息数据
     */
    public Pages<ProductSection> findList(ProductSectionParam param) {
        List<ProductSection> list = sectionMapper.findList(param);
        if (Objects.nonNull(param.getIncludeShelves()) && param.getIncludeShelves()) {
            list = list.stream().peek(productSection -> {
                    ProductShelfParam shelfParam = new ProductShelfParam();
                    if (Objects.nonNull(param.getIncludeShelvesQty())) {
                        shelfParam.setPage(1);
                        shelfParam.setRows(param.getIncludeShelvesQty().intValue());
                    }
                    shelfParam.setSectionId(productSection.getId());
                    if (Objects.nonNull(param.getIncludeProduct()) && param.getIncludeProduct()) {
                        shelfParam.setIncludeProduct(Boolean.TRUE);
                    }
                    productSection.setProductShelfList(productShelfService.findListByParam(shelfParam));
                }
            ).collect(Collectors.toList());
        }
        int total = param.getPageFlag() ? sectionMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

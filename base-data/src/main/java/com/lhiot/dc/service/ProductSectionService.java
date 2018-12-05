package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ProductSection;
import com.lhiot.dc.model.ProductSectionParam;
import com.lhiot.dc.mapper.ProductSectionMapper;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/15 16:44
 */
@Service
@Slf4j
@Transactional
public class ProductSectionService {

    private ProductSectionMapper sectionMapper;
    private ProductSectionRelationMapper relationMapper;

    public ProductSectionService(ProductSectionMapper sectionMapper, ProductSectionRelationMapper relationMapper) {
        this.sectionMapper = sectionMapper;
        this.relationMapper = relationMapper;
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
        return includeShelves != null && includeShelves ?
                includeProduct != null && includeProduct ? sectionMapper.findByIdIncludeShelvesIncludeProduct(sectionId, includeShelvesQty) : sectionMapper.findByIdIncludeShelves(sectionId, includeShelvesQty)
                : sectionMapper.findById(sectionId);
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
        List<ProductSection> list = Objects.nonNull(param.getIncludeShelves()) && param.getIncludeShelves() ?
                Objects.nonNull(param.getIncludeProduct()) && param.getIncludeProduct() ? sectionMapper.findListIncludeShelvesIncludeProduct(param) : sectionMapper.findListIncludeShelves(param)
                : sectionMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? sectionMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

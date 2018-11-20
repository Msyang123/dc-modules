package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductShelfParam;
import com.lhiot.dc.mapper.ProductSectionRelationMapper;
import com.lhiot.dc.mapper.ProductShelfMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author xiaojian created in 2018/11/14 11:44
 **/
@Service
@Slf4j
@Transactional
public class ProductShelfService {
    private ProductShelfMapper shelfMapper;
    private ProductSectionRelationMapper relationMapper;

    public ProductShelfService(ProductShelfMapper shelfMapper, ProductSectionRelationMapper relationMapper) {
        this.shelfMapper = shelfMapper;
        this.relationMapper = relationMapper;
    }

    /**
     * 新增商品上架信息
     *
     * @param ProductShelf对象
     * @return 商品上架Id
     */
    public Long insert(ProductShelf productShelf) {
        productShelf.setCreateAt(Date.from(Instant.now()));
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
     * 根据Id集合批量删除商品上架
     *
     * @param ids
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
     * @param specificationIds
     * @return 所属的商品上架集合
     */
    public List<ProductShelf> findListBySpecificationIds(String specificationIds) {
        List<ProductShelf> productShelfList = shelfMapper.findListBySpecificationIds(specificationIds);
        return productShelfList;
    }


    /**
     * 查询商品上架信息列表
     *
     * @param param 参数
     * @return 分页上架信息数据
     */
    public Pages<ProductShelf> findList(ProductShelfParam param) {
        List<ProductShelf> list = shelfMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? shelfMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

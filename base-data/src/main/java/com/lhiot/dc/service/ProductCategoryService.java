package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ProductCategory;
import com.lhiot.dc.model.ProductCategoryParam;
import com.lhiot.dc.mapper.ProductCategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/16 16:42
 */
@Service
@Slf4j
@Transactional
public class ProductCategoryService {
    private ProductCategoryMapper categoryMapper;

    public ProductCategoryService(ProductCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 新增分类
     *
     * @param productCategory 分类对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips addProductCategory(ProductCategory productCategory) {
        if (Objects.isNull(productCategory.getGroupName())) {
            return Tips.warn("分类名为空，添加失败.");
        }
        // 幂等添加
        List<ProductCategory> po = categoryMapper.findListByParentIdAndGroupName(productCategory.getParentId(), productCategory.getGroupName());
        if (!po.isEmpty()) {
            return Tips.warn("商品分类重复，添加失败.");
        }

        productCategory.setCreateAt(Date.from(Instant.now()));
        categoryMapper.insert(productCategory);
        return Tips.info(productCategory.getId() + "");
    }


    /**
     * 修改商品分类信息
     *
     * @param productCategory 商品分类对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(ProductCategory productCategory) {
        return categoryMapper.updateById(productCategory) > 0;
    }


    /**
     * 根据商品分类ID查找单个商品分类
     *
     * @param categoryId 商品分类ID
     * @return 商品版块对象
     */
    public ProductCategory findById(Long categoryId) {
        return categoryMapper.findById(categoryId);
    }


    /**
     * 根据Id集合批量删除分类信息
     *
     * @param ids 商品分类ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        return categoryMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询商品分类信息列表
     *
     * @param param 参数
     * @return 分页商品分类信息数据
     */
    public Pages<ProductCategory> findList(ProductCategoryParam param) {
        List<ProductCategory> list = categoryMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? categoryMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

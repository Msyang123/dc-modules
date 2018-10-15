package com.lhiot.dc.base.service;

import com.leon.microx.support.result.Pages;
import com.lhiot.dc.base.mapper.CategoryItemMapper;
import com.lhiot.dc.base.model.CategoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description:字典数据服务类
 *
 * @author yijun
 * @date 2018/10/12
 */
@Service
@Transactional
public class CategoryItemService {

    private final CategoryItemMapper categoryItemMapper;

    @Autowired
    public CategoryItemService(CategoryItemMapper categoryItemMapper) {
        this.categoryItemMapper = categoryItemMapper;
    }

    /**
     * Description:新增字典数据
     *
     * @param categoryItem
     * @return int
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public int create(CategoryItem categoryItem) {
        return this.categoryItemMapper.create(categoryItem);
    }

    /**
     * Description:根据id修改字典数据
     *
     * @param categoryItem
     * @return int
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public int updateById(CategoryItem categoryItem) {
        return this.categoryItemMapper.updateById(categoryItem);
    }

    /**
     * Description:根据ids删除字典数据
     *
     * @param ids id集合
     * @return int
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public int deleteByIds(String ids) {
        return this.categoryItemMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }

    /**
     * Description:根据id查找字典数据
     *
     * @param id
     * @return CategoryItem
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public CategoryItem selectById(Long id) {
        return this.categoryItemMapper.selectById(id);
    }

    /**
     * Description: 查询字典数据列表
     *
     * @param categoryItem
     * @return List
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public List<CategoryItem> list(CategoryItem categoryItem) {
        return this.categoryItemMapper.pageCategoryItems(categoryItem);
    }

    /**
     * Description: 查询字典数据分页列表
     *
     * @param categoryItem
     * @return Pages<CategoryItem>
     * @author yijun
     * 2018/10/12 09:41:14
     */
    public Pages<CategoryItem> pageList(CategoryItem categoryItem) {
        return Pages.of(this.categoryItemMapper.pageCategoryItemCounts(categoryItem),
                this.list(categoryItem));
    }

    /**
     * 根据父级CODE和本级CODE联合查询数据
     *
     * @param map cgCode 父级编码  code 本级编码
     * @return CategoryItem
     */
    public CategoryItem findByCode(Map<String, Object> map) {
        return categoryItemMapper.findByCode(map);
    }
}


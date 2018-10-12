package com.lhiot.dc.base.service;

import com.leon.microx.support.result.Pages;
import com.lhiot.dc.base.model.CategoryItem;
import com.lhiot.dc.base.mapper.CategoryItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
* Description:字典数据服务类
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
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */  
    public int create(CategoryItem categoryItem){
        return this.categoryItemMapper.create(categoryItem);
    }

    /** 
    * Description:根据id修改字典数据
    *  
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public int updateById(CategoryItem categoryItem){
        return this.categoryItemMapper.updateById(categoryItem);
    }

    /** 
    * Description:根据ids删除字典数据
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public int deleteByIds(String ids){
        return this.categoryItemMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找字典数据
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public CategoryItem selectById(Long id){
        return this.categoryItemMapper.selectById(id);
    }

    /** 
    * Description: 查询字典数据列表
    *  
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */  
    public List list(CategoryItem categoryItem){
        return this.categoryItemMapper.pageCategoryItems(categoryItem);
    }
    
    /** 
    * Description: 查询字典数据分页列表
    *  
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */  
    public Pages<CategoryItem> pageList(CategoryItem categoryItem) {
       return Pages.of(this.categoryItemMapper.pageCategoryItemCounts(categoryItem),
              this.list(categoryItem));
    }
}


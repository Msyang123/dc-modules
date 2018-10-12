package com.lhiot.dc.base.service;

import com.leon.microx.support.result.Pages;
import com.leon.microx.util.StringUtils;
import com.lhiot.dc.base.mapper.CategoryItemMapper;
import com.lhiot.dc.base.model.Category;
import com.lhiot.dc.base.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
* Description:字典服务类
* @author yijun
* @date 2018/10/12
*/
@Service
@Transactional
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryItemMapper categoryItemMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper, CategoryItemMapper categoryItemMapper) {
        this.categoryMapper = categoryMapper;
        this.categoryItemMapper = categoryItemMapper;
    }

    /** 
    * Description:新增字典
    *  
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */  
    public int create(Category category){
        return this.categoryMapper.create(category);
    }

    /** 
    * Description:根据id修改字典
    *  
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public int updateById(Category category){
        return this.categoryMapper.updateById(category);
    }

    /** 
    * Description:根据ids删除字典
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public int deleteByIds(String ids){
        List<String> idsArr = Arrays.asList(StringUtils.tokenizeToStringArray(ids, ","));
        //删除字典项
        categoryItemMapper.deleteByCateagoryIds(idsArr);
        int result=this.categoryMapper.deleteByIds(idsArr);
        return result;
    }
    
    /** 
    * Description:根据id查找字典
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */ 
    public Category selectById(Long id){
        Category category = this.categoryMapper.selectById(id);
        if(Objects.isNull(category)){
            return null;
        }
        category.setCategoryItemList(this.categoryItemMapper.selectByCgCode(category.getCode()));
        return category;
    }

    /**
     * Description:根据code查找字典
     *
     * @param code
     * @return
     * @author yijun
     */
    public Category selectByCode(String code){
        Category category = this.categoryMapper.selectByCode(code);
        if(Objects.isNull(category)){
            return null;
        }
        category.setCategoryItemList(this.categoryItemMapper.selectByCgCode(category.getCode()));
        return category;
    }


    /**
     * Description: 查询字典列表
     * @param category
     * @return
     */
    public List<Category> list(Category category) {
        return this.categoryMapper.pageCategorys(category);
    }
    /** 
    * Description: 查询字典分页列表
    *  
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */  
    public Pages<Category> pageList(Category category) {
       return Pages.of(this.categoryMapper.pageCategoryCounts(category),
              this.list(category));
    }
}


package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.SystemSettingCategory;
import com.lhiot.dc.data.mapper.SystemSettingCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:系统设置分类服务类
* @author yijun
* @date 2018/07/24
*/
@Service
@Transactional
public class SystemSettingCategoryService {

    private final SystemSettingCategoryMapper systemSettingCategoryMapper;

    @Autowired
    public SystemSettingCategoryService(SystemSettingCategoryMapper systemSettingCategoryMapper) {
        this.systemSettingCategoryMapper = systemSettingCategoryMapper;
    }

    /** 
    * Description:新增系统设置分类
    *  
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public int create(SystemSettingCategory systemSettingCategory){
        return this.systemSettingCategoryMapper.create(systemSettingCategory);
    }

    /** 
    * Description:根据id修改系统设置分类
    *  
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int updateById(SystemSettingCategory systemSettingCategory){
        return this.systemSettingCategoryMapper.updateById(systemSettingCategory);
    }

    /** 
    * Description:根据ids删除系统设置分类
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int deleteByIds(String ids){
        return this.systemSettingCategoryMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找系统设置分类
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public SystemSettingCategory selectById(Long id){
        return this.systemSettingCategoryMapper.selectById(id);
    }

    /** 
    * Description: 查询系统设置分类总记录数
    *  
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public long count(SystemSettingCategory systemSettingCategory){
        return this.systemSettingCategoryMapper.pageSystemSettingCategoryCounts(systemSettingCategory);
    }
    
    /** 
    * Description: 查询系统设置分类分页列表
    *  
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public PagerResultObject<SystemSettingCategory> pageList(SystemSettingCategory systemSettingCategory) {
       long total = 0;
       if (systemSettingCategory.getRows() != null && systemSettingCategory.getRows() > 0) {
           total = this.count(systemSettingCategory);
       }
       return PagerResultObject.of(systemSettingCategory, total,
              this.systemSettingCategoryMapper.pageSystemSettingCategorys(systemSettingCategory));
    }
}


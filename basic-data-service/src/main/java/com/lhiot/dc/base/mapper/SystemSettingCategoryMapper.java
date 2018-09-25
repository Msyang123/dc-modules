package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.SystemSettingCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:系统设置分类Mapper类
* @author yijun
* @date 2018/07/24
*/
@Mapper
public interface SystemSettingCategoryMapper {

    /**
    * Description:新增系统设置分类
    *
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int create(SystemSettingCategory systemSettingCategory);

    /**
    * Description:根据id修改系统设置分类
    *
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int updateById(SystemSettingCategory systemSettingCategory);

    /**
    * Description:根据ids删除系统设置分类
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找系统设置分类
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    SystemSettingCategory selectById(Long id);

    /**
    * Description:查询系统设置分类列表
    *
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
     List<SystemSettingCategory> pageSystemSettingCategorys(SystemSettingCategory systemSettingCategory);


    /**
    * Description: 查询系统设置分类总记录数
    *
    * @param systemSettingCategory
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    long pageSystemSettingCategoryCounts(SystemSettingCategory systemSettingCategory);
}

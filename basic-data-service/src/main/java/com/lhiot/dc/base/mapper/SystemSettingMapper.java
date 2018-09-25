package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.SystemSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:系统设置参数Mapper类
* @author yijun
* @date 2018/07/24
*/
@Mapper
public interface SystemSettingMapper {

    /**
    * Description:新增系统设置参数
    *
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int create(SystemSetting systemSetting);

    /**
    * Description:根据id修改系统设置参数
    *
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int updateById(SystemSetting systemSetting);

    /**
    * Description:根据ids删除系统设置参数
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找系统设置参数
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    SystemSetting selectById(Long id);


    /**
     * Description:根据系统设置key查找系统设置参数
     *
     * @param name
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    SystemSetting findByName(String name);

    /**
    * Description:查询系统设置参数列表
    *
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
     List<SystemSetting> pageSystemSettings(SystemSetting systemSetting);


    /**
    * Description: 查询系统设置参数总记录数
    *
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    long pageSystemSettingCounts(SystemSetting systemSetting);
}

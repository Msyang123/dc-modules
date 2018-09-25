package com.lhiot.dc.base.service;

import com.lhiot.dc.base.common.PagerResultObject;
import com.lhiot.dc.base.model.SystemSetting;
import com.lhiot.dc.base.mapper.SystemSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:系统设置参数服务类
* @author yijun
* @date 2018/07/24
*/
@Service
@Transactional
public class SystemSettingService {

    private final SystemSettingMapper systemSettingMapper;

    @Autowired
    public SystemSettingService(SystemSettingMapper systemSettingMapper) {
        this.systemSettingMapper = systemSettingMapper;
    }

    /** 
    * Description:新增系统设置参数
    *  
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public int create(SystemSetting systemSetting){
        return this.systemSettingMapper.create(systemSetting);
    }

    /** 
    * Description:根据id修改系统设置参数
    *  
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int updateById(SystemSetting systemSetting){
        return this.systemSettingMapper.updateById(systemSetting);
    }

    /** 
    * Description:根据ids删除系统设置参数
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int deleteByIds(String ids){
        return this.systemSettingMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据系统设置key查找系统设置参数
    *  
    * @param name
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public SystemSetting findByName(String name){
        return this.systemSettingMapper.findByName(name);
    }


    /**
     * Description:根据id查找系统设置参数
     *
     * @param id
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    public SystemSetting selectById(Long id){
        return this.systemSettingMapper.selectById(id);
    }

    /** 
    * Description: 查询系统设置参数总记录数
    *  
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public long count(SystemSetting systemSetting){
        return this.systemSettingMapper.pageSystemSettingCounts(systemSetting);
    }
    
    /** 
    * Description: 查询系统设置参数分页列表
    *  
    * @param systemSetting
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public PagerResultObject<SystemSetting> pageList(SystemSetting systemSetting) {
       long total = 0;
       if (systemSetting.getRows() != null && systemSetting.getRows() > 0) {
           total = this.count(systemSetting);
       }
       return PagerResultObject.of(systemSetting, total,
              this.systemSettingMapper.pageSystemSettings(systemSetting));
    }
}


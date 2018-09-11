package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.StorePosition;
import com.lhiot.dc.data.mapper.StorePositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:门店位置服务类
* @author yijun
* @date 2018/09/11
*/
@Service
@Transactional
public class StorePositionService {

    private final StorePositionMapper storePositionMapper;

    @Autowired
    public StorePositionService(StorePositionMapper storePositionMapper) {
        this.storePositionMapper = storePositionMapper;
    }

    /** 
    * Description:新增门店位置
    *  
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */  
    public int create(StorePosition storePosition){
        return this.storePositionMapper.create(storePosition);
    }

    /** 
    * Description:根据id修改门店位置
    *  
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */ 
    public int updateById(StorePosition storePosition){
        return this.storePositionMapper.updateById(storePosition);
    }

    /** 
    * Description:根据ids删除门店位置
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */ 
    public int deleteByIds(String ids){
        return this.storePositionMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找门店位置
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */ 
    public StorePosition selectById(Long id){
        return this.storePositionMapper.selectById(id);
    }

    /** 
    * Description: 查询门店位置总记录数
    *  
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */  
    public long count(StorePosition storePosition){
        return this.storePositionMapper.pageStorePositionCounts(storePosition);
    }
    
    /** 
    * Description: 查询门店位置分页列表
    *  
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */  
    public PagerResultObject<StorePosition> pageList(StorePosition storePosition) {
       long total = 0;
       if (storePosition.getRows() != null && storePosition.getRows() > 0) {
           total = this.count(storePosition);
       }
       return PagerResultObject.of(storePosition, total,
              this.storePositionMapper.pageStorePositions(storePosition));
    }
}


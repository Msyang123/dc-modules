package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.StorePosition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:门店位置Mapper类
* @author yijun
* @date 2018/09/11
*/
@Mapper
public interface StorePositionMapper {

    /**
    * Description:新增门店位置
    *
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
    int create(StorePosition storePosition);

    /**
    * Description:根据id修改门店位置
    *
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
    int updateById(StorePosition storePosition);

    /**
    * Description:根据ids删除门店位置
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找门店位置
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
    StorePosition selectById(Long id);


    /**
     * Description:根据门店id和应用类型查找门店位置
     *
     * @param storePosition
     * @return
     * @author yijun
     * @date 2018/09/11 15:32:04
     */
    StorePosition selectByStoreId(StorePosition storePosition);
    /**
     * Description:根据门店id和应用类型查找门店位置
     *
     * @param storePosition
     * @return
     * @author yijun
     * @date 2018/09/11 15:32:04
     */
    List<StorePosition> selectByStoreIds(StorePosition storePosition);

    /**
    * Description:查询门店位置列表
    *
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
     List<StorePosition> pageStorePositions(StorePosition storePosition);


    /**
    * Description: 查询门店位置总记录数
    *
    * @param storePosition
    * @return
    * @author yijun
    * @date 2018/09/11 15:32:04
    */
    long pageStorePositionCounts(StorePosition storePosition);


    StorePosition findByStoreIdAndApplicationType(Map<String,Object> storePosition);
}

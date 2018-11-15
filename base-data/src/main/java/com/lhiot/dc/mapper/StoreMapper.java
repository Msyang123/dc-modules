package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.Store;
import com.lhiot.dc.domain.StoreSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfeng created in 2018/9/22 9:09
 **/
@Mapper
@Repository
public interface StoreMapper {

    /**
     * Description:新增门店
     */
    int insert(Store store);

    /**
     * Description:根据id修改门店
     *
     */
    int update(Store store);

    /**
     * Description:根据id删除门店
     *
     */
    int deleteById(Long id);

    /**
     * Description:根据id查找门店
     *
     */
    Store selectById(Long id);

    Store selectByCode(String storeCode);
    /**
     * Description:根据门店ID集合查询门店信息
     */
    List<Store> selectListByIds(List<String> storeIds);

    /**
     * 根据门店名称和门店code模糊查询
     * @param param 查询条件
     * @return list
     */
    List<Store> selectList(StoreSearchParam param);
}

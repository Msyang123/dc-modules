package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/22 9:09
 **/
@Mapper
@Repository
public interface StoreMapper {

    /**
     * Description:新增门店
     *
     * @param store
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    int create(Store store);

    /**
     * Description:根据id修改门店
     *
     * @param store
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    int updateById(Store store);

    /**
     * Description:根据id删除门店
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    int deleteById(Long id);

    /**
     * Description:根据id查找门店
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    Store findById(Long id);

    /**
     * Description:查询门店列表
     *
     * @param store
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    List<Store> pageStores(Store store);

    /**
     * Description: 查询门店总记录数
     *
     * @param store
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    int pageStoreCounts(Store store);

    /**
     * Description:根据门店ID集合查询门店信息
     *
     * @param storeIds
     * @return
     * @author Limiaojun
     * @date 2018-05-30 12:18
     */
    List<Store> findStoreList(List<String> storeIds);

    Store findByCode(String storeCode);
}

package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.common.util.DistUtil;
import com.lhiot.dc.data.domain.LocationParam;
import com.lhiot.dc.data.domain.Store;
import com.lhiot.dc.data.domain.enums.StoreStatusEnum;
import com.lhiot.dc.data.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* Description:门店服务类
* @author Limiaojun
* @date 2018/06/02
*/
@Service
@Transactional
public class StoreService {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    /**
    * Description:新增门店
    *
    * @param store
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public int create(Store store){
        return this.storeMapper.create(store);
    }

    /**
    * Description:根据id修改门店
    *
    * @param store
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public int updateById(Store store){
        return this.storeMapper.updateById(store);
    }

    /**
    * Description:根据ids删除门店
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public int deleteByIds(String ids){
        return this.storeMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }

    /**
    * Description:根据id查找门店
    *
    * @param id
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public Store selectById(Long id){
        return this.storeMapper.selectById(id);
    }

    /**
    * Description: 查询门店总记录数
    *
    * @param store
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public long count(Store store){
        return this.storeMapper.pageStoreCounts(store);
    }

    /**
    * Description: 查询门店分页列表
    *
    * @param store
    * @return
    * @author Limiaojun
    * @date 2018/06/02 09:04:50
    */
    public PagerResultObject<Store> pageList(Store store) {
        long total = 0;
        if (store.getRows() != null && store.getRows() > 0) {
            total = this.count(store);
        }
        return PagerResultObject.of(store, total,
               this.storeMapper.pageStores(store));
    }

    /**
     * 根据门店ID集合查询门店信息
     * @param storeIds 门店ids
     * @param param 经纬度信息
     * @return
     */
    public List<Store> findStoreList(String storeIds,LocationParam param){
        String [] ids = storeIds.split(",");
        List<String> storeIdList = Arrays.asList(ids);
        List<Store> storeList = this.storeMapper.findStoreList(storeIdList);
        if(CollectionUtils.isEmpty(storeList)){
                return new ArrayList<>();
        }

        //经纬度信息不为空  排序
        if (Objects.nonNull(param) &&
                Objects.nonNull(param.getLocationX()) && Objects.nonNull(param.getLocationY())) {
            return this.storesSortByDistance(param,storeList);
        }
        return storeList;
    }


     /**
     * Description:查询所有门店列表
     *
     * @return
     * @author Limiaojun
     * @date 2018-05-30 11:35
     */
    public List<Store> findAllStores() {
        Store store = new Store();
        store.setStoreStatus(StoreStatusEnum.ENABLED);
        return this.list(store);
    }

    /**
     * Description:查询所有门店列表
     *
     * @return
     * @author Limiaojun
     * @date 2018-05-30 11:35
     */
    public List<Store> list(Store store) {
        if(!Objects.isNull(store)) {
            store.setRows(null);
        }
        return this.storeMapper.pageStores(store);
    }

    /**
     * Description: 按位置查询所有门店信息，按距离排序
     *
     * @param positionParam 位置信息对象
     * @return
     * @author Limiaojun
     * @date 2018/05/30 10:30:51
     */
    public List<Store> findStoresSortByDistance(LocationParam positionParam) {
        List<Store> listStore = this.findAllStores();

        return storesSortByDistance(positionParam, listStore);
    }

    /**
    * 按距离排序
    * @param positionParam  经纬度信息
    * @param listStore  门店集合
    * @return: java.util.List<com.lhiot.dc.data.domain.Store>
    * @Author: Limiaojun
    * @Date: 2018/8/30 11:50
    */
    private List<Store> storesSortByDistance(LocationParam positionParam, List<Store> listStore) {
        List<Store> nearbyStores=this.convertAndSortByDistance(listStore, positionParam.getLocationX(), positionParam.getLocationY());
        //将地推门店放置到第二个选项
        Store temp=null;
        for(Store item:nearbyStores){
                if(item.getStoreId()==73L){
                        temp=item;
                        nearbyStores.remove(item);
                        break;
                }
        }
        if(temp != null){
                nearbyStores.add(1, temp);
        }
        return nearbyStores;
    }

    /**
     * 根据坐标获取附近门店
     *
     * @param lat       纬度
     * @param lng       经度
     * @param km        范围
     * @param hourLimit 时间约束（大于这个时间将视频改成录播）
     * @return 门店集合
     */
    public List<Store> findUserNearbyStores(double lat, double lng, int km, int hourLimit){

        List<Store> allStore = this.findAllStores();

        List<Store> nearbyStores = new ArrayList<>();
        allStore.forEach(store -> {
            double distance = DistUtil.getDistance(Double.valueOf(store.getStoreCoordx() + ""), Double.valueOf(store.getStoreCoordy() + ""), lat, lng);
            store.setDistance(String.format("%.2f", distance));

            if (distance <= km) {
//                int hour = LocalTime.now().getHour();
//                if (hour >= hourLimit) {
//                    store.setVideoUrl((String) store.get("live_url"));
//                }
                nearbyStores.add(store);
            }
        });
        //将地推门店放置到第二个选项
        Store temp=null;
        for(Store item:nearbyStores){
                if(item.getStoreId()==73L){
                        temp=item;
                        nearbyStores.remove(item);
                        nearbyStores.add(1, temp);
                        break;
                }
        }
        return nearbyStores;
    }



    /**
     * Map转JavaBean，并按输入的经纬度坐标，按距离排序
     *
     * @param stores 要计算远近跟排序的源集合
     * @param lat    当前坐标纬度
     * @param lng    当前坐标经度
     * @return 排序后的门店信息集合
     */
    private List<Store> convertAndSortByDistance(List<Store> stores, double lat, double lng) {
        return stores.stream().map(store -> {
            double distance = DistUtil.getDistance(Double.valueOf(store.getStoreCoordx() + ""), Double.valueOf(store.getStoreCoordy() + ""), lat, lng);
            store.setDistance(String.format("%.2f", distance));
            return store;
        }).sorted((o1, o2) -> {
            int distance1 = (int) (Double.parseDouble(o1.getDistance()) * 10);
            int distance2 = (int) (Double.parseDouble(o2.getDistance()) * 10);
            return distance1 - distance2;
        }).collect(Collectors.toList());
    }
    
    public Store findByCode(String storeCode){
    	return storeMapper.findStoreByCode(storeCode);
    }
}


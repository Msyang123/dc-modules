package com.lhiot.dc.base.service;

import com.leon.microx.util.Maps;
import com.lhiot.dc.base.common.LocationParam;
import com.lhiot.dc.base.common.PagerResultObject;
import com.lhiot.dc.base.common.util.DistUtil;
import com.lhiot.dc.base.mapper.StoreMapper;
import com.lhiot.dc.base.mapper.StorePositionMapper;
import com.lhiot.dc.base.model.Store;
import com.lhiot.dc.base.model.StorePosition;
import com.lhiot.dc.base.model.type.ApplicationType;
import com.lhiot.dc.base.model.type.StoreStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zhangfeng created in 2018/9/22 9:03
 **/
@Service
@Slf4j
@Transactional
public class StoreService {

    private StoreMapper storeMapper;
    private StorePositionMapper storePositionMapper;

    public StoreService(StoreMapper storeMapper, StorePositionMapper storePositionMapper) {
        this.storeMapper = storeMapper;
        this.storePositionMapper = storePositionMapper;
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
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    public int deleteById(Long id){
        return this.storeMapper.deleteById(id);
    }

    /**
     * Description:根据id查找门店
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018/06/02 09:04:50
     */
    public Store selectById(Long id,ApplicationType applicationType){
        Store store = this.storeMapper.findById(id);

        if(Objects.isNull(store))
            return null;
        if(Objects.nonNull(applicationType)) {
            StorePosition searchStorePosition = new StorePosition();
            searchStorePosition.setStoreId(store.getId());
            searchStorePosition.setApplicationType(applicationType);
            store.setStorePosition(this.storePositionMapper.selectByStoreId(searchStorePosition));
        }
        return store;
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
    public List<Store> findStoreList(String storeIds, LocationParam param){
        String [] ids = storeIds.split(",");
        List<String> storeIdList = Arrays.asList(ids);
        List<Store> storeList = this.storeMapper.findStoreList(storeIdList);
        if(CollectionUtils.isEmpty(storeList)){
            return new ArrayList<>();
        }

        //经纬度信息不为空  排序
        if (Objects.nonNull(param) &&
                Objects.nonNull(param.getLat()) && Objects.nonNull(param.getLng())) {
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
    public List<Store> findAllStores(ApplicationType applicationType) {
        Store store = new Store();
        store.setStatus(StoreStatus.ENABLED);
        return this.list(store,applicationType);
    }

    /**
     * Description:查询所有门店列表
     *
     * @return
     * @author Limiaojun
     * @date 2018-05-30 11:35
     */
    public List<Store> list(Store store,ApplicationType applicationType) {
        if(!Objects.isNull(store)) {
            store.setRows(null);
        }
        List<Store> storeList = this.storeMapper.pageStores(store);

        if(Objects.nonNull(storeList)&&Objects.nonNull(applicationType)) {
            List<Long> storeIds = storeList.parallelStream()
                    .map(Store::getId)
                    .collect(Collectors.toList());

            StorePosition storePosition=new StorePosition();
            storePosition.setApplicationType(applicationType);
            storePosition.setStoreIds(storeIds);
            //批量查询门店位置
            List<StorePosition> storePositionList = this.storePositionMapper.selectByStoreIds(storePosition);

            //给每个门店赋值门店位置
            storeList.forEach(storeItem->
                    storePositionList.stream().filter(storePositionItem -> Objects.equals(storeItem.getId(),storePositionItem.getStoreId()))
                            .forEach(storePositionItem ->storeItem.setStorePosition(storePositionItem))
            );
        }
        return storeList;
    }

    /**
     * Description: 按位置查询所有门店信息，按距离排序
     *
     * @param positionParam 位置信息对象
     * @return
     * @author Limiaojun
     * @date 2018/05/30 10:30:51
     */
    public List<Store> findStoresSortByDistance(LocationParam positionParam,ApplicationType applicationType) {
        List<Store> listStore = this.findAllStores(applicationType);

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
        return listStore.stream().map(store -> {
            double distance = DistUtil.getDistance(store.getStorePosition().getLat(), store.getStorePosition().getLng(), positionParam.getLat(), positionParam.getLng());
            store.setDistance(String.format("%.2f", distance));
            return store;
        }).sorted((o1, o2) -> {
            int distance1 = (int) (Double.parseDouble(o1.getDistance()) * 10);
            int distance2 = (int) (Double.parseDouble(o2.getDistance()) * 10);
            return distance1 - distance2;
        }).collect(Collectors.toList());
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
    public List<Store> findUserNearbyStores(double lat, double lng, int km, int hourLimit,ApplicationType applicationType){

        List<Store> allStore = this.findAllStores(applicationType);

        List<Store> nearbyStores = new ArrayList<>();
        allStore.forEach(store -> {
            double distance = DistUtil.getDistance(store.getStorePosition().getLat(), store.getStorePosition().getLng(), lat, lng);
            store.setDistance(String.format("%.2f", distance));

            if (distance <= km) {
//                int hour = LocalTime.now().getHour();
//                if (hour >= hourLimit) {
//                    store.setVideoUrl((String) store.get("live_url"));
//                }
                nearbyStores.add(store);
            }
        });
        return nearbyStores;
    }

    public Store findById(Long storeId, ApplicationType applicationType) {
        Store store = storeMapper.findById(storeId);
        if (Objects.isNull(store)) {
            return null;
        }
        if (Objects.nonNull(applicationType)) {
            StorePosition storePosition = storePositionMapper.findByStoreIdAndApplicationType(
                    Maps.of("storeId", store.getId(), "applicationType", applicationType));
            store.setStorePosition(storePosition);
        }
        return store;
    }

    public Store findByCode(String storeCode, ApplicationType applicationType) {
        Store store = storeMapper.findByCode(storeCode);
        if (Objects.isNull(store)) {
            return null;
        }
        if (Objects.nonNull(applicationType)) {
            StorePosition storePosition = storePositionMapper.findByStoreIdAndApplicationType(
                    Maps.of("storeId", store.getId(), "applicationType", applicationType));
            store.setStorePosition(storePosition);
        }
        return store;
    }
}

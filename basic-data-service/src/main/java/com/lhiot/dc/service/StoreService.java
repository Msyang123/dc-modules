package com.lhiot.dc.service;

import com.leon.microx.util.Maps;
import com.leon.microx.util.StringUtils;
import com.leon.microx.web.result.Pages;
import com.lhiot.dc.common.LocationParam;
import com.lhiot.dc.common.util.DistUtil;
import com.lhiot.dc.domain.Store;
import com.lhiot.dc.domain.StorePosition;
import com.lhiot.dc.domain.StoreSearchParam;
import com.lhiot.dc.domain.type.ApplicationType;
import com.lhiot.dc.domain.type.StoreStatus;
import com.lhiot.dc.mapper.StoreMapper;
import com.lhiot.dc.mapper.StorePositionMapper;
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
 * @author zhangfeng created in 2018/9/22 9:03
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
     * @param store 门店信息
     * @return int
     */
    public int create(Store store) {
        return this.storeMapper.create(store);
    }

    /**
     * Description:根据id修改门店
     *
     * @param store 门店信息
     */
    public int updateById(Store store) {
        return this.storeMapper.updateById(store);
    }

    /**
     * Description:根据ids删除门店
     *
     * @param id 门店Id
     */
    public int deleteById(Long id) {
        return this.storeMapper.deleteById(id);
    }

    /**
     * Description:根据id查找门店
     *
     * @param id 门店Id
     */
    public Store selectById(Long id, ApplicationType applicationType) {
        Store store = this.storeMapper.findById(id);

        if (Objects.isNull(store)) {
            return null;
        }
        if (Objects.nonNull(applicationType)) {
            StorePosition searchStorePosition = new StorePosition();
            searchStorePosition.setStoreId(store.getId());
            searchStorePosition.setApplicationType(applicationType);
            store.setStorePosition(this.storePositionMapper.selectByStoreId(searchStorePosition));
        }
        return store;
    }


    /**
     * Description: 查询门店分页列表
     *
     * @param store 门店信息
     */
    public Pages<Store> pageList(Store store) {
        return Pages.of(this.storeMapper.pageStoreCounts(store),
                this.storeMapper.pageStores(store));
    }

    /**
     * 根据门店ID集合查询门店信息
     *
     * @param param 查询条件
     * @return List
     */
    public List<Store> findStoreList(StoreSearchParam param) {
        List<String> storeIdList = Arrays.asList(StringUtils.tokenizeToStringArray(param.getStoreIds(), ","));
        List<Store> storeList = this.storeMapper.findStoreList(storeIdList);
        if (CollectionUtils.isEmpty(storeList)) {
            return new ArrayList<>();
        }
        this.toStoreList(storeList,param.getApplicationType());
        //经纬度信息不为空  排序
        if (Objects.nonNull(param.getLat()) && Objects.nonNull(param.getLng())) {
            return this.storesSortByDistance(param.getLat(), param.getLng(), storeList);
        }
        return storeList;
    }


    /**
     * Description:查询所有门店列表
     */
    public List<Store> findAllStores(ApplicationType applicationType) {
        Store store = new Store();
        store.setStatus(StoreStatus.ENABLED);
        return this.list(store, applicationType);
    }

    /**
     * Description:查询所有门店列表
     */
    public List<Store> list(Store store, ApplicationType applicationType) {
        if (!Objects.isNull(store)) {
            store.setRows(null);
        }
        List<Store> storeList = this.storeMapper.pageStores(store);

        if (Objects.nonNull(storeList) && Objects.nonNull(applicationType)) {
            //批量查询门店位置
            //给每个门店赋值门店位置
            this.toStoreList(storeList,applicationType);
        }
        return storeList;
    }

    private void toStoreList(List<Store> storeList,ApplicationType applicationType) {
        List<Long> storeIds = storeList.parallelStream().map(Store::getId).collect(Collectors.toList());

        StorePosition storePosition = new StorePosition();
        storePosition.setApplicationType(applicationType);
        storePosition.setStoreIds(storeIds);
        List<StorePosition> storePositionList = this.storePositionMapper.selectByStoreIds(storePosition);

        //给每个门店赋值门店位置
        storeList.forEach(storeItem ->
                storePositionList.stream().filter(storePositionItem -> Objects.equals(storeItem.getId(), storePositionItem.getStoreId()))
                        .forEach(storeItem::setStorePosition)
        );
    }

    /**
     * Description: 按位置查询所有门店信息，按距离排序
     *
     * @param positionParam 位置信息对象
     * @return List
     * @author Limiaojun
     */
    public List<Store> findStoresSortByDistance(LocationParam positionParam, ApplicationType applicationType) {
        List<Store> listStore = this.findAllStores(applicationType);

        return storesSortByDistance(positionParam.getLat(), positionParam.getLng(), listStore);
    }

    /**
     * 按距离排序
     *
     * @param lat       纬度
     * @param lng       经度
     * @param listStore 门店集合
     * @return java.util.List<com.lhiot.dc.data.domain.Store>
     */
    private List<Store> storesSortByDistance(double lat, double lng, List<Store> listStore) {
        return listStore.stream().peek(store -> {
            double distance = DistUtil.getDistance(store.getStorePosition().getLat(), store.getStorePosition().getLng(), lat, lng);
            store.setDistance(String.format("%.2f", distance));
        }).sorted((o1, o2) -> {
            int distance1 = (int) (Double.parseDouble(o1.getDistance()) * 10);
            int distance2 = (int) (Double.parseDouble(o2.getDistance()) * 10);
            return distance1 - distance2;
        }).collect(Collectors.toList());
    }

    /**
     * 根据坐标获取附近门店
     *
     * @param km        范围
     * @param hourLimit 时间约束（大于这个时间将视频改成录播）
     * @return 门店集合
     */
    public List<Store> findUserNearbyStores(StoreSearchParam searchParam, int km, int hourLimit) {

        List<Store> allStore = this.findAllStores(searchParam.getApplicationType());

        List<Store> nearbyStores = new ArrayList<>();
        allStore.forEach(store -> {
            double distance = DistUtil.getDistance(store.getStorePosition().getLat(), store.getStorePosition().getLng(), searchParam.getLat(), searchParam.getLng());
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

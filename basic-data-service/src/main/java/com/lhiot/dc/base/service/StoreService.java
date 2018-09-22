package com.lhiot.dc.base.service;

import com.leon.microx.util.Maps;
import com.lhiot.dc.base.mapper.StoreMapper;
import com.lhiot.dc.base.mapper.StorePositionMapper;
import com.lhiot.dc.base.model.Store;
import com.lhiot.dc.base.model.StorePosition;
import com.lhiot.dc.base.model.type.ApplicationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

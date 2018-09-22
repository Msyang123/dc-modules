package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.StorePosition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Author zhangfeng created in 2018/9/22 9:09
 **/
@Mapper
@Repository
public interface StorePositionMapper {

    StorePosition findByStoreIdAndApplicationType(Map<String,Object> paramMap);


}

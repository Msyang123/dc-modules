package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author zhangfeng created in 2018/9/22 9:09
 **/
@Mapper
@Repository
public interface StoreMapper {

    Store findById(Long id);

    Store findByCode(String code);
}

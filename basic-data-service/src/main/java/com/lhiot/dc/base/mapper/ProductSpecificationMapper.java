package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangfeng created in 2018/9/20 11:08
 **/
@Mapper
@Repository
public interface ProductSpecificationMapper {

    int countByProductId(Long productId);

    List<Map<String,Object>> findProductIdList(List<String> productList);
}

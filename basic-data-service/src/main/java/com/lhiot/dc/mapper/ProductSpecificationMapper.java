package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductResult;
import com.lhiot.dc.domain.ProductSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangfeng created in 2018/9/20 11:08
 **/
@Mapper
@Repository
public interface ProductSpecificationMapper {

    int insert(ProductSpecification productSpecification);

    int updateSpecification(ProductSpecification productSpecification);

    ProductResult findById(Long id);

    List<ProductResult> findByIds(List<String> idList);

    int countByProductId(Long productId);

    List<Map<String, Object>> findProductIdList(List<String> productList);

    List<Map<String, Object>> findBaseSpecificationByProductIds(List<String> productId);
}

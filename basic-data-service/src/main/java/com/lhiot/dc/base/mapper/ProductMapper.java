package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/20 11:08
 **/
@Mapper
@Repository
public interface ProductMapper {

    int insert(Product product);

    int updateById(Product product);

    int deleteById(Long productId);

    int batchDeleteByIds(List<String> productIdList);
}

package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductShelfResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangfeng created in 2018/9/20 14:44
 **/
@Mapper
@Repository
public interface ProductShelfMapper {

    ProductShelfResult findById(Long shelfId);

    List<ProductShelfResult> findListByIds(List<String> shelfIdList);

    int insert(ProductShelf productShelf);

    int update(ProductShelf productShelf);
}

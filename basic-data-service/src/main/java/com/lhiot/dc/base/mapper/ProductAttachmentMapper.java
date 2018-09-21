package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.ProductAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/20 14:44
 **/
@Mapper
@Repository
public interface ProductAttachmentMapper {
    int insert(ProductAttachment attachment);

    int deleteByProductId(Long productId);

    int batchDeleteByProductIds(List<String> productIdList);

    int updateById(ProductAttachment attachment);

    List<ProductAttachment> findByProductId(Long productId);
    List<ProductAttachment> findAttachmentByProductIdList(List<String> productIdList);

}

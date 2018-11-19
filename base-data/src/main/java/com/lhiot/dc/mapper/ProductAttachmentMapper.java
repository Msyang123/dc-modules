package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author xiaojian created in 2018/11/13 8:51
 **/
@Mapper
@Repository
public interface ProductAttachmentMapper {
    /**
     * 新增商品附件
     *
     * @param attachment
     * @return 执行结果
     */
    int insert(ProductAttachment attachment);

    /**
     * 新增商品附件集合
     *
     * @param attachments
     * @return 商品附件id
     */
    int insertList(List<ProductAttachment> attachments);


    /**
     * 根据商品id，删除此商品所有附件
     *
     * @param productId
     * @return 执行结果
     */
    int deleteByProductId(Long productId);

    /**
     * 根据商品id集合，批量删除商品附件
     *
     * @param ids
     * @return 执行结果
     */
    int batchDeleteByProductIds(@Param("productIds") String productIds);

    /**
     * 根据商品id，查找商品附件集合
     *
     * @param productId
     * @return 商品附件集合
     */
    List<ProductAttachment> findByProductId(Long productId);


}
package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.ProductAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian created in 2018/11/13 8:51
 **/
@Mapper
@Repository
public interface ProductAttachmentMapper {
    /**
     * 新增商品附件
     *
     * @param attachment 商品附件对象
     * @return 执行结果
     */
    int insert(ProductAttachment attachment);

    /**
     * 新增商品附件集合
     *
     * @param attachments 商品附件集合
     * @return 商品附件id
     */
    int insertList(List<ProductAttachment> attachments);


    /**
     * 根据商品id，删除此商品所有附件
     *
     * @param productId 商品id
     * @return 执行结果
     */
    int deleteByProductId(Long productId);

    /**
     * 根据商品id集合，批量删除商品附件
     *
     * @param productIds 商品id集合
     * @return 执行结果
     */
    int batchDeleteByProductIds(@Param("productIds") String productIds);

    /**
     * 根据商品id，查找商品附件集合
     *
     * @param productId 商品id
     * @return 商品附件集合
     */
    List<ProductAttachment> findByProductId(Long productId);


}
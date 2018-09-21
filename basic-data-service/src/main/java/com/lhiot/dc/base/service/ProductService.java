package com.lhiot.dc.base.service;

import com.lhiot.dc.base.mapper.ProductAttachmentMapper;
import com.lhiot.dc.base.mapper.ProductMapper;
import com.lhiot.dc.base.model.Product;
import com.lhiot.dc.base.model.ProductAttachment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/20 11:33
 **/
@Service
@Slf4j
@Transactional
public class ProductService {
    private ProductMapper productMapper;
    private ProductAttachmentMapper attachmentMapper;

    public ProductService(ProductMapper productMapper, ProductAttachmentMapper attachmentMapper) {
        this.productMapper = productMapper;
        this.attachmentMapper = attachmentMapper;
    }

    public boolean addProduct(Product product) {
        return productMapper.insert(product) > 0;
    }

    public boolean updateById(Product product){
        return productMapper.updateById(product) > 0;
    }

    public boolean addProductAttachment(ProductAttachment attachment) {
        return attachmentMapper.insert(attachment) > 0;
    }

    /**
     * 根据商品ID删除商品
     * @param productId
     * @return
     */
    public int deleteById(Long productId){
        attachmentMapper.deleteByProductId(productId);
       return productMapper.deleteById(productId);
    }

    /**
     * 根据商品ID集合批量删除
     * @param productIdList
     * @return
     */
    public int batchDeleteById(List<String> productIdList){
        attachmentMapper.batchDeleteByProductIds(productIdList);
        return productMapper.batchDeleteByIds(productIdList);
    }

    public boolean updateById(ProductAttachment attachment){
       return attachmentMapper.updateById(attachment) > 0;
    }
}

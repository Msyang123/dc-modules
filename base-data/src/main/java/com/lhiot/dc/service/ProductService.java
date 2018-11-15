package com.lhiot.dc.service;

import com.lhiot.dc.domain.Product;
import com.lhiot.dc.domain.ProductAttachment;
import com.lhiot.dc.mapper.ProductAttachmentMapper;
import com.lhiot.dc.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaojian  created in  2018/11/12 17:09
 */
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

    /**
     * 新增商品
     *
     * @param Product对象
     * @return 新增商品Id
     */
    public Long addProduct(Product product) {
        int result=productMapper.insert(product);
        //保存商品附件
        List<ProductAttachment> attachments = product.getAttachments();
        if (result > 0 && attachments != null && !attachments.isEmpty()) {
            attachments = attachments.stream()
                    .map(attach -> {
                        attach.setProductId(product.getId());
                        return attach;
                    })
                    .collect(Collectors.toList());
            attachmentMapper.insertList(attachments);
        }
        return product.getId();
    }

    /**
     * 修改商品
     *
     * @param Product对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(Product product) {
        //查询出原有商品附件 进行排序
        List<ProductAttachment> oldAttachments = attachmentMapper.findByProductId(product.getId());
        oldAttachments = oldAttachments != null ? oldAttachments.stream()
                .sorted(Comparator.comparing(ProductAttachment::getUrl))
                .collect(Collectors.toList()) : new ArrayList();

        //得到最新的商品附件 进行排序 和设置商品id值
        List<ProductAttachment> newAttachments = product.getAttachments();
        newAttachments = newAttachments != null ? newAttachments.stream()
                .map(attach -> {
                    attach.setProductId(product.getId());
                    return attach;
                })
                .sorted(Comparator.comparing(ProductAttachment::getUrl))
                .collect(Collectors.toList()) : new ArrayList();

        //和原来的商品附件对比  如果有改动则先清除原有附件 再重新保存
        if (!oldAttachments.equals(newAttachments)) {
            attachmentMapper.deleteByProductId(product.getId());

            if (newAttachments != null && !newAttachments.isEmpty()) {
                attachmentMapper.insertList(newAttachments);
            }
        }
        return productMapper.updateById(product) > 0;
    }


    /**
     * 根据商品ID查找单个商品
     *
     * @param productId
     * @return 商品对象
     */
    public Product findById(Long productId) {
        Product product = productMapper.findById(productId);
        List<ProductAttachment> attachment = attachmentMapper.findByProductId(productId);
        product.setAttachments(attachment);
        return product;
    }


    /**
     * 根据商品ID删除商品
     *
     * @param productId
     * @return 执行结果 true 或者 false
     */
    public boolean delete(Long productId) {
        //先删除商品所属附件
        attachmentMapper.deleteByProductId(productId);
        return productMapper.deleteById(productId) > 0;
    }


    /**
     * 根据商品ID集合批量删除商品
     *
     * @param productIdList
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteById(List<String> productIdList) {
        //先删除商品所属附件
        attachmentMapper.batchDeleteByProductIds(productIdList);
        return productMapper.batchDeleteByIds(productIdList) > 0;
    }


}

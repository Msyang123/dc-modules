package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.Product;
import com.lhiot.dc.entity.ProductAttachment;
import com.lhiot.dc.model.ProductParam;
import com.lhiot.dc.mapper.ProductAttachmentMapper;
import com.lhiot.dc.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
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
     * @param product 商品对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips addProduct(Product product) {
        // 幂等添加
        Product po = productMapper.findByCode(product.getCode());
        if (Objects.nonNull(po)) {
            return Tips.warn("商品code重复，添加失败.");
        }

        product.setCreateAt(Date.from(Instant.now()));
        int result = productMapper.insert(product);
        //保存商品附件
        List<ProductAttachment> attachments = product.getAttachments();
        if (result > 0 && Objects.nonNull(attachments) && !attachments.isEmpty()) {
            attachments = attachments.stream()
                    .peek(attach -> attach.setProductId(product.getId()))
                    .collect(Collectors.toList());
            attachmentMapper.insertList(attachments);
        }
        return Tips.info(product.getId() + "");
    }

    /**
     * 修改商品
     *
     * @param product 商品对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(Product product) {
        //查询出原有商品附件 进行排序
        List<ProductAttachment> oldAttachments = attachmentMapper.findByProductId(product.getId());
        oldAttachments = Objects.nonNull(oldAttachments) ? oldAttachments.stream()
                .sorted(Comparator.comparing(ProductAttachment::getUrl))
                .collect(Collectors.toList()) : new ArrayList();

        //得到最新的商品附件 进行排序 和设置商品id值
        List<ProductAttachment> newAttachments = product.getAttachments();
        newAttachments = Objects.nonNull(newAttachments) ? newAttachments.stream()
                .peek(attach -> attach.setProductId(product.getId()))
                .sorted(Comparator.comparing(ProductAttachment::getUrl))
                .collect(Collectors.toList()) : new ArrayList();

        //和原来的商品附件对比  如果有改动则先清除原有附件 再重新保存
        if (!oldAttachments.equals(newAttachments)) {
            attachmentMapper.deleteByProductId(product.getId());

            if (!newAttachments.isEmpty()) {
                attachmentMapper.insertList(newAttachments);
            }
        }
        return productMapper.updateById(product) > 0;
    }


    /**
     * 根据商品ID查找单个商品
     *
     * @param productId 商品ID
     * @return 商品对象
     */
    public Product findById(Long productId) {
        return productMapper.findById(productId);
    }


    /**
     * 根据商品ID集合批量删除商品
     *
     * @param ids 商品ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        //先删除商品所属附件
        attachmentMapper.batchDeleteByProductIds(ids);
        return productMapper.deleteByIds(ids) > 0;
    }


    /**
     * 根据条件查询商品信息列表
     *
     * @param param 参数
     * @return 商品信息列表
     */
    public Pages<Product> findList(ProductParam param) {
        List<Product> list = productMapper.findList(param);
        int total = param.getPageFlag() ? productMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

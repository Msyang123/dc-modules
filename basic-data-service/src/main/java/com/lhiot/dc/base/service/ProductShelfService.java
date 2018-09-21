package com.lhiot.dc.base.service;

import com.lhiot.dc.base.mapper.ProductAttachmentMapper;
import com.lhiot.dc.base.mapper.ProductShelfMapper;
import com.lhiot.dc.base.model.ProductAttachment;
import com.lhiot.dc.base.model.ProductShelfResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/21 10:24
 **/
@Service
@Slf4j
@Transactional
public class ProductShelfService {
    private ProductShelfMapper shelfMapper;
    private ProductAttachmentMapper attachmentMapper;

    public ProductShelfService(ProductShelfMapper shelfMapper, ProductAttachmentMapper attachmentMapper) {
        this.shelfMapper = shelfMapper;
        this.attachmentMapper = attachmentMapper;
    }

    @Nullable
    public ProductShelfResult findById(Long shelfId){
       return shelfMapper.findById(shelfId);
    }

    @Nullable
    public List<ProductShelfResult> findListByIds(List<String> shelfIdList){
        return shelfMapper.findListByIds(shelfIdList);
    }

    @Nullable
    public List<ProductAttachment> findAttachmentByProductId(Long productId){
        return attachmentMapper.findByProductId(productId);
    }

    @Nullable
    public List<ProductAttachment> findAttachmentByProductIdList(List<String> productIdList){
        return attachmentMapper.findAttachmentByProductIdList(productIdList);
    }

    /**
     * 组装返回数据
     * @param attachmentList
     * @param result
     * @return
     */
    public List<ProductShelfResult> assemblyData(List<ProductAttachment> attachmentList,List<ProductShelfResult> result){
        if (CollectionUtils.isEmpty(attachmentList)){
            for (ProductShelfResult shelfResult : result){
                shelfResult.setAttachmentList(new ArrayList<>());
            }
            return result;
        }
        for (ProductShelfResult shelfResult : result){
            List<ProductAttachment> attachments = new ArrayList<>();
            for (ProductAttachment productAttachment : attachmentList){
                if (shelfResult.getProductId().equals(productAttachment.getProductId())){
                    attachments.add(productAttachment);
                }
            }
            shelfResult.setAttachmentList(attachments);
        }
        return result;
    }
}

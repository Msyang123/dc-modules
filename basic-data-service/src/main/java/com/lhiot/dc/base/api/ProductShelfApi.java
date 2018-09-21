package com.lhiot.dc.base.api;

import com.leon.microx.support.result.Multiple;
import com.leon.microx.util.StringUtils;
import com.lhiot.dc.base.model.ProductAttachment;
import com.lhiot.dc.base.model.ProductShelfResult;
import com.lhiot.dc.base.service.ProductShelfService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author zhangfeng created in 2018/9/21 9:26
 **/
@RestController
@Slf4j
@RequestMapping("/product/shelf")
public class ProductShelfApi {

    private ProductShelfService shelfService;

    public ProductShelfApi(ProductShelfService shelfService) {
        this.shelfService = shelfService;
    }

    @ApiOperation("根据商品上架Id查询详细信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "商品上架Id", dataType = "Long", required = true)
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long shelfId) {
        ProductShelfResult result = shelfService.findById(shelfId);
        if (Objects.isNull(result)) {
            return ResponseEntity.badRequest().body("没有查询到该商品上架信息！");
        }
        List<ProductAttachment> attachmentList = shelfService.findAttachmentByProductId(result.getProductId());
        result.setAttachmentList(attachmentList);
        if (CollectionUtils.isEmpty(attachmentList)) {
            result.setAttachmentList(new ArrayList<>());
        }
        return ResponseEntity.ok().body(result);
    }

    @ApiOperation("根据商品上架Id集合查询详细信息")
    @ApiImplicitParam(paramType = "query", name = "shelfIds", value = "商品上架Id集合字符串", dataType = "String", required = true)
    @GetMapping("/list")
    public ResponseEntity findListByIds(@RequestParam("shelfIds") String shelfIds) {

        List<ProductShelfResult> result = shelfService.findListByIds(Arrays.asList(StringUtils.tokenizeToStringArray(shelfIds,",")));
        if (CollectionUtils.isEmpty(result)) {
            return ResponseEntity.ok().body(Multiple.of(new ArrayList<>()));
        }
        List<String> productIdList = result.parallelStream().map(ProductShelfResult::getProductId).map(String::valueOf).collect(Collectors.toList());
        List<ProductAttachment> attachmentList = shelfService.findAttachmentByProductIdList(productIdList);
        result = shelfService.assemblyData(attachmentList, result);
        return ResponseEntity.ok().body(Multiple.of(result));
    }
}

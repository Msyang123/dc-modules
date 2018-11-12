package com.lhiot.dc.api;

import com.leon.microx.web.result.Multiple;
import com.leon.microx.util.StringUtils;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductAttachment;
import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductShelfResult;
import com.lhiot.dc.mapper.ProductShelfMapper;
import com.lhiot.dc.service.ProductShelfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @author zhangfeng created in 2018/9/21 9:26
 **/
@RestController
@Slf4j
@RequestMapping("/product-shelf")
@Api("商品上架接口")
public class ProductShelfApi {

    private ProductShelfService shelfService;
    private ProductShelfMapper shelfMapper;

    public ProductShelfApi(ProductShelfService shelfService, ProductShelfMapper shelfMapper) {
        this.shelfService = shelfService;
        this.shelfMapper = shelfMapper;
    }

    @ApiOperation(value = "根据商品上架Id查询详细信息",response = ProductShelfResult.class)
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

    @ApiOperation(value = "根据商品上架Id集合查询详细信息",response = ProductShelfResult.class,responseContainer = "Set")
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

    @ApiOperation("上架商品")
    @ApiImplicitParam(paramType = ApiParamType.BODY,name = "productShelf",value = "商品上架信息",dataType = "ProductShelf",required = true)
    @ApiHideBodyProperty("id")
    @PostMapping("")
    public ResponseEntity createShelfProduct(@RequestBody ProductShelf productShelf){
        if (shelfMapper.insert(productShelf) < 0){
            return ResponseEntity.badRequest().body("上架商品失败！");
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation("修改上架商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY,name = "productShelf",value = "商品上架信息",dataType = "ProductShelf",required = true),
            @ApiImplicitParam(paramType = ApiParamType.PATH,name = "id",value = "上架Id",dataType = "Long",required = true)
    })
    @ApiHideBodyProperty("id")
    @PutMapping("/{id}")
    public ResponseEntity updateShelfProduct(@PathVariable("id") Long id,@RequestBody ProductShelf productShelf){
        productShelf.setId(id);
        if (shelfMapper.update(productShelf) < 0){
            return ResponseEntity.badRequest().body("上架商品失败！");
        }
        return ResponseEntity.ok().build();
    }
}

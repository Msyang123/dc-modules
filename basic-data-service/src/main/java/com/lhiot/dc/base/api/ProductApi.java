package com.lhiot.dc.base.api;

import com.leon.microx.support.swagger.ApiHideBodyProperty;
import com.lhiot.dc.base.model.Product;
import com.lhiot.dc.base.model.ProductAttachment;
import com.lhiot.dc.base.service.ProductService;
import com.lhiot.dc.base.service.ProductSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhangfeng created in 2018/9/20 11:07
 **/
@RestController
@Slf4j
@RequestMapping("/product")
@Api(description = "商品接口")
public class ProductApi {

    private ProductService productService;
    private ProductSpecificationService specificationService;

    public ProductApi(ProductService productService, ProductSpecificationService specificationService) {
        this.productService = productService;
        this.specificationService = specificationService;
    }

    @ApiOperation("添加商品")
    @PostMapping("")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody Product product) {
        return productService.addProduct(product) ? ResponseEntity.ok().body(product.getId()) : ResponseEntity.badRequest().body("添加商品失败！");
    }

    @ApiOperation("修改商品信息")
    @PutMapping("")
    @ApiHideBodyProperty("createAt")
    public ResponseEntity update(@RequestBody Product product) {
        return productService.updateById(product) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }

    @ApiOperation("根据Id删除商品")
    @ApiImplicitParam(paramType = "path", name = "id", value = "商品Id", dataType = "Long", required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long productId) {
        boolean flag = specificationService.countSpecificationByProductId(productId);
        if (flag) {
            return ResponseEntity.badRequest().body("该商品存在规格信息不可删除！");
        }
        productService.deleteById(productId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("根据Id集合批量删除商品")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "商品Id", dataType = "String", required = true)
    @DeleteMapping
    public ResponseEntity batchDelete(@RequestParam("ids") String productIds) {
        List<String> productIdList = Arrays.asList(productIds.split(","));
        List<String> searchProductIdList = specificationService.findProductIdListByProductId(productIdList);
        if (!CollectionUtils.isEmpty(searchProductIdList)) {
            return ResponseEntity.badRequest().body("以下商品存在规格不可删除：" + productIdList.toString());
        }
        productService.batchDeleteById(productIdList);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("添加商品附件信息")
    @PostMapping("/attachment")
    @ApiHideBodyProperty("id")
    public ResponseEntity createAttachment(@RequestBody ProductAttachment productAttachment) {
        return productService.addProductAttachment(productAttachment) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("添加商品附加信息失败");
    }

    @ApiOperation("修改商品附件信息")
    @PutMapping("/attachment")
    @ApiHideBodyProperty("productId")
    public ResponseEntity updateAttachment(@RequestBody ProductAttachment productAttachment) {
        return productService.updateById(productAttachment) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改商品附加信息失败");
    }

}

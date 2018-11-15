package com.lhiot.dc.api;


import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Multiple;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductSpecification;
import com.lhiot.dc.service.ProductShelfService;
import com.lhiot.dc.service.ProductSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaojian created in 2018/11/13 11:55
 **/
@RestController
@Slf4j
@Api("商品规格接口")
public class ProductSpecificationApi {

    private ProductSpecificationService productSpecificationService;
    private ProductShelfService shelfService;

    public ProductSpecificationApi(ProductSpecificationService productSpecificationService,ProductShelfService shelfService) {
        this.productSpecificationService = productSpecificationService;
        this.shelfService=shelfService;
    }


    @ApiOperation("添加商品规格")
    @PostMapping("/product/specifications")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody ProductSpecification productSpecification) {
        Long Id = productSpecificationService.addProductSpecification(productSpecification);
        return Id > 0 ? ResponseEntity.created(URI.create("/product/specification/" + Id)).body(Maps.of("id", Id)) : ResponseEntity.badRequest().body("添加商品规格失败！");
    }


    @ApiOperation("修改商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "商品规格Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = "body", name = "productSpecification", value = "商品规格信息", dataType = "ProductSpecification", required = true)
    })
    @PutMapping("/product/specifications/{id}")
    @ApiHideBodyProperty("createAt")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProductSpecification productSpecification) {
        productSpecification.setId(id);
        return productSpecificationService.update(productSpecification) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }


    @ApiOperation(value = "根据Id查找商品规格", response = ProductSpecification.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品规格Id", dataType = "Long", required = true)
    @GetMapping("/product/specifications/{id}")
    public ResponseEntity single(@PathVariable("id") Long specificationId) {
        return ResponseEntity.ok().body(productSpecificationService.findById(specificationId));
    }


    @ApiOperation("根据Id删除商品规格")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品规格Id", dataType = "Long", required = true)
    @DeleteMapping("/product/specifications/{id}")
    public ResponseEntity delete(@PathVariable("id") Long specificationId) {
        List<ProductShelf> productShelfList=shelfService.findListBySpecificationId(specificationId);
        if (productShelfList != null && !productShelfList.isEmpty()) {
            return ResponseEntity.badRequest().body("该商品规格存在上架信息不可删除！");
        }
        return productSpecificationService.delete(specificationId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


    @ApiOperation("根据商品Id删除所属商品规格集合")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "productId", value = "商品Id", dataType = "Long", required = true)
    @DeleteMapping("/product/{productId}/specifications")
    public ResponseEntity deleteByProductId(@PathVariable("productId") Long productId) {
        List<ProductShelf> productShelfList=shelfService.findListByProductId(productId);
        if (productShelfList != null && !productShelfList.isEmpty()) {
            return ResponseEntity.badRequest().body("该商品下的商品规格存在上架信息不可删除！");
        }
        ;
        return productSpecificationService.deleteByProductId(productId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


    @ApiOperation(value = "根据商品Id查找商品规格集合", response = ProductSpecification.class, responseContainer = "Set")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "productId", value = "商品Id", dataType = "Long", required = true)
    @GetMapping("/product/{productId}/specifications")
    public ResponseEntity findListByProductId(@PathVariable("productId") Long productId) {
        List<ProductSpecification> specificationList = productSpecificationService.findListByProductId(productId);
        if (CollectionUtils.isEmpty(specificationList)) {
            return ResponseEntity.ok().body(Multiple.of(new ArrayList<>()));
        }
        return ResponseEntity.ok().body(specificationList);
    }


}

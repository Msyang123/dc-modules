package com.lhiot.dc.api;


import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.ProductShelf;
import com.lhiot.dc.entity.ProductSpecification;
import com.lhiot.dc.model.ProductSpecificationParam;
import com.lhiot.dc.service.ProductShelfService;
import com.lhiot.dc.service.ProductSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author xiaojian created in 2018/11/13 11:55
 **/
@RestController
@Slf4j
@Api(tags = {"商品规格接口"})
public class ProductSpecificationApi {

    private ProductSpecificationService productSpecificationService;
    private ProductShelfService shelfService;

    public ProductSpecificationApi(ProductSpecificationService productSpecificationService, ProductShelfService shelfService) {
        this.productSpecificationService = productSpecificationService;
        this.shelfService = shelfService;
    }


    @ApiOperation("添加商品规格")
    @PostMapping("/product-specifications")
    @ApiHideBodyProperty({"id", "product"})
    public ResponseEntity create(@RequestBody ProductSpecification productSpecification) {
        Long id = productSpecificationService.addProductSpecification(productSpecification);
        return id > 0 ? ResponseEntity.created(URI.create("/product-specifications/" + id)).body(Maps.of("id", id)) : ResponseEntity.badRequest().body("添加商品规格失败！");
    }


    @ApiOperation("修改商品规格")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品规格Id", dataType = "Long", required = true)
    })
    @PutMapping("/product-specifications/{id}")
    @ApiHideBodyProperty({"id", "product"})
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProductSpecification productSpecification) {
        productSpecification.setId(id);
        return productSpecificationService.update(productSpecification) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败！");
    }


    @ApiOperation(value = "根据Id查找商品规格", response = ProductSpecification.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品规格Id", dataType = "Long", required = true)
    @GetMapping("/product-specifications/{id}")
    public ResponseEntity single(@PathVariable("id") Long specificationId) {
        ProductSpecification productSpecification = productSpecificationService.findById(specificationId);
        return ResponseEntity.ok().body(productSpecification);
    }


    @ApiOperation("根据Id删除商品规格")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个商品规格Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/product-specifications/{ids}")
    public ResponseEntity deleteByProductId(@PathVariable("ids") String ids) {
        List<ProductShelf> productShelfList = shelfService.findListBySpecificationIds(ids);
        if (productShelfList != null && !productShelfList.isEmpty()) {
            return ResponseEntity.badRequest().body("商品规格存在上架信息不可删除！");
        }
        return productSpecificationService.deleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询商品规格信息列表", response = ProductSpecification.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ProductSpecificationParam")
    })
    @PostMapping("/product-specifications/pages")
    public ResponseEntity search(@RequestBody ProductSpecificationParam param) {
        log.debug("查询商品规格信息列表\t param:{}", param);
        Pages<ProductSpecification> pages = productSpecificationService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

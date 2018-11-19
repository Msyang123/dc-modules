package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductCategory;
import com.lhiot.dc.domain.ProductCategoryParam;
import com.lhiot.dc.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author xiaojian  created in  2018/11/16 16:38
 */
@RestController
@Slf4j
@Api("商品分类接口")
public class ProductCategoryApi {
    private ProductCategoryService categoryService;

    public ProductCategoryApi(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("添加商品分类")
    @PostMapping("/product-categories")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody ProductCategory productCategory) {
        Long id = categoryService.addProductCategory(productCategory);
        return id > 0 ? ResponseEntity.created(URI.create("/product-categories/" + id)).body(Maps.of("id", id)) : ResponseEntity.badRequest().body("添加商品分类失败！");
    }


    @ApiOperation("修改商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品分类Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "productCategory", value = "商品分类信息", dataType = "ProductCategory", required = true)
    })
    @PutMapping("/product-categories/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProductCategory productCategory) {
        productCategory.setId(id);
        return categoryService.update(productCategory) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }


    @ApiOperation(value = "根据Id查找商品分类", response = ProductCategory.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品分类Id", dataType = "Long", required = true)
    @GetMapping("/product-categories/{id}")
    public ResponseEntity single(@PathVariable("id") Long categoryId) {
        ProductCategory productCategory = categoryService.findById(categoryId);
        //TODO 需要确认用哪种返回
        return ResponseEntity.ok().body(productCategory);
        //return productCategory != null ? ResponseEntity.ok().body(productCategory) : ResponseEntity.badRequest().body("没有找到商品分类信息");
        //return productCategory != null ? ResponseEntity.ok().body(productCategory) : ResponseEntity.notFound().build();
    }


    @ApiOperation("根据Id删除商品分类")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个商品分类Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/product-categories/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return categoryService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


    @ApiOperation("根据条件分页查询商品分类信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ProductCategoryParam")
    })
    @PostMapping("/product-categories/pages")
    public ResponseEntity search(@RequestBody ProductCategoryParam param) {
        log.debug("查询商品分类信息列表\t param:{}", param);
        Pages<ProductCategory> pages = categoryService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

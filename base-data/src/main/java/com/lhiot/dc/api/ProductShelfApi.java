package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductShelf;
import com.lhiot.dc.domain.ProductShelfParam;
import com.lhiot.dc.service.ProductShelfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author xiaojian created in 2018/11/14 12:12
 **/
@RestController
@Slf4j
@Api("商品上架接口")
public class ProductShelfApi {
    private ProductShelfService shelfService;

    public ProductShelfApi(ProductShelfService shelfService) {
        this.shelfService = shelfService;
    }


    @ApiOperation("新增商品上架")
    @ApiHideBodyProperty("id")
    @PostMapping("/product-shelves")
    public ResponseEntity create(@RequestBody ProductShelf productShelf) {
        Long Id = shelfService.insert(productShelf);
        return Id > 0 ? ResponseEntity.created(URI.create("/product-shelves/" + Id)).body(Maps.of("id", Id)) : ResponseEntity.badRequest().body("新增商品上架信息失败！");
    }

    @ApiOperation("修改商品上架")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品上架Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "productShelf", value = "商品上架信息", dataType = "ProductShelf", required = true)
    })
    @PutMapping("/product-shelves/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProductShelf productShelf) {
        productShelf.setId(id);
        return shelfService.update(productShelf) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }

    @ApiOperation(value = "根据Id查找商品上架", response = ProductShelf.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品上架Id", dataType = "Long", required = true)
    @GetMapping("/product-shelves/{id}")
    public ResponseEntity single(@PathVariable("id") Long shelfId) {
        ProductShelf productShelf = shelfService.findById(shelfId);
        //TODO 需要确认用哪种返回
        return ResponseEntity.ok().body(productShelf);
        //return productShelf != null ? ResponseEntity.ok().body(productShelf) : ResponseEntity.badRequest().body("没有找到商品上架信息");
        //return productShelf != null ? ResponseEntity.ok().body(productShelf) : ResponseEntity.notFound().build();
    }


    @ApiOperation("根据Id删除商品上架")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个商品上架Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/product-shelves/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return shelfService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


    @ApiOperation("根据条件分页查询商品上架信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ProductShelfParam")
    })
    @PostMapping("/product-shelves/pages")
    public ResponseEntity search(@RequestBody ProductShelfParam param) {
        log.debug("查询商品上架信息列表\t param:{}", param);
        Pages<ProductShelf> pages = shelfService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

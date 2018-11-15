package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductShelf;
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
    @PostMapping("/product-shelf")
    public ResponseEntity createShelfProduct(@RequestBody ProductShelf productShelf) {
        Long Id = shelfService.insert(productShelf);
        return Id > 0 ? ResponseEntity.created(URI.create("/product-shelf/" + Id)).body(Maps.of("id", Id)) : ResponseEntity.badRequest().body("新增商品上架信息失败！");
    }

    @ApiOperation("修改商品上架")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "商品上架Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = "body", name = "productShelf", value = "商品上架信息", dataType = "ProductShelf", required = true)
    })
    @ApiHideBodyProperty("createAt")
    @PutMapping("/product-shelf/{id}")
    public ResponseEntity updateShelfProduct(@PathVariable("id") Long id, @RequestBody ProductShelf productShelf) {
        productShelf.setId(id);
        return shelfService.update(productShelf) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }

    @ApiOperation(value = "根据Id查找商品上架", response = ProductShelf.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品规上架Id", dataType = "Long", required = true)
    @GetMapping("/product-shelf/{id}")
    public ResponseEntity single(@PathVariable("id") Long shelfId) {
        return ResponseEntity.ok().body(shelfService.findById(shelfId));
    }


}

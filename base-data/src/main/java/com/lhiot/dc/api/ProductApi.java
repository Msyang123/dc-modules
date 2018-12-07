package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.Product;
import com.lhiot.dc.model.ProductParam;
import com.lhiot.dc.service.ProductService;
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
 * @author xiaojian  created in  2018/11/12 17:07
 */
@RestController
@Slf4j
@Api(description = "商品接口")
public class ProductApi {
    private ProductService productService;
    private ProductSpecificationService productSpecificationService;

    public ProductApi(ProductService productService, ProductSpecificationService productSpecificationService) {
        this.productService = productService;
        this.productSpecificationService = productSpecificationService;
    }

    @ApiOperation("添加商品")
    @PostMapping("/products")
    @ApiHideBodyProperty({"id", "attachments", "createAt"})
    public ResponseEntity create(@RequestBody Product product) {
        Tips tips = productService.addProduct(product);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        Long productId = Long.valueOf(tips.getMessage());
        return productId > 0 ? ResponseEntity.created(URI.create("/products/" + productId)).body(Maps.of("id", productId)) : ResponseEntity.badRequest().body("添加商品失败！");
    }

    @ApiOperation("修改商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品Id", dataType = "Long", required = true)
    })
    @PutMapping("/products/{id}")
    @ApiHideBodyProperty({"id", "code", "attachments", "createAt"})
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败！");
    }


    @ApiOperation(value = "根据Id查找商品", response = Product.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品Id", dataType = "Long", required = true)
    @GetMapping("/products/{id}")
    public ResponseEntity single(@PathVariable("id") Long productId) {
        Product product = productService.findById(productId);
        return ResponseEntity.ok().body(product);
    }


    @ApiOperation("根据商品Id删除商品")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个商品Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/products/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        List<String> searchProductNameList = productSpecificationService.findHaveSpecificationByProductIds(ids);
        if (searchProductNameList != null && !searchProductNameList.isEmpty()) {
            return ResponseEntity.badRequest().body("以下商品存在规格不可删除：" + searchProductNameList.toString());
        }
        return productService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询商品信息列表", response = Product.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ProductParam")
    })
    @PostMapping("/products/pages")
    @ApiHideBodyProperty("attachments")
    public ResponseEntity search(@RequestBody ProductParam param) {
        log.debug("查询商品信息列表\t param:{}", param);
        Pages<Product> pages = productService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

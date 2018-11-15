package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.util.StringUtils;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.Product;
import com.lhiot.dc.domain.ProductSpecification;
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
import java.util.Arrays;
import java.util.List;

/**
 * @author xiaojian  created in  2018/11/12 17:07
 */
@RestController
@Slf4j
@Api("商品接口")
public class ProductApi {
    private ProductService productService;
    private ProductSpecificationService productSpecificationService;

    public ProductApi(ProductService productService, ProductSpecificationService productSpecificationService) {
        this.productService = productService;
        this.productSpecificationService = productSpecificationService;
    }

    @ApiOperation("添加商品")
    @PostMapping("/products")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody Product product) {
        Long productId = productService.addProduct(product);
        return productId > 0 ? ResponseEntity.created(URI.create("/products/" + productId)).body(Maps.of("id", productId)) : ResponseEntity.badRequest().body("添加商品失败！");
    }

    @ApiOperation("修改商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "商品Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = "body", name = "product", value = "商品信息", dataType = "Product", required = true)
    })
    @ApiHideBodyProperty("createAt")
    @PutMapping("/products/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败");
    }


    @ApiOperation(value = "根据Id查找商品", response = Product.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品Id", dataType = "Long", required = true)
    @GetMapping("/products/{id}")
    public ResponseEntity single(@PathVariable("id") Long productId) {
        return ResponseEntity.ok().body(productService.findById(productId));
    }


    @ApiOperation("根据Id删除商品")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品Id", dataType = "Long", required = true)
    @DeleteMapping("/products/{id}")
    public ResponseEntity delete(@PathVariable("id") Long productId) {
        List<ProductSpecification> specificationList = productSpecificationService.findListByProductId(productId);
        if (specificationList != null && !specificationList.isEmpty()) {
            return ResponseEntity.badRequest().body("该商品存在规格信息不可删除！");
        }
        return productService.delete(productId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }

    @ApiOperation("根据Id集合批量删除商品")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "商品Id集合以逗号隔开", dataType = "String", required = true)
    @DeleteMapping("/products/batch/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        List<String> productIdList = Arrays.asList(StringUtils.tokenizeToStringArray(ids, ","));
        List<String> searchProductNameList = productSpecificationService.findSpecificationProductIdList(productIdList);
        if (searchProductNameList != null && !searchProductNameList.isEmpty()) {
            return ResponseEntity.badRequest().body("以下商品存在规格不可删除：" + searchProductNameList.toString());
        }
        return productService.batchDeleteById(productIdList) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


}

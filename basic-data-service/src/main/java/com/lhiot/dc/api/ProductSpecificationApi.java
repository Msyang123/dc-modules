package com.lhiot.dc.api;

import com.leon.microx.web.result.Multiple;
import com.leon.microx.web.result.Pages;
import com.lhiot.dc.domain.ProductResult;
import com.lhiot.dc.mapper.ProductSpecificationMapper;
import com.lhiot.dc.service.ProductSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangfeng created in 2018/9/20 15:08
 **/
@RestController
@Slf4j
@RequestMapping("/product/specification")
@Api(description = "规格接口")
public class ProductSpecificationApi {

    private ProductSpecificationMapper productSpecificationMapper;
    private ProductSpecificationService specificationService;

    public ProductSpecificationApi(ProductSpecificationMapper productSpecificationMapper, ProductSpecificationService specificationService) {
        this.productSpecificationMapper = productSpecificationMapper;
        this.specificationService = specificationService;
    }

    @ApiOperation(value = "根据规格Id查询商品信息",response = ProductResult.class,responseContainer = "Set")
    @ApiImplicitParam(paramType = "path", name = "id", value = "规格Id", dataType = "Long", required = true)
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        ProductResult productResult = productSpecificationMapper.findById(id);
        if (Objects.isNull(productResult)) {
            return ResponseEntity.badRequest().body("商品不存在！");
        }
        List<ProductResult> productList = new ArrayList<>();
        productList.add(productResult);
        return ResponseEntity.ok(Multiple.of(specificationService.assemblyProductList(productList)));
    }


}

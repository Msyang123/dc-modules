package com.lhiot.dc.api;

import com.leon.microx.util.StringUtils;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<ProductResult> results = specificationService.assemblyProductList(productList);
        return ResponseEntity.ok(results.get(0));
    }

    @ApiOperation(value = "根据规格Id集合查询商品信息",response = ProductResult.class,responseContainer = "Set")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "规格Id集合", dataType = "String", required = true)
    @GetMapping("/list")
    public ResponseEntity findByIds(@RequestParam("ids") String ids) {
        List<String> idList = Arrays.asList(StringUtils.tokenizeToStringArray(ids,","));
        List<ProductResult> productResultList = productSpecificationMapper.findByIds(idList);
        if (!Objects.equals(idList.size(),productResultList.size())){
            return ResponseEntity.badRequest().body("传入信息有误！");
        }
        if (CollectionUtils.isEmpty(productResultList)) {
            return ResponseEntity.badRequest().body("商品不存在！");
        }
        return ResponseEntity.ok(Multiple.of(specificationService.assemblyProductList(productResultList)));
    }


}

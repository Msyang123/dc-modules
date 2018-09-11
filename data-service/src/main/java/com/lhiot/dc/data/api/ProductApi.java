package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.Product;
import com.lhiot.dc.data.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
* Description:商品信息接口类
* @author Limiaojun
* @date 2018/06/01
*/
@Api(description = "商品信息接口")
@Slf4j
@RestController
@RequestMapping("/products")
@SuppressWarnings("rawtypes")
public class ProductApi {

    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "添加商品信息")
    @ApiImplicitParam(paramType = "body", name = "product", value = "要添加的商品信息", required = true, dataType = "Product")
    public ResponseEntity<Integer> create(@RequestBody Product product) {
        log.debug("添加商品信息\t param:{}",product);

        return ResponseEntity.ok(productService.create(product));
    }

    @PutMapping
    @ApiOperation(value = "根据id更新商品信息")
    @ApiImplicitParam(paramType = "body", name = "product", value = "要更新的商品信息", required = true, dataType = "Product")
    public ResponseEntity<Integer> update(@RequestBody Product product) {
        log.debug("根据id更新商品信息\t param:{}",product);

        return ResponseEntity.ok(productService.updateById(product));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除商品信息")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品信息的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除商品信息\t param:{}",ids);

        return ResponseEntity.ok(productService.deleteByIds(ids));
    }

    @ApiOperation(value = "根据id查询商品信息", notes = "根据id查询商品信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productService.selectById(id));
    }

    @PostMapping("/page/query")
    @ApiOperation(value = "查询商品信息分页列表")
    public ResponseEntity<PagerResultObject<Product>> pageQuery(Product product){
        log.debug("查询商品信息分页列表\t param:{}",product);

        return ResponseEntity.ok(productService.pageList(product));
    }


    @GetMapping("/list/{ids}")
    @ApiOperation(value = "根据ids查找商品信息列表")
    public ResponseEntity<Multiple> findProducts(@PathVariable("ids") String ids) {
        log.debug("根据ids查找商品信息列表\t param:{}", ids);
        Product param = new Product();
        param.setRows(null);
        param.setIds(Arrays.asList(ids.split(",")));

        return ResponseEntity.ok(Multiple.of(productService.pageList(param).getRows()));
    }

    @ApiOperation(value = "查询所有商品列表", notes = "查询所有商品列表")
    @GetMapping("/all")
    public ResponseEntity<Multiple> findAllWxSmallProductResults() {
        Product param = new Product();
        param.setRows(null);

        return ResponseEntity.ok(Multiple.of(productService.pageList(param).getRows()));
    }

}

package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductUnit;
import com.lhiot.dc.data.service.ProductUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:商品单位接口类
* @author Limiaojun
* @date 2018/06/01
*/
@Api(description = "商品单位接口")
@Slf4j
@RestController
@RequestMapping("/products-unit")
public class ProductUnitApi {

    private final ProductUnitService productUnitService;

    @Autowired
    public ProductUnitApi(ProductUnitService productUnitService) {
        this.productUnitService = productUnitService;
    }

    @PostMapping
    @ApiOperation(value = "添加商品单位")
    @ApiImplicitParam(paramType = "body", name = "productUnit", value = "要添加的商品单位", required = true, dataType = "ProductUnit")
    public ResponseEntity<Integer> create(@RequestBody ProductUnit productUnit) {
        log.debug("添加商品单位\t param:{}",productUnit);

        return ResponseEntity.ok(productUnitService.create(productUnit));
    }

    @PutMapping
    @ApiOperation(value = "根据id更新商品单位")
    @ApiImplicitParam(paramType = "body", name = "productUnit", value = "要更新的商品单位", required = true, dataType = "ProductUnit")
    public ResponseEntity<Integer> update(@RequestBody ProductUnit productUnit) {
        log.debug("根据id更新商品单位\t param:{}",productUnit);

        return ResponseEntity.ok(productUnitService.updateById(productUnit));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除商品单位")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品单位的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除商品单位\t param:{}",ids);

        return ResponseEntity.ok(productUnitService.deleteByIds(ids));
    }

    @PostMapping("/page/query")
    @ApiOperation(value = "查询商品单位分页列表")
    public ResponseEntity<PagerResultObject<ProductUnit>> pageQuery(ProductUnit productUnit){
        log.debug("查询商品单位分页列表\t param:{}",productUnit);

        return ResponseEntity.ok(productUnitService.pageList(productUnit));
    }

    @ApiOperation(value = "根据id查询商品单位信息", notes = "根据id查询商品单位信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductUnit> findProduct(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productUnitService.selectById(id));
    }

    @ApiOperation(value = "判断单位是否可以删除", notes = "判断单位是否可以删除")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "单位主键ids,逗号分隔", required = true, dataType = "String")
    @GetMapping("/whetherdelete")
    public ResponseEntity<Boolean> whetherDelete(@RequestParam String ids){
            return ResponseEntity.ok(productUnitService.canBeDeleteUnit(ids));
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "查询所有商品单位列表", notes = "查询所有商品单位列表")
    @GetMapping("/all")
    public ResponseEntity<Multiple> findAllProductUnits() {
        ProductUnit param = new ProductUnit();
        param.setRows(null);

        return ResponseEntity.ok(Multiple.of(productUnitService.pageList(param).getRows()));
    }

}

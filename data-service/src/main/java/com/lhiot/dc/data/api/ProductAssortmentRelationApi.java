package com.lhiot.dc.data.api;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductAssortmentRelation;
import com.lhiot.dc.data.service.ProductAssortmentRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:套餐商品关联接口类
* @author yijun
* @date 2018/07/24
*/
@Api(description = "套餐商品关联接口")
@Slf4j
@RestController
@RequestMapping("/product-assortment-relation")
public class ProductAssortmentRelationApi {

    private final ProductAssortmentRelationService productAssortmentRelationService;

    @Autowired
    public ProductAssortmentRelationApi(ProductAssortmentRelationService productAssortmentRelationService) {
        this.productAssortmentRelationService = productAssortmentRelationService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加套餐商品关联")
    @ApiImplicitParam(paramType = "body", name = "productAssortmentRelation", value = "要添加的套餐商品关联", required = true, dataType = "ProductAssortmentRelation")
    public ResponseEntity<Integer> create(@RequestBody ProductAssortmentRelation productAssortmentRelation) {
        log.debug("添加套餐商品关联\t param:{}",productAssortmentRelation);
        
        return ResponseEntity.ok(productAssortmentRelationService.create(productAssortmentRelation));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新套餐商品关联")
    @ApiImplicitParam(paramType = "body", name = "productAssortmentRelation", value = "要更新的套餐商品关联", required = true, dataType = "ProductAssortmentRelation")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody ProductAssortmentRelation productAssortmentRelation) {
        log.debug("根据id更新套餐商品关联\t id:{} param:{}",id,productAssortmentRelation);
        productAssortmentRelation.setId(id);
        
        return ResponseEntity.ok(productAssortmentRelationService.updateById(productAssortmentRelation));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除套餐商品关联")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除套餐商品关联的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除套餐商品关联\t param:{}",ids);
        
        return ResponseEntity.ok(productAssortmentRelationService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询套餐商品关联", notes = "根据id查询套餐商品关联")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductAssortmentRelation> findProductAssortmentRelation(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productAssortmentRelationService.selectById(id));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询套餐商品关联分页列表")
    public ResponseEntity<PagerResultObject<ProductAssortmentRelation>> pageQuery(ProductAssortmentRelation productAssortmentRelation){
        log.debug("查询套餐商品关联分页列表\t param:{}",productAssortmentRelation);
        
        return ResponseEntity.ok(productAssortmentRelationService.pageList(productAssortmentRelation));
    }
    
}

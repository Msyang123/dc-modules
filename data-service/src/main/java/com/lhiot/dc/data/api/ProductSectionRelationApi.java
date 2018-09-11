package com.lhiot.dc.data.api;

import com.leon.microx.util.StringUtils;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductSectionRelation;
import com.lhiot.dc.data.service.ProductSectionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:板块商品关联接口类
* @author yijun
* @date 2018/08/24
*/
@Api(description = "板块商品关联接口")
@Slf4j
@RestController
@RequestMapping("/product-section-relation")
public class ProductSectionRelationApi {

    private final ProductSectionRelationService productSectionRelationService;

    @Autowired
    public ProductSectionRelationApi(ProductSectionRelationService productSectionRelationService) {
        this.productSectionRelationService = productSectionRelationService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加板块商品关联")
    @ApiImplicitParam(paramType = "body", name = "productSectionRelation", value = "要添加的板块商品关联", required = true, dataType = "ProductSectionRelation")
    public ResponseEntity<Integer> create(@RequestBody ProductSectionRelation productSectionRelation) {
        log.debug("添加板块商品关联\t param:{}",productSectionRelation);
        if(StringUtils.isNotBlank(productSectionRelation.getProductStandardIds())){
            String[] productStandardIds  = productSectionRelation.getProductStandardIds().split(",");
            for(String productStandIdStr:productStandardIds){
                ProductSectionRelation newRelation = new ProductSectionRelation();
                newRelation.setProductSectionId(productSectionRelation.getProductSectionId());
                newRelation.setProductStandardId(Long.valueOf(productStandIdStr));
                productSectionRelationService.update(newRelation);
            }
        }
        return ResponseEntity.ok(productSectionRelationService.create(productSectionRelation));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新板块商品关联")
    @ApiImplicitParam(paramType = "body", name = "productSectionRelation", value = "要更新的板块商品关联", required = true, dataType = "ProductSectionRelation")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody ProductSectionRelation productSectionRelation) {
        log.debug("根据id更新板块商品关联\t id:{} param:{}",id,productSectionRelation);
        productSectionRelation.setId(id);
        
        return ResponseEntity.ok(productSectionRelationService.updateById(productSectionRelation));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除板块商品关联")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除板块商品关联的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除板块商品关联\t param:{}",ids);
        
        return ResponseEntity.ok(productSectionRelationService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询板块商品关联", notes = "根据id查询板块商品关联")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductSectionRelation> findProductSectionRelation(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productSectionRelationService.selectById(id));
    }
    
    @PostMapping("/page/query")
    @ApiOperation(value = "查询板块商品关联分页列表")
    public ResponseEntity<PagerResultObject<ProductSectionRelation>> pageQuery(ProductSectionRelation productSectionRelation){
        log.debug("查询板块商品关联分页列表\t param:{}",productSectionRelation);
        return ResponseEntity.ok(productSectionRelationService.pageList(productSectionRelation));
    }
    
}

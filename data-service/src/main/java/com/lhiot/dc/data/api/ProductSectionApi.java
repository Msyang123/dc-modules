package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductSection;
import com.lhiot.dc.data.domain.out.ProductSectionProductResult;
import com.lhiot.dc.data.domain.out.ProductSectionSubResult;
import com.lhiot.dc.data.service.ProductSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description:商品板块接口类
 * @author Limiaojun
 * @date 2018/07/28
 */
@Api(description = "商品板块接口")
@Slf4j
@RestController
@RequestMapping("/products-section")
public class ProductSectionApi {

    private final ProductSectionService productSectionService;
    @Autowired
    public ProductSectionApi(ProductSectionService productSectionService) {
        this.productSectionService = productSectionService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加商品板块")
    @ApiImplicitParam(paramType = "body", name = "productSection", value = "要添加的商品板块", required = true, dataType = "ProductSection")
    public ResponseEntity<Integer> create(@RequestBody ProductSection productSection) {
        log.debug("添加商品板块\t param:{}", productSection);
        return ResponseEntity.ok(productSectionService.create(productSection));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新商品板块")
    @ApiImplicitParam(paramType = "body", name = "productSection", value = "要更新的商品板块", required = true, dataType = "ProductSection")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody ProductSection productSection) {
        log.debug("根据id更新商品板块\t id:{} param:{}",id, productSection);
        productSection.setId(id);
        return ResponseEntity.ok(productSectionService.updateById(productSection));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除商品板块")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品板块的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除商品板块\t param:{}",ids);

        return ResponseEntity.ok(productSectionService.deleteByIds(ids));
    }

    @ApiOperation(value = "根据id查询商品板块", notes = "根据id查询商品板块")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductSection> findProductBlock(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productSectionService.selectById(id));
    }

    @PostMapping("/page/query")
    @ApiOperation(value = "查询商品板块分页列表")
    public ResponseEntity<PagerResultObject<ProductSection>> pageQuery(ProductSection productSection){
        log.debug("查询商品板块分页列表\t param:{}", productSection);
        return ResponseEntity.ok(productSectionService.pageList(productSection));
    }
    @GetMapping("/findByPositionName")
    @ApiOperation(value = "根据位置查询板块信息")
    public ResponseEntity<ProductSectionSubResult> findByPositionName(String  positionName, String applicationType, String storeId ){
        ProductSectionSubResult productSectionResult = productSectionService.findSubByPositionName(positionName,applicationType,storeId);
        return ResponseEntity.ok(productSectionResult);
    }
    @GetMapping("/products")
    @ApiOperation(value = "查询板块商品信息")
    public ResponseEntity<ProductSectionProductResult> findByProducts(String storeId, String positionName, String applicationType){
        ProductSectionProductResult productSectionProductResult = productSectionService.findProductByPositionName(positionName,applicationType,storeId);
        return ResponseEntity.ok(productSectionProductResult);
    }
    @ApiOperation(value = "商品板块信息树查询", notes = "后台管理--商品板块信息树查询")
    @PostMapping("/tree")
    public ResponseEntity<Multiple> productGroupTree(ProductSection productSection){
        return ResponseEntity.ok(Multiple.of(productSectionService.sectionTree(productSection)));
    }
    @ApiOperation(value = "后台管理--判断当前分类信息是否可以删除", notes = "后台管理--判断当前分类信息是否可以删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "ids", value = "分类信息ids，逗号分隔", required = true, dataType = "String")
    })
    @GetMapping("/whetherdelete")
    public ResponseEntity<Boolean> whetherDelete(@RequestParam String ids){
        log.info("ids="+ids);
        return ResponseEntity.ok(productSectionService.canDeleteGroup(ids));
    }
}

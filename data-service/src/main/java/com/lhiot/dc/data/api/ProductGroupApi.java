package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductGroup;
import com.lhiot.dc.data.service.ProductGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:商品分类接口类
* @author Limiaojun
* @date 2018/05/30
*/
@Api(description = "后台管理-商品分类接口")
@Slf4j
@RestController
@RequestMapping("/products-group")
public class ProductGroupApi {

    private final ProductGroupService productGroupService;

    @Autowired
    public ProductGroupApi(ProductGroupService productGroupService) {
        this.productGroupService = productGroupService;
    }

    @PostMapping
    @ApiOperation(value = "添加商品分类")
    @ApiImplicitParam(paramType = "body", name = "productGroup", value = "要添加的商品分类", required = true, dataType = "ProductGroup")
    public ResponseEntity<Integer> create(@RequestBody ProductGroup productGroup) {
        log.debug("添加商品分类\t param:{}",productGroup);

        return ResponseEntity.ok(productGroupService.create(productGroup));
    }

    @PutMapping
    @ApiOperation(value = "根据id更新商品分类")
    @ApiImplicitParam(paramType = "body", name = "productGroup", value = "要更新的商品分类", required = true, dataType = "ProductGroup")
    public ResponseEntity<Integer> update(@RequestBody ProductGroup productGroup) {
        log.debug("根据id更新商品分类\t param:{}",productGroup);

        return ResponseEntity.ok(productGroupService.updateById(productGroup));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据id删除商品分类")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除商品分类的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据id删除商品分类\t param:{}",ids);

        return ResponseEntity.ok(productGroupService.deleteByIds(ids));
    }

    @PostMapping("/page/query")
    @ApiOperation(value = "查询商品分类分页列表")
    public ResponseEntity<PagerResultObject<ProductGroup>> pageQuery(ProductGroup productGroup){
        log.debug("查询商品分类分页列表\t param:{}",productGroup);

        return ResponseEntity.ok(productGroupService.pageList(productGroup));
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "商品分类信息树查询", notes = "后台管理--商品分类信息分类查询")
    @GetMapping("/tree")
    public ResponseEntity<Multiple> productGroupTree(){
            return ResponseEntity.ok(Multiple.of(productGroupService.productGroupTree()));
    }

    @ApiOperation(value = "后台管理--根据id查询商品分类信息", notes = "后台管理--根据id查询商品分类信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<ProductGroup> findProductGroup(@PathVariable("id") Long id) {

        return ResponseEntity.ok(productGroupService.selectById(id));
    }

    @ApiOperation(value = "后台管理--判断当前分类信息是否可以删除", notes = "后台管理--判断当前分类信息是否可以删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "ids", value = "分类信息ids，逗号分隔", required = true, dataType = "String")
    })
    @GetMapping("/whetherdelete")
    public ResponseEntity<Boolean> whetherDelete(@RequestParam String ids){
            log.info("ids="+ids);
            return ResponseEntity.ok(productGroupService.canDeleteGroup(ids));
    }
}

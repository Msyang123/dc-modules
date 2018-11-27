package com.lhiot.dc.api;

import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.ProductSectionRelation;
import com.lhiot.dc.service.ProductSectionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author xiaojian  created in  2018/11/16 9:24
 */
@RestController
@Slf4j
@Api(description = "商品版块与商品上架关系接口")
public class ProductSectionRelationApi {

    private ProductSectionRelationService relationService;

    public ProductSectionRelationApi(ProductSectionRelationService relationService) {
        this.relationService = relationService;
    }

    @ApiOperation("添加版块与商品上架关系")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "productSectionRelation", value = "版块与商品上架关系信息", dataType = "ProductSectionRelation", required = true)
    @PostMapping("/product-section-relations")
    public ResponseEntity create(@RequestBody ProductSectionRelation productSectionRelation) {
        Tips tips = relationService.addRelation(productSectionRelation);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        Long relationId = Long.valueOf(tips.getMessage());
        return relationId > 0 ?
                ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("添加商品与版块关系记录失败！");
    }


    @ApiOperation("根据Id删除版块与商品上架关系")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "关系Id", dataType = "Long", required = true)
    @DeleteMapping("/product-section-relations/{id}")
    public ResponseEntity delete(@PathVariable("id") Long relationId) {
        return relationService.deleteRelation(relationId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation("批量添加版块与商品上架关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "sectionId", value = "商品版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "shelfIds", value = "多个商品上架Id以英文逗号分隔", dataType = "String", required = true)
    })
    @PostMapping("/product-section-relations/batches")
    public ResponseEntity createBatch(@RequestParam("sectionId") Long sectionId, @RequestParam("shelfIds") String shelfIds) {
        Tips tips = relationService.addRelationList(sectionId, shelfIds);
        return tips.err() ? ResponseEntity.badRequest().body(tips.getMessage()) : ResponseEntity.ok().build();
    }


    @ApiOperation("批量删除版块与商品上架关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "sectionId", value = "商品版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "shelfIds", value = "多个商品上架Id以英文逗号分隔,为空则删除此版块所有上架关系", dataType = "String")
    })
    @DeleteMapping("/product-section-relations/batches")
    public ResponseEntity deleteBatch(@RequestParam("sectionId") Long sectionId, @RequestParam(value = "shelfIds", required = false) String shelfIds) {
        return relationService.deleteRelationList(sectionId, shelfIds) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


}

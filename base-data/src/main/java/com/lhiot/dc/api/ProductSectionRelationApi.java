package com.lhiot.dc.api;

import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductSectionRelation;
import com.lhiot.dc.service.ProductSectionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author xiaojian  created in  2018/11/16 9:24
 */
@RestController
@Slf4j
@Api("商品版块与商品上架关系接口")
public class ProductSectionRelationApi {

    private ProductSectionRelationService relationService;

    public ProductSectionRelationApi(ProductSectionRelationService relationService) {
        this.relationService = relationService;
    }

    @ApiOperation("添加商品上架与版块关系")
    @PostMapping("/product-section-relations")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody ProductSectionRelation productSectionRelation) {
        Long relationId = relationService.addRelation(productSectionRelation);
        return relationId > 0 ?
                ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("添加商品与版块关系记录失败！");
    }


    @ApiOperation("根据Id删除商品上架与版块关系")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "关系Id", dataType = "Long", required = true)
    @DeleteMapping("/product-section-relations/{id}")
    public ResponseEntity delete(@PathVariable("id") Long relationId) {
        return relationService.deleteRelation(relationId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败");
    }


}

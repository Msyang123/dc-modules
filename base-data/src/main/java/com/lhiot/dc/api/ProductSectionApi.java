package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.domain.ProductSection;
import com.lhiot.dc.domain.ProductSectionParam;
import com.lhiot.dc.service.ProductSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * @author xiaojian  created in  2018/11/15 16:42
 */
@RestController
@Slf4j
@Api("商品版块接口")
public class ProductSectionApi {

    private ProductSectionService sectionService;

    public ProductSectionApi(ProductSectionService sectionService) {
        this.sectionService = sectionService;
    }

    @ApiOperation("添加商品版块")
    @PostMapping("/product-sections")
    @ApiHideBodyProperty("id")
    public ResponseEntity create(@RequestBody ProductSection productSection) {
        Tips tips = sectionService.addSection(productSection);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        Long sectionId = Long.valueOf(tips.getMessage());
        return sectionId > 0 ?
                ResponseEntity.created(URI.create("/product-sections/" + sectionId)).body(Maps.of("id", sectionId))
                : ResponseEntity.badRequest().body("添加商品版块失败！");
    }


    @ApiOperation("修改商品版块")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "productSection", value = "商品版块信息", dataType = "ProductSection", required = true)
    })
    @PutMapping("/product-sections/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ProductSection productSection) {
        productSection.setId(id);
        return sectionService.update(productSection) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败！");
    }


    @ApiOperation(value = "根据Id查找商品版块", response = ProductSection.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "商品版块Id", dataType = "Long", required = true)
    @GetMapping("/product-sections/{id}")
    public ResponseEntity single(@PathVariable("id") Long sectionId) {
        ProductSection productSection = sectionService.findById(sectionId);
        return ResponseEntity.ok().body(productSection);
    }


    @ApiOperation("根据Id删除商品版块")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个商品版块Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/product-sections/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return sectionService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation("根据条件分页查询商品版块信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ProductSectionParam")
    })
    @PostMapping("/product-sections/pages")
    public ResponseEntity search(@RequestBody ProductSectionParam param) {
        log.debug("查询商品版块信息列表\t param:{}", param);
        Pages<ProductSection> pages = sectionService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

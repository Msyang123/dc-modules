package com.lhiot.dc.base.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.lhiot.dc.base.model.CategoryItem;
import com.lhiot.dc.base.service.CategoryItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Description:字典数据接口类
 *
 * @author yijun
 * @date 2018/10/12
 */
@Api(description = "字典数据接口")
@Slf4j
@RestController
@RequestMapping("")
public class CategoryItemApi {

    private final CategoryItemService categoryItemService;

    @Autowired
    public CategoryItemApi(CategoryItemService categoryItemService) {
        this.categoryItemService = categoryItemService;
    }

    @PostMapping("/category-item")
    @ApiOperation(value = "添加字典数据")
    @ApiImplicitParam(paramType = "body", name = "categoryItem", value = "要添加的字典数据", required = true, dataType = "CategoryItem")
    public ResponseEntity<Integer> create(@RequestBody CategoryItem categoryItem) {
        log.debug("添加字典数据\t param:{}", categoryItem);

        return ResponseEntity.ok(categoryItemService.create(categoryItem));
    }

    @PutMapping("/category-item/{id}")
    @ApiOperation(value = "根据id更新字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "要更新的字典数据id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "body", name = "categoryItem", value = "要更新的字典数据", required = true, dataType = "CategoryItem")
    })
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody CategoryItem categoryItem) {
        log.debug("根据id更新字典数据\t id:{} param:{}", id, categoryItem);
        categoryItem.setId(id);

        return ResponseEntity.ok(categoryItemService.updateById(categoryItem));
    }

    @DeleteMapping("/category-item/{ids}")
    @ApiOperation(value = "根据ids删除字典数据")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除字典数据的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除字典数据\t param:{}", ids);
        categoryItemService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "根据id查询字典数据", notes = "根据id查询字典数据")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/category-item/{id}")
    public ResponseEntity<CategoryItem> findCategoryItem(@PathVariable("id") Long id) {

        return ResponseEntity.ok(categoryItemService.selectById(id));
    }

    @GetMapping("/category-item/pages")
    @ApiOperation(value = "查询字典数据分页列表")
    public ResponseEntity<Pages<CategoryItem>> pageQuery(CategoryItem categoryItem) {
        log.debug("查询字典数据分页列表\t param:{}", categoryItem);

        return ResponseEntity.ok(categoryItemService.pageList(categoryItem));
    }

    @GetMapping("/category-item/list")
    @ApiOperation(value = "查询字典数据列表")
    public ResponseEntity<List<CategoryItem>> list(CategoryItem categoryItem) {
        log.debug("查询字典数据列表\t param:{}", categoryItem);

        return ResponseEntity.ok(categoryItemService.list(categoryItem));
    }

    @ApiOperation("根据code和c_code查询规格信息")
    @GetMapping("/category/{cgCode}/category-item/{code}")
    public ResponseEntity findByCode(@PathVariable("cgCode") String cgCode, @PathVariable("code") String code) {
        CategoryItem categoryItem = categoryItemService.findByCode(Maps.of("cgCode", cgCode, "code", code));
        return Objects.isNull(categoryItem) ?
                ResponseEntity.badRequest().build() : ResponseEntity.ok().body(categoryItem);
    }
}

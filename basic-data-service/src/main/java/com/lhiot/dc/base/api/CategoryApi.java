package com.lhiot.dc.base.api;

import com.leon.microx.web.result.Pages;
import com.lhiot.dc.base.model.Category;
import com.lhiot.dc.base.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:字典接口类
 *
 * @author yijun
 * @date 2018/10/12
 */
@Api(description = "字典接口")
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping({"", "/"})
    @ApiOperation(value = "添加字典")
    @ApiImplicitParam(paramType = "body", name = "category", value = "要添加的字典", required = true, dataType = "Category")
    public ResponseEntity<Integer> create(@RequestBody Category category) {
        log.debug("添加字典\t param:{}", category);

        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id更新字典")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "要更新的字典的id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "body", name = "category", value = "要更新的字典", required = true, dataType = "Category")
    })
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody Category category) {
        log.debug("根据id更新字典\t id:{} param:{}", id, category);
        category.setId(id);

        return ResponseEntity.ok(categoryService.updateById(category));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除字典")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除字典的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除字典\t param:{}", ids);
        categoryService.deleteByIds(ids);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "根据id查询字典", notes = "根据id查询字典")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategory(@PathVariable("id") Long id) {

        return ResponseEntity.ok(categoryService.selectById(id));
    }

    @ApiOperation(value = "根据字典code查询字典", notes = "根据id查询字典")
    @ApiImplicitParam(paramType = "path", name = "code", value = "code", required = true, dataType = "String")
    @GetMapping("/by-code/{code}")
    public ResponseEntity<Category> findByName(@PathVariable("code") String code) {

        return ResponseEntity.ok(categoryService.selectByCode(code));
    }

    @GetMapping("/pages")
    @ApiOperation(value = "查询字典分页列表")
    public ResponseEntity<Pages<Category>> pageQuery(Category category) {
        log.debug("查询字典分页列表\t param:{}", category);

        return ResponseEntity.ok(categoryService.pageList(category));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询字典列表")
    public ResponseEntity<List<Category>> list(Category category) {
        log.debug("查询字典分页列表\t param:{}", category);

        return ResponseEntity.ok(categoryService.list(category));
    }

}

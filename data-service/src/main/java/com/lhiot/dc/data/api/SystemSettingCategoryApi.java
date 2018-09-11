package com.lhiot.dc.data.api;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.SystemSettingCategory;
import com.lhiot.dc.data.service.SystemSettingCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:系统设置分类接口类
* @author yijun
* @date 2018/07/24
*/
@Api(description = "系统设置分类接口")
@Slf4j
@RestController
@RequestMapping("/system-setting-category")
public class SystemSettingCategoryApi {

    private final SystemSettingCategoryService systemSettingCategoryService;

    @Autowired
    public SystemSettingCategoryApi(SystemSettingCategoryService systemSettingCategoryService) {
        this.systemSettingCategoryService = systemSettingCategoryService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加系统设置分类")
    @ApiImplicitParam(paramType = "body", name = "systemSettingCategory", value = "要添加的系统设置分类", required = true, dataType = "SystemSettingCategory")
    public ResponseEntity<Integer> create(@RequestBody SystemSettingCategory systemSettingCategory) {
        log.debug("添加系统设置分类\t param:{}",systemSettingCategory);
        
        return ResponseEntity.ok(systemSettingCategoryService.create(systemSettingCategory));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新系统设置分类")
    @ApiImplicitParam(paramType = "body", name = "systemSettingCategory", value = "要更新的系统设置分类", required = true, dataType = "SystemSettingCategory")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody SystemSettingCategory systemSettingCategory) {
        log.debug("根据id更新系统设置分类\t id:{} param:{}",id,systemSettingCategory);
        systemSettingCategory.setId(id);
        
        return ResponseEntity.ok(systemSettingCategoryService.updateById(systemSettingCategory));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除系统设置分类")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除系统设置分类的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除系统设置分类\t param:{}",ids);
        
        return ResponseEntity.ok(systemSettingCategoryService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询系统设置分类", notes = "根据id查询系统设置分类")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<SystemSettingCategory> findSystemSettingCategory(@PathVariable("id") Long id) {

        return ResponseEntity.ok(systemSettingCategoryService.selectById(id));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询系统设置分类分页列表")
    public ResponseEntity<PagerResultObject<SystemSettingCategory>> pageQuery(SystemSettingCategory systemSettingCategory){
        log.debug("查询系统设置分类分页列表\t param:{}",systemSettingCategory);
        
        return ResponseEntity.ok(systemSettingCategoryService.pageList(systemSettingCategory));
    }
    
}

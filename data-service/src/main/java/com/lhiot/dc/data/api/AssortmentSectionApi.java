package com.lhiot.dc.data.api;

import com.leon.microx.support.result.Multiple;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.AssortmentSection;
import com.lhiot.dc.data.service.AssortmentSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* Description:套餐板块分类接口类
* @author yijun
* @date 2018/07/24
*/
@Api(description = "套餐板块分类接口")
@Slf4j
@RestController
@RequestMapping("/assortments-section")
public class AssortmentSectionApi {

    private final AssortmentSectionService assortmentSectionService;

    @Autowired
    public AssortmentSectionApi(AssortmentSectionService assortmentSectionService) {
        this.assortmentSectionService = assortmentSectionService;
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加套餐板块分类")
    @ApiImplicitParam(paramType = "body", name = "assortmentSection", value = "要添加的套餐板块分类", required = true, dataType = "AssortmentSection")
    public ResponseEntity<Integer> create(@RequestBody AssortmentSection assortmentSection) {
        log.debug("添加套餐板块分类\t param:{}",assortmentSection);
        
        return ResponseEntity.ok(assortmentSectionService.create(assortmentSection));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "根据id更新套餐板块分类")
    @ApiImplicitParam(paramType = "body", name = "assortmentSection", value = "要更新的套餐板块分类", required = true, dataType = "AssortmentSection")
    public ResponseEntity<Integer> update(@PathVariable("id") Long id, @RequestBody AssortmentSection assortmentSection) {
        log.debug("根据id更新套餐板块分类\t id:{} param:{}",id,assortmentSection);
        assortmentSection.setId(id);
        
        return ResponseEntity.ok(assortmentSectionService.updateById(assortmentSection));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据ids删除套餐板块分类")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "要删除套餐板块分类的ids,逗号分割", required = true, dataType = "String")
    public ResponseEntity<Integer> deleteByIds(@PathVariable("ids") String ids) {
        log.debug("根据ids删除套餐板块分类\t param:{}",ids);
        
        return ResponseEntity.ok(assortmentSectionService.deleteByIds(ids));
    }
    
    @ApiOperation(value = "根据id查询套餐板块分类", notes = "根据id查询套餐板块分类")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键id", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public ResponseEntity<AssortmentSection> findAssortmentSection(@PathVariable("id") Long id) {

        return ResponseEntity.ok(assortmentSectionService.selectById(id));
    }
    
    @GetMapping("/page/query")
    @ApiOperation(value = "查询套餐板块分类分页列表")
    public ResponseEntity<PagerResultObject<AssortmentSection>> pageQuery(AssortmentSection assortmentSection){
        log.debug("查询套餐板块分类分页列表\t param:{}",assortmentSection);
        
        return ResponseEntity.ok(assortmentSectionService.pageList(assortmentSection));
    }

    @ApiOperation(value = "根据位置查询套餐版块", notes = "根据位置查询套餐版块")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "nameEn", value = "板块英语名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "applicationType", value = "应用类型id", required = true, dataType = "String")
    }
    )
    @GetMapping("/section")
    public ResponseEntity findAssortmentSections(@RequestParam("nameEn") String nameEn,
                                                 @RequestParam("applicationType") String applicationType) {
        return ResponseEntity.ok(assortmentSectionService.findAssortmentSections(nameEn,applicationType));
    }

    @GetMapping("/assortmentSectionCategory")
    @ApiOperation(value = "查询套餐板块分类分页列表")
    public ResponseEntity<Multiple> assortmentSectionCategory(AssortmentSection assortmentSection){
        return ResponseEntity.ok(Multiple.of(assortmentSectionService.assortmentSections(assortmentSection)));
    }
    
}

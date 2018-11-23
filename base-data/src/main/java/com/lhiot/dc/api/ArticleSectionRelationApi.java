package com.lhiot.dc.api;

import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.ArticleSectionRelation;
import com.lhiot.dc.service.ArticleSectionRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaojian  created in  2018/11/22 10:25
 */
@RestController
@Slf4j
@Api(description = "文章版块与文章关系接口")
public class ArticleSectionRelationApi {

    private ArticleSectionRelationService relationService;

    public ArticleSectionRelationApi(ArticleSectionRelationService relationService) {
        this.relationService = relationService;
    }


    @ApiOperation("添加文章版块与文章关系")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "articleSectionRelation", value = "文章版块与文章关系信息", dataType = "ArticleSectionRelation", required = true)
    @PostMapping("/article-section-relations")
    public ResponseEntity create(@RequestBody ArticleSectionRelation articleSectionRelation) {
        Long relationId = relationService.addRelation(articleSectionRelation);
        return relationId > 0 ?
                ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("添加文章与版块关系记录失败！");
    }


    @ApiOperation("根据Id删除版块与文章关系")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "关系Id", dataType = "Long", required = true)
    @DeleteMapping("/article-section-relations/{id}")
    public ResponseEntity delete(@PathVariable("id") Long relationId) {
        return relationService.deleteRelation(relationId) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation("批量添加版块与文章关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "sectionId", value = "文章版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "articleIds", value = "多个文章Id以英文逗号分隔", dataType = "String", required = true)
    })
    @PostMapping("/article-section-relations/batches")
    public ResponseEntity createBatch(@RequestParam("sectionId") String sectionId, @RequestParam("articleIds") String articleIds) {
        return relationService.addRelationList(Long.valueOf(sectionId), articleIds) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("批量添加版块与文章关系失败！");
    }


    @ApiOperation("批量删除版块与文章关系")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "sectionId", value = "文章版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "articleIds", value = "多个文章Id以英文逗号分隔,为空则删除此版块所有文章关系", dataType = "String")
    })
    @DeleteMapping("/article-section-relations/batches")
    public ResponseEntity deleteBatch(@RequestParam("sectionId") String sectionId, @RequestParam(value = "articleIds", required = false) String articleIds) {
        return relationService.deleteRelationList(Long.valueOf(sectionId), articleIds) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


}

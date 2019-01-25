package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.leon.microx.web.swagger.ApiHideBodyProperty;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.ArticleSection;
import com.lhiot.dc.model.ArticleSectionParam;
import com.lhiot.dc.service.ArticleSectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author xiaojian  created in  2018/11/21 15:28
 */
@RestController
@Slf4j
@Api(tags = {"文章版块接口"})
public class ArticleSectionApi {
    private ArticleSectionService articleSectionService;

    public ArticleSectionApi(ArticleSectionService articleSectionService) {
        this.articleSectionService = articleSectionService;
    }

    @ApiOperation("添加文章版块")
    @PostMapping("/article-sections")
    @ApiHideBodyProperty({"id", "uiPosition", "createAt", "articleList"})
    public ResponseEntity create(@Valid @RequestBody ArticleSection articleSection) {
        Tips tips = articleSectionService.addArticleSection(articleSection);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        Long sectionId = Long.valueOf(tips.getMessage());
        return sectionId > 0 ?
                ResponseEntity.created(URI.create("/article-sections/" + sectionId)).body(Maps.of("id", sectionId))
                : ResponseEntity.badRequest().body("添加文章版块失败！");
    }


    @ApiOperation("修改文章版块")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章版块Id", dataType = "Long", required = true)
    })
    @PutMapping("/article-sections/{id}")
    @ApiHideBodyProperty({"id", "uiPosition", "createAt", "articleList", "articleIds"})
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody ArticleSection articleSection) {
        articleSection.setId(id);
        Tips tips = articleSectionService.update(articleSection);
        if (tips.err()) {
            return ResponseEntity.badRequest().body(tips.getMessage());
        }
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "根据Id查找文章版块", response = ArticleSection.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章版块Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "includeArticles", dataType = "Boolean", value = "是否加载版块下文章信息(为空则默认为false)"),
            @ApiImplicitParam(paramType = ApiParamType.QUERY, name = "includeArticlesQty", dataType = "Long", value = "加载文章最大条数(includeArticles为true起作用，为空则加载所有)")
    })
    @GetMapping("/article-sections/{id}")
    public ResponseEntity single(@PathVariable("id") Long id,
                                 @RequestParam(value = "includeArticles", required = false) boolean includeArticles,
                                 @RequestParam(value = "includeArticlesQty", required = false) Long includeArticlesQty) {
        ArticleSection articleSection = articleSectionService.findById(id, includeArticles, includeArticlesQty);
        return ResponseEntity.ok().body(articleSection);
    }


    @ApiOperation("根据Id删除文章版块")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个文章版块Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/article-sections/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return articleSectionService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询文章版块信息列表", response = ArticleSection.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ArticleSectionParam")
    })
    @PostMapping("/article-sections/pages")
    public ResponseEntity search(@RequestBody ArticleSectionParam param) {
        log.debug("查询文章版块信息列表\t param:{}", param);
        Pages<ArticleSection> pages = articleSectionService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

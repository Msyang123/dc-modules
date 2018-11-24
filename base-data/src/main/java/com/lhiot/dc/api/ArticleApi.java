package com.lhiot.dc.api;

import com.leon.microx.util.Maps;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.swagger.ApiParamType;
import com.lhiot.dc.entity.Article;
import com.lhiot.dc.model.ArticleParam;
import com.lhiot.dc.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/22 8:52
 */
@RestController
@Slf4j
@Api(description = "文章接口")
public class ArticleApi {
    private ArticleService articleService;

    public ArticleApi(ArticleService articleService) {
        this.articleService = articleService;
    }


    @ApiOperation("添加文章")
    @ApiImplicitParam(paramType = ApiParamType.BODY, name = "article", value = "文章信息", dataType = "Article", dataTypeClass = Article.class, required = true)
    @PostMapping("/articles")
    public ResponseEntity create(@RequestBody Article article) {
        if (Objects.isNull(article.getTitle())) {
            return ResponseEntity.badRequest().body("标题为空，添加失败！");
        }
        Long id = articleService.addArticle(article);

        return id > 0 ?
                ResponseEntity.created(URI.create("/articles/" + id)).body(Maps.of("id", id))
                : ResponseEntity.badRequest().body("添加文章失败！");
    }


    @ApiOperation("修改文章")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章Id", dataType = "Long", required = true),
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "article", value = "文章信息", dataType = "Article", required = true)
    })
    @PutMapping("/articles/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Article article) {
        article.setId(id);
        return articleService.update(article) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("修改信息失败！");
    }


    @ApiOperation(value = "根据Id查找文章", response = Article.class)
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "id", value = "文章Id", dataType = "Long", required = true)
    @GetMapping("/articles/{id}")
    public ResponseEntity single(@PathVariable("id") Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.ok().body(article);
    }


    @ApiOperation("根据Id删除文章")
    @ApiImplicitParam(paramType = ApiParamType.PATH, name = "ids", value = "多个文章Id以英文逗号分隔", dataType = "String", required = true)
    @DeleteMapping("/articles/{ids}")
    public ResponseEntity batchDelete(@PathVariable("ids") String ids) {
        return articleService.batchDeleteByIds(ids) ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().body("删除信息失败！");
    }


    @ApiOperation(value = "根据条件分页查询文章信息列表", response = Article.class, responseContainer = "Set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = ApiParamType.BODY, name = "param", value = "查询条件", dataType = "ArticleParam")
    })
    @PostMapping("/articles/pages")
    public ResponseEntity search(@RequestBody ArticleParam param) {
        log.debug("查询文章信息列表\t param:{}", param);
        Pages<Article> pages = articleService.findList(param);
        return ResponseEntity.ok(pages);
    }


}

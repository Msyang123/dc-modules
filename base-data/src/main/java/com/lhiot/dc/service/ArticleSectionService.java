package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.ArticleSection;
import com.lhiot.dc.mapper.ArticleSectionMapper;
import com.lhiot.dc.mapper.ArticleSectionRelationMapper;
import com.lhiot.dc.model.ArticleParam;
import com.lhiot.dc.model.ArticleSectionParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xiaojian  created in  2018/11/21 15:30
 */
@Service
@Slf4j
@Transactional
public class ArticleSectionService {
    private ArticleSectionMapper articleSectionMapper;
    private ArticleSectionRelationMapper relationMapper;
    private ArticleService articleService;

    public ArticleSectionService(ArticleSectionMapper articleSectionMapper, ArticleSectionRelationMapper relationMapper, ArticleService articleService) {
        this.articleSectionMapper = articleSectionMapper;
        this.relationMapper = relationMapper;
        this.articleService = articleService;
    }

    /**
     * 新增文章版块
     *
     * @param articleSection 文章版块对象
     * @return Tips信息  成功在message中返回Id
     */
    public Tips addArticleSection(ArticleSection articleSection) {
        if (Objects.isNull(articleSection.getNameCn())) {
            return Tips.warn("文章版块中文名称为空，添加失败.");
        }

        // 幂等添加
        List<ArticleSection> po = articleSectionMapper.findListByParentIdAndNameCn(articleSection.getParentId(), articleSection.getNameCn());
        if (!po.isEmpty()) {
            return Tips.warn("文章版块重复，添加失败.");
        }

        articleSection.setCreateAt(Date.from(Instant.now()));
        articleSectionMapper.insert(articleSection);
        return Tips.info(articleSection.getId() + "");
    }


    /**
     * 修改文章版块信息
     *
     * @param articleSection 文章版块对象
     * @return Tips信息 执行结果
     */
    public Tips update(ArticleSection articleSection) {
        int result = articleSectionMapper.updateById(articleSection);
        return result > 0 ? Tips.info("修改成功") : Tips.warn("修改信息失败！");
    }


    /**
     * 根据文章版块ID查找单个文章版块
     *
     * @param id                 文章版块ID
     * @param includeArticles    是否加载版块下文章信息
     * @param includeArticlesQty 加载文章最大条数(includeArticles为true起作用，为空则加载所有)
     * @return 文章版块对象
     */
    public ArticleSection findById(Long id, Boolean includeArticles, Long includeArticlesQty) {
        ArticleSection articleSection = articleSectionMapper.findById(id);
        if (Objects.nonNull(articleSection) && Objects.nonNull(includeArticles) && includeArticles) {
            ArticleParam articleParam = new ArticleParam();
            articleParam.setSectionId(articleSection.getId());
            if (Objects.nonNull(includeArticlesQty)) {
                articleParam.setPage(1);
                articleParam.setRows(includeArticlesQty.intValue());
            }
            articleSection.setArticleList(articleService.findListByParam(articleParam));
        }
        return articleSection;
    }


    /**
     * 根据Id集合批量删除文章版块信息
     *
     * @param ids 版块Id集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        //根据文章版块ID集合删除相关关系
        relationMapper.deleteRelationBySectionIds(ids);

        return articleSectionMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询文章版块信息列表
     *
     * @param param 参数
     * @return 分页文章版块信息数据
     */
    public Pages<ArticleSection> findList(ArticleSectionParam param) {
        List<ArticleSection> list = articleSectionMapper.findList(param);
        if (Objects.nonNull(param.getIncludeArticles()) && param.getIncludeArticles()) {
            list = list.stream().peek(articleSection -> {
                        ArticleParam articleParam = new ArticleParam();
                        if (Objects.nonNull(param.getIncludeArticlesQty())) {
                            articleParam.setPage(1);
                            articleParam.setRows(param.getIncludeArticlesQty().intValue());
                        }
                        articleParam.setSectionId(articleSection.getId());
                        articleSection.setArticleList(articleService.findListByParam(articleParam));
                    }
            ).collect(Collectors.toList());
        }
        int total = param.getPageFlag() ? articleSectionMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

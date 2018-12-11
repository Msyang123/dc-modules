package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.lhiot.dc.entity.Article;
import com.lhiot.dc.mapper.ArticleMapper;
import com.lhiot.dc.mapper.ArticleSectionRelationMapper;
import com.lhiot.dc.model.ArticleParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author xiaojian  created in  2018/11/22 8:53
 */
@Service
@Slf4j
@Transactional
public class ArticleService {
    private ArticleMapper articleMapper;
    private ArticleSectionRelationMapper relationMapper;

    public ArticleService(ArticleMapper articleMapper, ArticleSectionRelationMapper relationMapper) {
        this.articleMapper = articleMapper;
        this.relationMapper = relationMapper;
    }

    /**
     * 新增文章
     *
     * @param article 文章对象
     * @return 返回文章Id
     */
    public Long addArticle(Article article) {
        article.setCreateAt(Date.from(Instant.now()));
        articleMapper.insert(article);
        return article.getId();
    }


    /**
     * 修改文章信息
     *
     * @param article 文章对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(Article article) {
        return articleMapper.updateById(article) > 0;
    }


    /**
     * 根据文章ID查找单个文章
     *
     * @param id 文章ID
     * @return 文章对象
     */
    public Article findById(Long id) {
        return articleMapper.findById(id);
    }


    /**
     * 根据Id集合批量删除文章信息
     *
     * @param ids 文章ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        //根据文章ID集合删除相关关系
        relationMapper.deleteRelationByArticleIds(ids);

        return articleMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询文章信息列表
     *
     * @param param 参数
     * @return 分页文章信息数据
     */
    public Pages<Article> findList(ArticleParam param) {
        List<Article> list = articleMapper.findList(param);
        int total = param.getPageFlag() ? articleMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

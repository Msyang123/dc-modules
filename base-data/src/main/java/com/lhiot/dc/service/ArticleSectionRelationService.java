package com.lhiot.dc.service;

import com.leon.microx.util.StringUtils;
import com.lhiot.dc.entity.ArticleSectionRelation;
import com.lhiot.dc.mapper.ArticleSectionRelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xiaojian  created in  2018/11/22 10:27
 */
@Service
@Slf4j
@Transactional
public class ArticleSectionRelationService {

    private ArticleSectionRelationMapper relationMapper;

    public ArticleSectionRelationService(ArticleSectionRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }


    /**
     * 新增文章与版块关系
     *
     * @param articleSectionRelation 文章与版块关系对象
     * @return 文章与版块关系Id
     */
    public Long addRelation(ArticleSectionRelation articleSectionRelation) {
        relationMapper.insert(articleSectionRelation);
        return articleSectionRelation.getId();
    }


    /**
     * 新增批量文章与版块关系
     *
     * @param sectionId 版块ID
     * @param articleIds 文章ID集合
     * @return 执行结果
     */
    public boolean addRelationList(Long sectionId, String articleIds) {
        List<ArticleSectionRelation> asrList = new ArrayList<>();
        String [] articleIdArrays=StringUtils.tokenizeToStringArray(articleIds,",");
        List<String> articleIdList=Stream.of(articleIdArrays).collect(Collectors.toList());
        ArticleSectionRelation articleSectionRelation;
        for (String articleId : articleIdList) {
            articleSectionRelation = new ArticleSectionRelation();
            articleSectionRelation.setSectionId(sectionId);
            articleSectionRelation.setArticleId(Long.valueOf(articleId));
            asrList.add(articleSectionRelation);
        }
        return relationMapper.insertList(asrList) > 0;
    }


    /**
     * 删除文章与版块关系
     *
     * @param relationId 关系ID
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelation(Long relationId) {
        return relationMapper.deleteById(relationId) > 0;
    }


    /**
     * 批量删除文章与版块关系
     *
     * @param sectionId 版块ID
     * @param articleIds 文章ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelationList(Long sectionId, String articleIds) {
        return relationMapper.deleteRelationList(sectionId, articleIds) > 0;
    }


}

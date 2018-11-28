package com.lhiot.dc.service;

import com.leon.microx.util.StringUtils;
import com.leon.microx.web.result.Tips;
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
     * @return Tips信息
     */
    public Tips addRelation(ArticleSectionRelation articleSectionRelation) {
        //先做幂等验证
        List<ArticleSectionRelation> poList=relationMapper.selectRelationList(articleSectionRelation.getSectionId(),articleSectionRelation.getArticleId()+"");
        if(!poList.isEmpty()){
            return Tips.warn("文章上架与版块关系重复，添加失败.");
        }

        relationMapper.insert(articleSectionRelation);
        return Tips.info(articleSectionRelation.getId() + "");
    }


    /**
     * 新增批量文章与版块关系
     *
     * @param sectionId  版块ID
     * @param articleIds 文章ID集合
     * @return Tips信息
     */
    public Tips addRelationList(Long sectionId, String articleIds) {
        //先做幂等验证
        List<ArticleSectionRelation> poList=relationMapper.selectRelationList(sectionId,articleIds);
        if(!poList.isEmpty()){
            return Tips.warn("文章上架与版块关系重复，添加失败.");
        }

        List<ArticleSectionRelation> asrList = new ArrayList<>();
        String[] articleIdArrays = StringUtils.tokenizeToStringArray(articleIds, ",");
        List<String> articleIdList = Stream.of(articleIdArrays).collect(Collectors.toList());
        ArticleSectionRelation articleSectionRelation;
        for (String articleId : articleIdList) {
            articleSectionRelation = new ArticleSectionRelation();
            articleSectionRelation.setSectionId(sectionId);
            articleSectionRelation.setArticleId(Long.valueOf(articleId));
            asrList.add(articleSectionRelation);
        }
        int result = relationMapper.insertList(asrList);
        return result > 0 ? Tips.info("添加成功") : Tips.warn("批量添加版块与文章关系失败！");
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
     * @param sectionId  版块ID
     * @param articleIds 文章ID集合
     * @return 执行结果 true 或者 false
     */
    public boolean deleteRelationList(Long sectionId, String articleIds) {
        return relationMapper.deleteRelationList(sectionId, articleIds) > 0;
    }


}

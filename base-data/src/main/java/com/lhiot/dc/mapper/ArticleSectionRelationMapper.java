package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.ArticleSectionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/22 10:48
 */
@Mapper
@Repository
public interface ArticleSectionRelationMapper {

    /**
     * 新增文章与版块关系记录
     *
     * @param articleSectionRelation 文章与版块关系对象
     * @return 执行结果
     */
    int insert(ArticleSectionRelation articleSectionRelation);


    /**
     * 新增文章与版块关系
     *
     * @param list 文章与版块关系集合
     * @return 执行结果
     */
    int insertList(List<ArticleSectionRelation> list);


    /**
     * 删除文章与版块关系记录
     *
     * @param relationId 关系ID
     * @return 执行结果
     */
    int deleteById(Long relationId);


    /**
     * 根据文章ID集合 删除文章与版块关系记录
     *
     * @param articleIds 文章ID集合
     * @return 执行结果
     */
    int deleteRelationByArticleIds(@Param("articleIds") String articleIds);


    /**
     * 根据文章版块ID集合 删除文章与版块关系记录
     *
     * @param sectionIds 版块ID集合
     * @return 执行结果
     */
    int deleteRelationBySectionIds(@Param("sectionIds") String sectionIds);


    /**
     * 批量删除文章与版块关系记录
     *
     * @param sectionId  版块ID
     * @param articleIds 文章ID集合
     * @return 执行结果
     */
    int deleteRelationList(@Param("sectionId") Long sectionId, @Param("articleIds") String articleIds);


    /**
     * 查询文章与版块关系记录
     *
     * @param sectionId 文章版块ID
     * @param articleIds  文章ID集合
     * @return 关系集合
     */
    List<ArticleSectionRelation> selectRelationList(@Param("sectionId") Long sectionId, @Param("articleIds") String articleIds);


}

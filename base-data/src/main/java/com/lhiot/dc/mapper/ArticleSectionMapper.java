package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.ArticleSection;
import com.lhiot.dc.model.ArticleSectionParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/21 15:31
 */
@Mapper
@Repository
public interface ArticleSectionMapper {

    /**
     * 新增文章版块
     *
     * @param articleSection 文章版块对象
     * @return 执行结果
     */
    int insert(ArticleSection articleSection);


    /**
     * 修改文章版块
     *
     * @param articleSection 文章版块对象
     * @return 执行结果
     */
    int updateById(ArticleSection articleSection);


    /**
     * 根据ID查找单个文章版块
     *
     * @param id 文章版块ID
     * @return 文章版块对象
     */
    ArticleSection findById(Long id);


    /**
     * 根据parentId和name_cn查找单个文章版块集合
     *
     * @param parentId 父级编号
     * @param nameCn   板块中文名称
     * @return 文章版块集合
     */
    List<ArticleSection> findListByParentIdAndNameCn(@Param("parentId") Long parentId, @Param("nameCn") String nameCn);


    /**
     * 根据ID集合批量删除文章版块
     *
     * @param ids 文章版块ID集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询文章版块信息列表
     *
     * @param param 参数
     * @return 文章版块信息列表
     */
    List<ArticleSection> findList(ArticleSectionParam param);

    /**
     * 查询文章版块信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(ArticleSectionParam param);


}

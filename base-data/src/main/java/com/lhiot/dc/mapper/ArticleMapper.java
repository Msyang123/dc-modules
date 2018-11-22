package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.Article;
import com.lhiot.dc.model.ArticleParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/22 8:54
 */
@Mapper
@Repository
public interface ArticleMapper {
    /**
     * 新增文章
     *
     * @param article 文章对象
     * @return 执行结果
     */
    int insert(Article article);


    /**
     * 修改文章
     *
     * @param article 文章对象
     * @return 执行结果
     */
    int updateById(Article article);


    /**
     * 根据ID查找单个文章
     *
     * @param id 文章ID
     * @return 文章对象
     */
    Article findById(Long id);


    /**
     * 根据ID集合批量删除文章
     *
     * @param ids 文章ID集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询文章信息列表
     *
     * @param param 参数
     * @return 文章信息列表
     */
    List<Article> findList(ArticleParam param);

    /**
     * 查询文章信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(ArticleParam param);


}

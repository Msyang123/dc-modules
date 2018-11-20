package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductSection;
import com.lhiot.dc.domain.ProductSectionParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/15 16:52
 */
@Mapper
@Repository
public interface ProductSectionMapper {

    /**
     * 新增商品版块
     *
     * @param ProductSection对象
     * @return 执行结果
     */
    int insert(ProductSection productSection);


    /**
     * 修改商品版块
     *
     * @param ProductSection对象
     * @return 执行结果
     */
    int updateById(ProductSection productSection);

    /**
     * 根据ID查找单个商品版块
     *
     * @param sectionId
     * @return 商品版块对象
     */
    ProductSection findById(Long sectionId);


    /**
     * 根据parentId和sectionName查找单个商品版块
     *
     * @param parentId
     * @param sectionName
     * @return 商品版块对象
     */
    ProductSection findByParentIdAndSectionName(@Param("parentId") Long parentId, @Param("sectionName") String sectionName);


    /**
     * 根据ID集合批量删除商品版块
     *
     * @param ids
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询商品版块信息列表
     *
     * @param param
     * @return 商品版块信息列表
     */
    List<ProductSection> findList(ProductSectionParam param);

    /**
     * 查询商品版块信息总数
     *
     * @param param
     * @return 总数
     */
    int findCount(ProductSectionParam param);


}

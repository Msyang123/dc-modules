package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.ProductCategory;
import com.lhiot.dc.domain.ProductCategoryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/16 16:45
 */
@Mapper
@Repository
public interface ProductCategoryMapper {

    /**
     * 新增商品分类
     *
     * @param ProductCategory对象
     * @return 执行结果
     */
    int insert(ProductCategory productCategory);


    /**
     * 修改商品分类
     *
     * @param ProductCategory对象
     * @return 执行结果
     */
    int updateById(ProductCategory productCategory);


    /**
     * 根据ID查找单个商品分类
     *
     * @param categoryId
     * @return 商品分类对象
     */
    ProductCategory findById(Long categoryId);


    /**
     * 根据parentId和groupName查找单个商品分类
     *
     * @param parentId
     * @param groupName
     * @return 商品分类对象
     */
    ProductCategory findByParentIdAndGroupName(@Param("parentId") Long parentId, @Param("sectionName") String groupName);


    /**
     * 根据ID集合删除商品分类集合
     *
     * @param ids
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询商品分类信息列表
     *
     * @param param
     * @return 商品分类信息列表
     */
    List<ProductCategory> findList(ProductCategoryParam param);

    /**
     * 查询商品分类信息总数
     *
     * @param param
     * @return 总数
     */
    int findCount(ProductCategoryParam param);


}

package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.ProductSection;
import com.lhiot.dc.model.ProductSectionParam;
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
     * @param productSection 商品版块对象
     * @return 执行结果
     */
    int insert(ProductSection productSection);


    /**
     * 修改商品版块
     *
     * @param productSection 商品版块对象
     * @return 执行结果
     */
    int updateById(ProductSection productSection);

    /**
     * 根据ID查找单个商品版块
     *
     * @param sectionId 商品版块ID
     * @return 商品版块对象
     */
    ProductSection findById(Long sectionId);


    /**
     * 根据id查找单个商品版块 包含商品上架信息
     *
     * @param id 商品版块ID
     * @param includeShelvesQty 包含商品上架数量
     * @return 商品版块对象
     */
    ProductSection findByIdIncludeShelves(@Param("id") Long id,@Param("includeShelvesQty") Long includeShelvesQty);


    /**
     * 根据id查找单个商品版块 包含商品上架信息和包含商品信息
     *
     * @param id 商品版块ID
     * @param includeShelvesQty 包含商品上架数量
     * @return 商品版块对象
     */
    ProductSection findByIdIncludeShelvesIncludeProduct(@Param("id") Long id,@Param("includeShelvesQty") Long includeShelvesQty);



    /**
     * 根据parentId和sectionName查找商品版块集合
     *
     * @param parentId    父ID
     * @param sectionName 板块名称
     * @return 商品版块集合
     */
    List<ProductSection> findListByParentIdAndSectionName(@Param("parentId") Long parentId, @Param("sectionName") String sectionName);


    /**
     * 根据ID集合批量删除商品版块
     *
     * @param ids 商品版块ID集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询商品版块信息列表
     *
     * @param param 参数
     * @return 商品版块信息列表
     */
    List<ProductSection> findList(ProductSectionParam param);


    /**
     * 查询商品版块信息列表   包含商品上架信息
     *
     * @param param 参数
     * @return 商品版块信息列表
     */
    List<ProductSection> findListIncludeShelves(ProductSectionParam param);


    /**
     * 查询商品版块信息列表   包含商品上架信息和商品信息
     *
     * @param param 参数
     * @return 商品版块信息列表
     */
    List<ProductSection> findListIncludeShelvesIncludeProduct(ProductSectionParam param);


    /**
     * 查询商品版块信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(ProductSectionParam param);


}

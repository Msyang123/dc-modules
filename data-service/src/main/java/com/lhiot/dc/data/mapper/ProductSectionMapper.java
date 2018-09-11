package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductSection;
import com.lhiot.dc.data.domain.out.ProductSectionProductResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:商品板块Mapper类
* @author Limiaojun
* @date 2018/07/28
*/
@Mapper
public interface ProductSectionMapper {

    /**
    * Description:新增商品板块
    *
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
    int create(ProductSection productSection);

    /**
    * Description:根据id修改商品板块
    *
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
    int updateById(ProductSection productSection);

    /**
    * Description:根据ids删除商品板块
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找商品板块
    *
    * @param id
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
    ProductSection selectById(Long id);

    /**
    * Description:查询商品板块列表
    *
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
     List<ProductSection> pageProductSections(ProductSection productSection);


    /**
    * Description: 查询商品板块总记录数
    *
    * @param productSection
    * @return
    * @author Limiaojun
    * @date 2018/07/28 10:27:41
    */
    long pageProductSectionCounts(ProductSection productSection);

    ProductSection findByPositionName(Map<String, Object> param);

    List<ProductSection> findByParentId(List<String> idList);

    List<ProductSectionProductResult> sectionTree(ProductSection productSection);


}

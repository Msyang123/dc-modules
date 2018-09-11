package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.AssortmentSection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:套餐板块分类Mapper类
* @author yijun
* @date 2018/07/24
*/
@Mapper
public interface AssortmentSectionMapper {

    /**
    * Description:新增套餐板块分类
    *
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int create(AssortmentSection assortmentSection);

    /**
    * Description:根据id修改套餐板块分类
    *
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int updateById(AssortmentSection assortmentSection);

    /**
    * Description:根据ids删除套餐板块分类
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找套餐板块分类
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    AssortmentSection selectById(Long id);

    /**
    * Description:查询套餐板块分类列表
    *
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
     List<AssortmentSection> pageAssortmentSections(AssortmentSection assortmentSection);

    /**
     * 查询套餐树列表
     * @param assortmentSection
     * @return
     */
     List<AssortmentSection> assortmentSections(AssortmentSection assortmentSection);


    /**
    * Description: 查询套餐板块分类总记录数
    *
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    long pageAssortmentSectionCounts(AssortmentSection assortmentSection);


    /**
     * 套餐版块查询
     * @param pram
     * @return
     */
    AssortmentSection findAssortmentSections(Map pram);
}

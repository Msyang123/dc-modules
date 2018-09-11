package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.Assortment;
import com.lhiot.dc.data.domain.AssortmentSectionRelation;
import com.lhiot.dc.data.domain.out.AssortmentSectionRelationResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:板块套餐关联Mapper类
* @author yijun
* @date 2018/07/24
*/
@Mapper
public interface AssortmentSectionRelationMapper {

    /**
    * Description:新增板块套餐关联
    *
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int create(AssortmentSectionRelation assortmentSectionRelation);

    /**
    * Description:根据id修改板块套餐关联
    *
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int updateById(AssortmentSectionRelation assortmentSectionRelation);

    /**
    * Description:根据ids删除板块套餐关联
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找板块套餐关联
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    AssortmentSectionRelation selectById(Long id);

    /**
    * Description:查询板块套餐关联列表
    *
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
     List<AssortmentSectionRelation> pageAssortmentSectionRelations(AssortmentSectionRelation assortmentSectionRelation);

    List<AssortmentSectionRelationResult> pageAssortmentSectionRelation(AssortmentSectionRelationResult assortmentSectionRelationResult);

    /**
    * Description: 查询板块套餐关联总记录数
    *
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    long pageAssortmentSectionRelationCounts(AssortmentSectionRelation assortmentSectionRelation);

    long pageAssortmentSectionRelationResultCounts(AssortmentSectionRelationResult assortmentSectionRelationResult);


    /**
     * Description:根据套餐板块id查找套餐
     *
     * @param id
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    List<Assortment> findAssortmentByAssortmentSectionId(Long id);
}

package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.AssortmentSectionRelation;
import com.lhiot.dc.data.domain.out.AssortmentSectionRelationResult;
import com.lhiot.dc.data.mapper.AssortmentSectionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:板块套餐关联服务类
* @author yijun
* @date 2018/07/24
*/
@Service
@Transactional
public class AssortmentSectionRelationService {

    private final AssortmentSectionRelationMapper assortmentSectionRelationMapper;

    @Autowired
    public AssortmentSectionRelationService(AssortmentSectionRelationMapper assortmentSectionRelationMapper) {
        this.assortmentSectionRelationMapper = assortmentSectionRelationMapper;
    }

    /** 
    * Description:新增板块套餐关联
    *  
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public int create(AssortmentSectionRelation assortmentSectionRelation){
        return this.assortmentSectionRelationMapper.create(assortmentSectionRelation);
    }

    /** 
    * Description:根据id修改板块套餐关联
    *  
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int updateById(AssortmentSectionRelation assortmentSectionRelation){
        return this.assortmentSectionRelationMapper.updateById(assortmentSectionRelation);
    }

    /** 
    * Description:根据ids删除板块套餐关联
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int deleteByIds(String ids){
        return this.assortmentSectionRelationMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找板块套餐关联
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public AssortmentSectionRelation selectById(Long id){
        return this.assortmentSectionRelationMapper.selectById(id);
    }

    /** 
    * Description: 查询板块套餐关联总记录数
    *  
    * @param assortmentSectionRelation
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public long count(AssortmentSectionRelation assortmentSectionRelation){
        return this.assortmentSectionRelationMapper.pageAssortmentSectionRelationCounts(assortmentSectionRelation);
    }

    public long resultCount(AssortmentSectionRelationResult assortmentSectionRelationResult){
        return this.assortmentSectionRelationMapper.pageAssortmentSectionRelationResultCounts(assortmentSectionRelationResult);
    }

    /**
     * Description: 查询板块套餐关联分页列表
     *
     * @param assortmentSectionRelationResult
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    public PagerResultObject<AssortmentSectionRelationResult> pageList(AssortmentSectionRelationResult assortmentSectionRelationResult) {
        long total = 0;
        if (assortmentSectionRelationResult.getRows() != null && assortmentSectionRelationResult.getRows() > 0) {
            total = this.resultCount(assortmentSectionRelationResult);
        }
        return PagerResultObject.of(assortmentSectionRelationResult, total,
                this.assortmentSectionRelationMapper.pageAssortmentSectionRelation(assortmentSectionRelationResult));
    }

}


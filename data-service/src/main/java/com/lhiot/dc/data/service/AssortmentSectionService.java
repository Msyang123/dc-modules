package com.lhiot.dc.data.service;

import com.leon.microx.util.ImmutableMap;
import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.AssortmentSection;
import com.lhiot.dc.data.mapper.AssortmentSectionMapper;
import com.lhiot.dc.data.mapper.AssortmentSectionRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
* Description:套餐板块分类服务类
* @author yijun
* @date 2018/07/24
*/
@Service
@Transactional
public class AssortmentSectionService {

    private final AssortmentSectionMapper assortmentSectionMapper;

    private final AssortmentSectionRelationMapper assortmentSectionRelationMapper;

    @Autowired
    public AssortmentSectionService(AssortmentSectionMapper assortmentSectionMapper, AssortmentSectionRelationMapper assortmentSectionRelationMapper) {
        this.assortmentSectionMapper = assortmentSectionMapper;
        this.assortmentSectionRelationMapper = assortmentSectionRelationMapper;
    }

    /** 
    * Description:新增套餐板块分类
    *  
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public int create(AssortmentSection assortmentSection){
        return this.assortmentSectionMapper.create(assortmentSection);
    }

    /** 
    * Description:根据id修改套餐板块分类
    *  
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int updateById(AssortmentSection assortmentSection){
        return this.assortmentSectionMapper.updateById(assortmentSection);
    }

    /** 
    * Description:根据ids删除套餐板块分类
    *  
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public int deleteByIds(String ids){
        return this.assortmentSectionMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }
    
    /** 
    * Description:根据id查找套餐板块分类
    *  
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */ 
    public AssortmentSection selectById(Long id){
        return this.assortmentSectionMapper.selectById(id);
    }

    /** 
    * Description: 查询套餐板块分类总记录数
    *  
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public long count(AssortmentSection assortmentSection){
        return this.assortmentSectionMapper.pageAssortmentSectionCounts(assortmentSection);
    }
    
    /** 
    * Description: 查询套餐板块分类分页列表
    *  
    * @param assortmentSection
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */  
    public PagerResultObject<AssortmentSection> pageList(AssortmentSection assortmentSection) {
       long total = 0;
       if (assortmentSection.getRows() != null && assortmentSection.getRows() > 0) {
           total = this.count(assortmentSection);
       }
       return PagerResultObject.of(assortmentSection, total,
              this.assortmentSectionMapper.pageAssortmentSections(assortmentSection));
    }

    /**
     * 查询套餐树列表
     * @param assortmentSection
     * @return
     */
    public List<AssortmentSection> assortmentSections(AssortmentSection assortmentSection){
        return this.assortmentSectionMapper.assortmentSections(assortmentSection);
    }

    /**
     * Description:套餐版块查询
     *
     * @param nameEn
     * @param applicationType
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    public AssortmentSection findAssortmentSections(String nameEn,String applicationType){
        AssortmentSection assortmentSection=this.assortmentSectionMapper.findAssortmentSections(ImmutableMap.of("nameEn",nameEn,"applicationType",applicationType));
        if(assortmentSection!=null) {
            //查询套餐商品
            assortmentSection.setAssortments(this.assortmentSectionRelationMapper.findAssortmentByAssortmentSectionId(assortmentSection.getId()));
        }
        return assortmentSection;
    }

}


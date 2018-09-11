package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.Assortment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:商品套餐Mapper类
* @author yijun
* @date 2018/07/24
*/
@Mapper
public interface AssortmentMapper {

    /**
    * Description:新增商品套餐
    *
    * @param assortment
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract int create(Assortment assortment);

    /**
    * Description:根据id修改商品套餐
    *
    * @param assortment
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract int updateById(Assortment assortment);

    /**
    * Description:根据ids删除商品套餐
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找商品套餐
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract Assortment selectById(Long id);


    /**
     * Description:根据关键字查找商品套餐
     *
     * @param param
     * @return
     * @author yijun
     * @date 2018/07/24 09:55:48
     */
    public abstract List<Assortment> findAssortmentByKeywords(Map param);

    /**
    * Description:查询商品套餐列表
    *
    * @param assortment
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract List<Assortment> pageAssortments(Assortment assortment);


    /**
    * Description: 查询商品套餐总记录数
    *
    * @param assortment
    * @return
    * @author yijun
    * @date 2018/07/24 09:55:48
    */
    public abstract long pageAssortmentCounts(Assortment assortment);
    
    //根据套餐id的集合查询套餐
    public abstract List<Assortment> findByIds(List<Long> list);

    public abstract long findByIdsCount(List<Long> list);

}

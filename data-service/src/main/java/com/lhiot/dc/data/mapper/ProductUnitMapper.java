package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:商品单位Mapper类
* @author Limiaojun
* @date 2018/06/01
*/
@Mapper
public interface ProductUnitMapper {

    /**
    * Description:新增商品单位
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int create(ProductUnit productUnit);

    /**
    * Description:根据id修改商品单位
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int updateById(ProductUnit productUnit);

    /**
    * Description:根据id删除商品单位
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:查询商品单位列表
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    List<ProductUnit> pageProductUnits(ProductUnit productUnit);

    /**
    * Description: 查询商品单位总记录数
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    long pageProductUnitCounts(ProductUnit productUnit);

    /**
     * Description:根据id查询商品单位信息
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:21
     */
    ProductUnit selectById(Long id);

    /**
     * Description:判断单位是否可以删除
     *
     * @param asList
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:43
     */
    int isUnitIdInUse(List<String> asList);
}

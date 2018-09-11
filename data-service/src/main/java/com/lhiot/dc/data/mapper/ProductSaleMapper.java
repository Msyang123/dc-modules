package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductSale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* Description:Mapper类
* @author yijun
* @date 2018/08/09
*/
@Mapper
public interface ProductSaleMapper {

    /**
    * Description:新增
    *
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
    int create(ProductSale productSale);

    /**
    * Description:根据id修改
    *
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
    int updateById(ProductSale productSale);

    /**
    * Description:根据ids删除
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
    ProductSale selectById(Integer id);

    /**
    * Description:查询列表
    *
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
     List<ProductSale> pageProductSales(ProductSale productSale);


    /**
    * Description: 查询总记录数
    *
    * @param productSale
    * @return
    * @author yijun
    * @date 2018/08/09 09:24:16
    */
    long pageProductSaleCounts(ProductSale productSale);

    Long findSaleCountByProductId(Map<String, Object> map);

    void deleteProductSale();

    void recordSaleCount();
}

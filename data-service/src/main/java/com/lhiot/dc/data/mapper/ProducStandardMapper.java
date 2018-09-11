package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Description:商品规格Mapper类
* @author Limiaojun
* @date 2018/07/20
*/
@Mapper
public interface ProducStandardMapper {

    /**
    * Description:新增商品规格
    *
    * @param producstandard
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
    int create(ProductStandard producstandard);

    /**
    * Description:根据id修改商品规格
    *
    * @param producstandard
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
    int updateById(ProductStandard producstandard);

    /**
    * Description:根据ids删除商品规格
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:根据id查找商品规格
    *
    * @param id
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
    ProductStandard selectById(Long id);

    /**
    * Description:查询商品规格列表
    *
    * @param producstandard
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
     List<ProductStandard> pageProducstandards(ProductStandard producstandard);


    /**
    * Description: 查询商品规格总记录数
    *
    * @param producstandard
    * @return
    * @author Limiaojun
    * @date 2018/07/20 15:33:33
    */
    long pageProducstandardCounts(ProductStandard producstandard);

    int updateBatch(@Param(value = "list") List<ProductStandard> productStandardItem);
}

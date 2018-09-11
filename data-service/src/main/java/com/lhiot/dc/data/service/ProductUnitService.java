package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductUnit;
import com.lhiot.dc.data.mapper.ProductUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
* Description:商品单位服务类
* @author Limiaojun
* @date 2018/06/01
*/
@Service
@Transactional
public class ProductUnitService {

    private final ProductUnitMapper productUnitMapper;

    @Autowired
    public ProductUnitService(ProductUnitMapper productUnitMapper) {
        this.productUnitMapper = productUnitMapper;
    }

    /**
    * Description:新增商品单位
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int create(ProductUnit productUnit){
        return this.productUnitMapper.create(productUnit);
    }

    /**
    * Description:根据id修改商品单位
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int updateById(ProductUnit productUnit){
        return this.productUnitMapper.updateById(productUnit);
    }

    /**
    * Description:根据id删除商品单位
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int deleteByIds(String ids){
        return this.productUnitMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }

    /**
    * Description: 查询商品单位总记录数
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public long count(ProductUnit productUnit){
        return this.productUnitMapper.pageProductUnitCounts(productUnit);
    }

    /**
    * Description: 查询商品单位分页列表
    *
    * @param productUnit
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public PagerResultObject<ProductUnit> pageList(ProductUnit productUnit) {
        long total = 0;
        if (productUnit.getRows() != null && productUnit.getRows() > 0) {
            total = this.count(productUnit);
        }
        return PagerResultObject.of(productUnit, total,
               this.productUnitMapper.pageProductUnits(productUnit));
    }

    /**
     * Description:根据id查询商品单位信息
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:19
     */
    public ProductUnit selectById(Long id) {

        return this.productUnitMapper.selectById(id);
    }

    /**
     * Description:判断单位是否可以删除
     *
     * @param ids
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:43
     */
    public boolean canBeDeleteUnit(String ids) {
        int count = this.productUnitMapper.isUnitIdInUse(Arrays.asList(ids.split(",")));
        return count > 0;
    }
}


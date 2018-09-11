package com.lhiot.dc.data.service;

import com.lhiot.dc.data.common.PagerResultObject;
import com.lhiot.dc.data.domain.ProductGroup;
import com.lhiot.dc.data.mapper.ProductGroupMapper;
import com.lhiot.dc.data.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
* Description:商品分类服务类
* @author Limiaojun
* @date 2018/06/01
*/
@Service
@Transactional
public class ProductGroupService {

    private final ProductGroupMapper productGroupMapper;
    private final ProductMapper productMapper;

    @Autowired
    public ProductGroupService(ProductGroupMapper productGroupMapper, ProductMapper productMapper) {
        this.productGroupMapper = productGroupMapper;
        this.productMapper = productMapper;
    }

    /**
    * Description:新增商品分类
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int create(ProductGroup productGroup){
        return this.productGroupMapper.create(productGroup);
    }

    /**
    * Description:根据id修改商品分类
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int updateById(ProductGroup productGroup){
        return this.productGroupMapper.updateById(productGroup);
    }

    /**
    * Description:根据id删除商品分类
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public int deleteByIds(String ids){
        return this.productGroupMapper.deleteByIds(Arrays.asList(ids.split(",")));
    }

    /**
    * Description: 查询商品分类总记录数
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public long count(ProductGroup productGroup){
        return this.productGroupMapper.pageProductGroupCounts(productGroup);
    }

    /**
    * Description: 查询商品分类分页列表
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    public PagerResultObject<ProductGroup> pageList(ProductGroup productGroup) {
        long total = 0;
        if (productGroup.getRows() != null && productGroup.getRows() > 0) {
            total = this.count(productGroup);
        }
        return PagerResultObject.of(productGroup, total,
               this.productGroupMapper.pageProductGroups(productGroup));
    }



    /**
     * 获取商品分类信息的树结构
     * @return
     */
    public List<ProductGroup> productGroupTree(){
            return this.productGroupMapper.productGroupTree();
    }


    /**
     * 判断商品分类信息是否可以被删除
     * @param ids 商品分类信息的id，以逗号分隔
     * @return
     */
    public boolean canDeleteGroup(String ids){
            List<String> idList = Arrays.asList(ids.split(","));
            int productCount = productMapper.findProductByCode(idList);
            int groupCount = productGroupMapper.findProductGroupCountsByParentId(idList);
            return productCount>0 || groupCount>0;
    }

    /**
     * Description:根据id查找商品分类
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018/06/01 20:08:31
     */
     public ProductGroup selectById(Long id){
         return this.productGroupMapper.selectById(id);
     }
}


package com.lhiot.dc.data.mapper;

import com.lhiot.dc.data.domain.ProductGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:商品分类Mapper类
* @author Limiaojun
* @date 2018/06/01
*/
@Mapper
public interface ProductGroupMapper {

    /**
    * Description:新增商品分类
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int create(ProductGroup productGroup);

    /**
    * Description:根据id修改商品分类
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int updateById(ProductGroup productGroup);

    /**
    * Description:根据id删除商品分类
    *
    * @param ids
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    int deleteByIds(List<String> ids);

    /**
    * Description:查询商品分类列表
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    List<ProductGroup> pageProductGroups(ProductGroup productGroup);

    /**
    * Description: 查询商品分类总记录数
    *
    * @param productGroup
    * @return
    * @author Limiaojun
    * @date 2018/06/01 17:46:50
    */
    long pageProductGroupCounts(ProductGroup productGroup);

    /**
     * Description:获取商品分类信息的树结构
     *
     * @return
     * @author Limiaojun
     * @date 2018-06-01 19:44
     */
    List<ProductGroup> productGroupTree();

    /**
     * Description:根据parentid查询商品分类信息记录数
     *
     * @param idList
     * @return
     * @author Limiaojun
     * @date 2018-06-01 19:45
     */
    int findProductGroupCountsByParentId(List<String> idList);

    /**
     * Description:根据id查找商品分类
     *
     * @param id
     * @return
     * @author Limiaojun
     * @date 2018-06-01 20:08
     */
    ProductGroup selectById(Long id);
}

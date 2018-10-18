package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.CategoryItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* Description:字典数据Mapper类
* @author yijun
* @date 2018/10/12
*/
@Mapper
@Repository
public interface CategoryItemMapper {

    /**
    * Description:新增字典数据
    *
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int create(CategoryItem categoryItem);

    /**
    * Description:根据id修改字典数据
    *
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int updateById(CategoryItem categoryItem);

    /**
    * Description:根据ids删除字典数据
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int deleteByIds(List<String> ids);


    /**
     * Description:根据字典ids删除字典数据
     *
     * @param ids
     * @return
     * @author yijun
     */
    int deleteByCateagoryIds(List<String> ids);

    /**
    * Description:根据id查找字典数据
    *
    * @param id
    * @return
    * @author yijun
    */
    CategoryItem selectById(Long id);

    /**
     * Description:根据cgCode查找字典数据
     *
     * @param cgCode
     * @return
     * @author yijun
     */
    List<CategoryItem> selectByCgCode(String cgCode);


    /**
    * Description:查询字典数据列表
    *
    * @param categoryItem
    * @return
    * @author yijun
    */
     List<CategoryItem> pageCategoryItems(CategoryItem categoryItem);


    /**
    * Description: 查询字典数据总记录数
    *
    * @param categoryItem
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int pageCategoryItemCounts(CategoryItem categoryItem);

    CategoryItem findByCode(Map<String,Object> map);
}

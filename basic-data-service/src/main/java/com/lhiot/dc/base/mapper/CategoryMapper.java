package com.lhiot.dc.base.mapper;

import com.lhiot.dc.base.model.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* Description:字典Mapper类
* @author yijun
* @date 2018/10/12
*/
@Mapper
public interface CategoryMapper {

    /**
    * Description:新增字典
    *
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int create(Category category);

    /**
    * Description:根据id修改字典
    *
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int updateById(Category category);

    /**
    * Description:根据ids删除字典
    *
    * @param ids
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int deleteByIds(java.util.List<String> ids);

    /**
    * Description:根据id查找字典
    *
    * @param id
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    Category selectById(Long id);

    /**
     * Description:根据code查找字典
     *
     * @param code
     * @return
     * @author yijun
     * @date 2018/10/12 09:41:14
     */
    Category selectByCode(String code);


    /**
    * Description:查询字典列表
    *
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
     List<Category> pageCategorys(Category category);


    /**
    * Description: 查询字典总记录数
    *
    * @param category
    * @return
    * @author yijun
    * @date 2018/10/12 09:41:14
    */
    int pageCategoryCounts(Category category);
}

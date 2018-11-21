package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.UiPosition;
import com.lhiot.dc.model.UiPositionParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/20 16:17
 */
@Mapper
@Repository
public interface UiPositionMapper {

    /**
     * 新增位置
     *
     * @param UiPosition对象
     * @return 执行结果
     */
    int insert(UiPosition uiPosition);


    /**
     * 修改位置
     *
     * @param UiPosition对象
     * @return 执行结果
     */
    int updateById(UiPosition uiPosition);


    /**
     * 根据code查找单个位置
     *
     * @param code
     * @return 位置对象
     */
    UiPosition findByCode(@Param("code") String code);


    /**
     * 根据ID查找单个位置
     *
     * @param positionId
     * @return 商品分类对象
     */
    UiPosition findById(Long positionId);


    /**
     * 根据ID集合删除位置集合
     *
     * @param ids
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询位置信息列表
     *
     * @param param
     * @return 商品分类信息列表
     */
    List<UiPosition> findList(UiPositionParam param);

    /**
     * 查询位置信息总数
     *
     * @param param
     * @return 总数
     */
    int findCount(UiPositionParam param);



}

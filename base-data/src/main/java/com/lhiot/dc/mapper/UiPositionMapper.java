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
     * @param uiPosition 位置对象
     * @return 执行结果
     */
    int insert(UiPosition uiPosition);


    /**
     * 修改位置
     *
     * @param uiPosition 位置对象
     * @return 执行结果
     */
    int updateById(UiPosition uiPosition);


    /**
     * 根据code查找单个位置
     *
     * @param code 位置code
     * @return 位置对象
     */
    UiPosition findByCode(@Param("code") String code);


    /**
     * 根据ID查找单个位置
     *
     * @param positionId 位置ID
     * @return 位置对象
     */
    UiPosition findById(Long positionId);


    /**
     * 根据ID集合删除位置集合
     *
     * @param ids 位置ID集合
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询位置信息列表
     *
     * @param param 参数
     * @return 位置信息列表
     */
    List<UiPosition> findList(UiPositionParam param);

    /**
     * 查询位置信息总数
     *
     * @param param 参数
     * @return 总数
     */
    int findCount(UiPositionParam param);


}

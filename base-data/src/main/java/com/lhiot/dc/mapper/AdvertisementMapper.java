package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.Advertisement;
import com.lhiot.dc.model.AdvertisementParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaojian  created in  2018/11/21 10:03
 */
@Mapper
@Repository
public interface AdvertisementMapper {

    /**
     * 新增广告
     *
     * @param Advertisement对象
     * @return 执行结果
     */
    int insert(Advertisement advertisement);


    /**
     * 修改广告
     *
     * @param Advertisement对象
     * @return 执行结果
     */
    int updateById(Advertisement advertisement);


    /**
     * 根据ID查找单个广告
     *
     * @param id
     * @return 广告对象
     */
    Advertisement findById(Long id);


    /**
     * 根据ID集合删除广告集合
     *
     * @param ids
     * @return 执行结果
     */
    int deleteByIds(@Param("ids") String ids);


    /**
     * 查询广告信息列表
     *
     * @param param
     * @return 商品分类信息列表
     */
    List<Advertisement> findList(AdvertisementParam param);

    /**
     * 查询广告信息总数
     *
     * @param param
     * @return 总数
     */
    int findCount(AdvertisementParam param);


}

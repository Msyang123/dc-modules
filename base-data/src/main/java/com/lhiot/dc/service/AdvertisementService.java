package com.lhiot.dc.service;

import com.leon.microx.web.result.Pages;
import com.lhiot.dc.entity.Advertisement;
import com.lhiot.dc.mapper.AdvertisementMapper;
import com.lhiot.dc.model.AdvertisementParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author xiaojian  created in  2018/11/21 10:02
 */
@Service
@Slf4j
@Transactional
public class AdvertisementService {
    private AdvertisementMapper advertisementMapper;

    public AdvertisementService(AdvertisementMapper advertisementMapper) {
        this.advertisementMapper = advertisementMapper;
    }

    /**
     * 新增广告
     *
     * @param advertisement 广告对象
     * @return 返回广告Id
     */
    public Long addAdvertisement(Advertisement advertisement) {
        advertisement.setCreateAt(Date.from(Instant.now()));
        advertisementMapper.insert(advertisement);
        return advertisement.getId();
    }


    /**
     * 修改广告
     *
     * @param advertisement 广告对象
     * @return 执行结果 true 或者 false
     */
    public boolean update(Advertisement advertisement) {
        return advertisementMapper.updateById(advertisement) > 0;
    }


    /**
     * 根据广告ID查找单个广告
     *
     * @param id 广告ID
     * @return 广告对象
     */
    public Advertisement findById(Long id) {
        return advertisementMapper.findById(id);
    }


    /**
     * 根据Id集合批量删除广告信息
     *
     * @param ids 广告Id集合
     * @return 执行结果 true 或者 false
     */
    public boolean batchDeleteByIds(String ids) {
        return advertisementMapper.deleteByIds(ids) > 0;
    }


    /**
     * 查询广告信息列表
     *
     * @param param 参数
     * @return 分页广告信息数据
     */
    public Pages<Advertisement> findList(AdvertisementParam param) {
        List<Advertisement> list = advertisementMapper.findList(param);
        boolean pageFlag = Objects.nonNull(param.getPage()) && Objects.nonNull(param.getRows()) && param.getPage() > 0 && param.getRows() > 0;
        int total = pageFlag ? advertisementMapper.findCount(param) : list.size();
        return Pages.of(total, list);
    }


}

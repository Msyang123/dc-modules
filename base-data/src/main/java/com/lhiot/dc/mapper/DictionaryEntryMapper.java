package com.lhiot.dc.mapper;

import com.lhiot.dc.entity.DictionaryEntry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Leon (234239150@qq.com) created in 14:53 18.10.15
 */
@Mapper
@Repository
public interface DictionaryEntryMapper {

    void deleteByDictCode(String dictCode);

    void insert(DictionaryEntry entry);

    List<DictionaryEntry> selectByDictCode(String dictCode);

    /**
     *
     * @param map dictCode字典表Code，code字典项Code
     * @return DictionaryEntry
     */
    DictionaryEntry selectByDictCodeAndEntryCode(Map<String,Object> map );

    /**
     *
     * @param map dictCode字典表Code,code字典项Code
     */
    void deleteByDictCodeAndEntryCode(Map<String,Object> map);

    void updateByDictCodeAndEntryCode(Map<String, Object> map);
}

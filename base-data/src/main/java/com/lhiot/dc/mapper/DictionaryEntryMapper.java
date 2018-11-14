package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.DictionaryEntry;
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

    DictionaryEntry selectByDictCodeAndEntryCode(String dictCode, String code);

    void deleteByDictCodeAndEntryCode(String dictCode, String code);

    void updateByDictCodeAndEntryCode(Map<String, Object> map);
}

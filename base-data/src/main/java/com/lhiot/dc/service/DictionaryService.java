package com.lhiot.dc.service;

import com.leon.microx.util.Maps;
import com.leon.microx.util.StringUtils;
import com.leon.microx.web.result.Pages;
import com.leon.microx.web.result.Tips;
import com.lhiot.dc.entity.Dictionary;
import com.lhiot.dc.entity.DictionaryEntry;
import com.lhiot.dc.model.SearchParameter;
import com.lhiot.dc.mapper.DictionaryEntryMapper;
import com.lhiot.dc.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Leon (234239150@qq.com) created in 16:58 18.10.15
 */
@Service
public class DictionaryService {

    private DictionaryMapper mapper;

    private DictionaryEntryMapper entryMapper;

    @Autowired
    public DictionaryService(DictionaryMapper mapper, DictionaryEntryMapper entryMapper) {
        this.mapper = mapper;
        this.entryMapper = entryMapper;
    }

    public Pages pages(SearchParameter search) {
        List<Dictionary> dictionaries = search.isIncludeChildren()
                ? mapper.tree(search)
                : mapper.list(search);
        return Pages.of(mapper.count(search), dictionaries);
    }

    @Nullable
    public Dictionary findByCode(String code, boolean includeEntries) {
        Dictionary dictionary = mapper.selectByCode(code);
        if (includeEntries && Objects.nonNull(dictionary)) {
            dictionary.setEntries(entryMapper.selectByDictCode(dictionary.getCode()));
        }
        return dictionary;
    }

    public Tips add(Dictionary dictionary) {
        Dictionary po = mapper.selectByCode(dictionary.getCode());
        if (Objects.nonNull(po)) { // 幂等添加
            return Tips.warn("字典code重复，添加失败.");
        }
        mapper.insert(dictionary);
        return Tips.empty();
    }

    public void remove(String code) {
        Dictionary dictionary = mapper.selectByCode(code);
        if (Objects.nonNull(dictionary)) {
            entryMapper.deleteByDictCode(dictionary.getCode());
            mapper.delete(dictionary.getId());
        }
    }

    //==========================DictionaryEntry=====================================================================================================

    public void updateEntry(String dictCode, String code, DictionaryEntry entry) {
        Dictionary dictionary = mapper.selectByCode(dictCode);
        if (Objects.nonNull(dictionary)) {
            entryMapper.updateByDictCodeAndEntryCode(Maps.of(
                    "dictCode", dictCode, "code", code, "entry", entry
            ));
        }
    }

    public void deleteEntry(String dictCode, @Nullable String code) {
        if (StringUtils.isBlank(code)) {
            entryMapper.deleteByDictCode(dictCode);
            return;
        }
        entryMapper.deleteByDictCodeAndEntryCode(Maps.of("dictCode", dictCode, "code", code));
    }

    public Tips addEntry(String dictCode, DictionaryEntry entry) {
        Dictionary dictionary = this.findByCode(dictCode, false);
        if (Objects.isNull(dictionary)) {
            return Tips.warn("未找到字典，请先添加此字典");
        }
        DictionaryEntry po = entryMapper.selectByDictCodeAndEntryCode(Maps.of("dictCode", dictCode, "code", entry.getCode()));
        if (Objects.nonNull(po)) {   // 幂等
            return Tips.warn("此字典下已有该code项！请不要使用重复的code码");
        }
        entry.setDictCode(dictionary.getCode());
        entryMapper.insert(entry);
        return Tips.empty();
    }
}

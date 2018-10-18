package com.lhiot.dc.mapper;

import com.lhiot.dc.domain.SearchParameter;
import com.lhiot.dc.domain.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Leon (234239150@qq.com) created in 11:32 18.10.15
 */
@Mapper
@Repository
public interface DictionaryMapper {

    int insert(Dictionary dictionary);

    void delete(Long id);

    @Nullable Dictionary selectByCode(String dictCode);

    void update(Dictionary dictionary);

    List<Dictionary> list(SearchParameter search);

    List<Dictionary> tree(String code);

    int count(SearchParameter search);
}

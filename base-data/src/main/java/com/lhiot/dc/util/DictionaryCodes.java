package com.lhiot.dc.util;

import com.leon.microx.web.result.Tips;
import com.lhiot.dc.dictionary.DictionaryClient;
import com.lhiot.dc.dictionary.module.Dictionary;

import java.util.Optional;

/**
 * 字典Code配置类
 *
 * @author xiaojian  created in  2018/11/23 15:27
 */
public class DictionaryCodes {

    //应用类型
    public static final String APPLICATION_TYPE = "applications";


    /**
     * 验证字典项及子项是否存在
     * @param client DictionaryClient
     * @param code   字典项code
     * @param entryCode 子项code
     * @return Tips信息
     */
    public static Tips dictionaryCode(DictionaryClient client, String code, String entryCode) {
        Optional<Dictionary> optional = client.dictionary(code);
        if (!optional.isPresent()) {
            return Tips.warn("没有找到" + code + "字典");
        }
        if (!optional.get().hasEntry(entryCode)){
            return Tips.warn("没有找到字典项"+entryCode);
        }
        return Tips.info("成功找到字典及字典子项");
    }
}

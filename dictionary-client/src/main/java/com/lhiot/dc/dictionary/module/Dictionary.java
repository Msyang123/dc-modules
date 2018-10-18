package com.lhiot.dc.dictionary.module;

import lombok.Data;

import java.util.List;

/**
 * @author Leon (234239150@qq.com) created in 11:29 18.10.15
 */
@Data
public class Dictionary {

    private Dictionary parent;
    private String name;
    private String code;
    private String description;

    private List<Dictionary> children;
    private List<Entry> entries;

    @Data
    public static class Entry {
        private String dictCode;
        private String name;
        private String code;
        private Integer sort;
        private String attach;
    }
}

package com.lhiot.dc.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author xiaojian  created in  2018/12/6 8:52
 */
@Data
@ToString
@AllArgsConstructor
public class ReadArticleEvent {
    private Long articleId;
    private Long addAmount = 1L;

    public ReadArticleEvent(Long articleId){
        this.articleId = articleId;
    }
}

package com.lhiot.dc.listener;

import com.lhiot.dc.entity.Article;
import com.lhiot.dc.mapper.ArticleMapper;
import com.lhiot.dc.util.ReadArticleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author xiaojian  created in  2018/12/6 8:55
 */
@Slf4j
@Component
public class ReadArticleListener {
    private ArticleMapper articleMapper;

    public ReadArticleListener(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Async
    @EventListener
    public void onApplicationEvent(ReadArticleEvent readArticleEvent) {
        log.info("Args: {}", readArticleEvent.getRequestArgs());
        ResponseEntity entity = readArticleEvent.getResponseEntity();
        if (entity.hasBody()) {
            log.info("Response: {}", entity.getBody());
            Article article = null;
            try {
                article = (Article) entity.getBody();
            } catch (Exception exception) {
                log.warn("Not Found Article Object For Args:" + readArticleEvent.getRequestArgs());
            }
            if (article != null) {
                articleMapper.addReadAmountById(article.getId(), 1L);
            }
        }
    }
}

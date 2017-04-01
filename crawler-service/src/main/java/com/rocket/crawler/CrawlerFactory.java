package com.rocket.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xxu on 3/31/2017.
 */
@Component
public class CrawlerFactory implements CrawlController.WebCrawlerFactory<Crawler> {

    @Autowired
    private Crawler crawlerEngine;

    @Override
    public Crawler newInstance() throws Exception {
        return crawlerEngine;
    }
}


package com.rocket.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xxu on 3/31/2017.
 */
@Service
public class CrawlerService {

    private static final Logger log = LoggerFactory.getLogger(CrawlerService.class);

    @Autowired
    private CrawlerFactory crawlerEngineFactory;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrawlController crawlController;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void startCrawler() {
        crawlController.start(crawlerEngineFactory, 7);
    }

    public void saveFetchResult(FishingGear fishingGear) {
        restTemplate.postForObject("http://items-service/save", fishingGear, FishingGear.class);
    }
}

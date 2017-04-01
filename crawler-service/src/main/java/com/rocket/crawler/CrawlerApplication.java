package com.rocket.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableScheduling//run @Schedule marked method
@EnableAsync// run @Async marked method
@EnableEurekaClient
public class CrawlerApplication {

    public static ConcurrentHashMap<String, String> webUrlMap = new ConcurrentHashMap<String, String>();

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CrawlController crawlController() {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 2;
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        //config.setSocketTimeout(5000);
        //config.setShutdownOnEmptyQueue(true);
        config.setMaxDepthOfCrawling(7);

		/*
         * Instantiate the controller for this crawl.
		 */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
             /*
         * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
            List<String> seeds = new ArrayList<String>();
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/reel/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/lure/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/rod/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/cooler/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/wear/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/eye/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/foot/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/line/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/case/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/esa/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/yohin/index.html");
//            seeds.add("http://www.daiwa.com/jp/fishing/item3/snowpeak/index.html");
            seeds.add("http://www.daiwa.com/jp/fishing/item3/index.html");

            for (String seed : seeds) {
                controller.addSeed(seed);
            }



		/*
         * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controller;
    }

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }

}
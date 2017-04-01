package com.rocket.crawler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Crawler extends WebCrawler {

    @Autowired
    private CrawlerService crawlerService;

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page in
     * which we have discovered this new url and the second parameter is the new
     * url. You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic). In this example,
     * we are instructing the crawler to ignore urls that have css, js, git, ...
     * extensions and to only accept urls that start with
     * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
     * parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (!FILTERS.matcher(href).matches() && (href.startsWith("http://www.daiwa.com/jp/fishing/item3/") || href.startsWith("http://www.daiwa.com/jp/fishing/item/"))) {
//            if (CrawlerApplication.webUrlMap.contains(href)) {
////                System.out.println(href  + " : duplicate!");
////                return false;
//            } else {
//                CrawlerApplication.webUrlMap.put(href, href);
//                System.out.println("put url : " + href);
//                return true;
//            }
            // System.out.println("put url : " + href);
            return true;
        }
        return false;
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if (url.startsWith("http://www.daiwa.com/jp/fishing/item3/")) {
            return;
        }
        String brandName = "Daiwa";
        String brandId = "Daiwa";
        //http://www.daiwa.com/jp/fishing/item/wear/rain_wr/dr9006/index.html
        String name = getProductName(url);
        System.out.println("product name: " + name + " URL:" + url);
        String productId;
        String webUrl = url;
        String pageHtml = null;
        String category = getProductCatagory(url);
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            //String text = htmlParseData.getText();
            pageHtml = htmlParseData.getHtml();
            //Set<WebURL> links = htmlParseData.getOutgoingUrls();
            //  System.out.println("Text length: " + text.length());
            // System.out.println("Html length: " + html.length());
            // System.out.println("Number of outgoing links: " + links.size());
        }
        FishingGear fishingGear = new FishingGear();
        fishingGear.setBrandName(brandName);
        fishingGear.setBrandId(brandId);
        fishingGear.setWebUrl(webUrl);
        fishingGear.setName(name);
        fishingGear.setCategory(category);
        if (pageHtml != null || !pageHtml.equals(""))
            fishingGear.setPageHtml(pageHtml);
        crawlerService.saveFetchResult(fishingGear);
    }

    public Crawler() {
    }

    private String getProductCatagory(String url){
        url = url.substring(6);
        String[] a = url.split("/");
        return a[5];
    }

    private String getProductName(String url) {
        String[] a = url.split("/");
        String b = a[a.length - 2];
        if (b.contains("_")) {
            b = b.replaceAll("_", "-");
        }
        b = b.toUpperCase();
        return b;
    }

    public static void main(String argz[]){
        String url = "http://www.daiwa.com/jp/fishing/item/terminal_tackle/ayu_te/tomolure_hfhs/index.html";
        url = url.substring(6);
        String[] a = url.split("/");
        System.out.println(a[5]);

    }
}

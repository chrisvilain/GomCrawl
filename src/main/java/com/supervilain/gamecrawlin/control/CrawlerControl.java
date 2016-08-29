package com.supervilain.gamecrawlin.control;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

/**
 * @author a600413 - Christophe Vilain
 *         26/08/2016
 */
@Component
public class CrawlerControl {

    public CrawlController configureAndStart(Class<? extends WebCrawler> clazz) {
        String storage = "C:/Utilisateurs/a600413/Documents/Personnel";
        int numberOfCrawlers = 2;

        CrawlConfig configuration = new CrawlConfig();
        configuration.setCrawlStorageFolder(storage);
        configuration.setProxyHost("proxy-internet.localnet");
        configuration.setProxyPort(3128);

        PageFetcher pageFetcher = new PageFetcher(configuration);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

        try {
            CrawlController crawlController = new CrawlController(configuration, pageFetcher, robotstxtServer);
            crawlController.addSeed("http://www.ics.uci.edu/~lopes/");
            crawlController.addSeed("http://www.ics.uci.edu/~welling/");
            crawlController.addSeed("http://www.ics.uci.edu/");

            crawlController.start(clazz, numberOfCrawlers);
            return crawlController;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void stopCrawler(CrawlController controller) {
        controller.shutdown();
    }
}

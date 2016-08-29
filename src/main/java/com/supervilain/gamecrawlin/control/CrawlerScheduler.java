package com.supervilain.gamecrawlin.control;

import com.supervilain.gamecrawlin.crawler.CrawlerOne;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author a600413 - Christophe Vilain
 *         29/08/2016
 */
@Component
public class CrawlerScheduler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerScheduler.class);

    @Autowired
    private CrawlerControl crawlerControl;

    private CrawlController crawlController;

    public CrawlController getCrawlController() {
        return crawlController;
    }

    public void setCrawlController(CrawlController crawlController) {
        this.crawlController = crawlController;
    }

    //TODO real times
    @Scheduled(cron = "0 46 16 * * *")
    public void start() {
//        CrawlController controller = this.crawlerControl.configureAndStart(CrawlerOne.class);
//        setCrawlController(controller);
        System.out.println("Starting the CRON job !");
    }

    //TODO real times
    @Scheduled(cron = "0 47 16 * * *")
    public void stop() {
//        this.getCrawlController().shutdown();
        System.out.println("Done with the CRON job.");
    }
}

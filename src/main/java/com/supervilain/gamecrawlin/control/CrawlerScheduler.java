package com.supervilain.gamecrawlin.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author a600413 - Christophe Vilain
 *         29/08/2016
 */
@Component
public class CrawlerScheduler {

    private static final Logger log = LoggerFactory.getLogger(CrawlerScheduler.class);

    //TODO real times
    @Scheduled(cron = "0 46 16 * * *")
    public void start() {
        System.out.println("Starting the CRON job !");
    }

    //TODO real times
    @Scheduled(cron = "0 47 16 * * *")
    public void stop() {
        System.out.println("Done with the CRON job.");
    }
}

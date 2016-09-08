package com.supervilain.gamecrawlin.crawler;

import java.util.HashMap;
import java.util.Map;

import com.supervilain.gamecrawlin.crawler.analyzer.GameClassifier;
import com.supervilain.gamecrawlin.jdbc.CustomDbSupport;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author a600413 - Christophe Vilain
 *         07/09/2016
 */
public class CrawlerLogic {

    private static final Logger log = LoggerFactory.getLogger(CrawlerLogic.class);

    @Autowired
    private CustomJSoupCrawlingMethods methodsRepo;

    @Autowired
    private CustomDbSupport customDbSupport;

    @Autowired
    private GameClassifier gameClassifier;

    private final String startUrl = "";
    private final String intermediateStorageLocation = "";
    private boolean proceed = true;

    public void boot(String startUrl) {
        HashMap<String, String> sortedGames = sort(crawl(startUrl));
        logic(sortedGames);
    }

    public void logic(HashMap<String, String> map) {
        if(proceed) {
            log.info("Processing a new data batch.");
            for (Map.Entry entry: map.entrySet()) {
                HashMap toBeStored = sort(crawl(entry.getValue().toString()));
                writeHashMapToCsv(toBeStored);
            }
            log.info("Finished the processing.");
            logic(loadMostRecentStoredMap());
        } else {
            log.info("Crawler stopped.");
        }
    }

    public void writeHashMapToCsv(HashMap<String, String> map) {

    }

    public HashMap loadMostRecentStoredMap() {

        return null;
    }

    public HashMap crawl(String url) {
        Document doc = methodsRepo.getTo(url);
        return methodsRepo.retrievePageSC2HyperLinks(doc);
    }

    public HashMap sort(HashMap<String, String> map) {
        HashMap sorted = new HashMap();
        for (Map.Entry entry: map.entrySet()) {
            if (customDbSupport.hrefExistsInDb(entry.getValue().toString())) {
                log.info("The SC2 game is already in database : {}", entry.getKey().toString());
            } else {
                sorted.put(entry.getKey(), entry.getValue());
                customDbSupport.addAnSC2Game(gameClassifier.classifySC2Game(entry.getKey().toString()));
                log.info("A new SC2 game was found and added to the database.");
            }
        }
        return sorted;
    }

}

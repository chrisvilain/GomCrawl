package com.supervilain.gamecrawlin.crawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pengyifan.commons.collections.tree.TreeNode;
import com.supervilain.gamecrawlin.Model.ProcessTuple;
import com.supervilain.gamecrawlin.crawler.analyzer.GameClassifier;
import com.supervilain.gamecrawlin.jdbc.CustomDbSupport;

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

    private volatile boolean logicReadyToProceed = true;
    private volatile boolean proceed = true;
    private final String startUrl = "";
    private final String intermediateStorageLocation = "";

    //TODO experimental
    public void nodeBoot(String startUrl) {
        Document doc = methodsRepo.getTo(startUrl);
        nodeLogic(new TreeNode(new ProcessTuple(doc.title(), startUrl)));
    }

    public void boot(String startUrl) {
        HashMap<String, String> sortedGames = sort(crawl(startUrl));
        logic(sortedGames);
    }

    public void nodeLogic(TreeNode rootNode) {
        if(proceed) {
            while (!this.isLogicReadyToProceed()) {}
            ProcessTuple tuple = (ProcessTuple) rootNode.getObject();
            HashMap map = methodsRepo.retrievePageSC2HyperLinks(methodsRepo.getTo(tuple.getValue()));
            prepareNextStep(sort(rootNode, map));
        } else {
            log.info("Crawler stopped.");
        }
    }

    public void prepareNextStep(List<TreeNode> list) {
        this.setLogicReadyToProceed(false);
        for (TreeNode node: list) {
            nodeLogic(node);
        }
        this.setLogicReadyToProceed(true);
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

    //TODO experimental
    public List<TreeNode> sort(TreeNode parentNode, HashMap<String, String> games) {
        List<TreeNode> sortedList = null;
        for (Map.Entry entry: games.entrySet()) {
            if (customDbSupport.hrefExistsInDb(entry.getValue().toString())) {
                log.info("The SC2 game is already in database : {}", entry.getKey().toString());
            } else {
                TreeNode node = new TreeNode(new ProcessTuple(entry.getKey().toString(), entry.getValue().toString()));
                node.setParent(parentNode);
                sortedList.add(node);
                log.info("A new SC2 game was found.");
            }
        }
        return sortedList;
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

    public boolean isLogicReadyToProceed() {
        return logicReadyToProceed;
    }

    public void setLogicReadyToProceed(boolean logicReadyToProceed) {
        this.logicReadyToProceed = logicReadyToProceed;
    }

    public boolean isProceed() {
        return proceed;
    }

    public void setProceed(boolean proceed) {
        this.proceed = proceed;
    }
}

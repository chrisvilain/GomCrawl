package com.supervilain.gamecrawlin.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.pengyifan.commons.collections.tree.TreeNode;
import com.supervilain.gamecrawlin.Model.ProcessTuple;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.supervilain.gamecrawlin.Enums.KnownSC2Players;
import com.supervilain.gamecrawlin.Enums.SC2Tournament;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author a600413 - Christophe Vilain
 *         01/09/2016
 */

@Component
public class CustomJSoupCrawlingMethods {

    private String separator = String.valueOf('_');
    private String superClassifierSC2 = "StarCraft 2";

    public Document getTo(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return doc;
    }

    public HashMap<String, String> retrievePageSC2HyperLinks(Document document) {
        HashMap<String, String> SC2HyperLinks = null;
        Elements links = document.getElementsByTag("a");
        for (Element hyperlink: links) {
            String title = hyperlink.attr("title");
            if(isASC2Game(title)) {
                SC2HyperLinks.put(title, hyperlink.toString());
            }
        }
        return SC2HyperLinks;
    }

    public boolean isASC2Game(String title) {
        if (title.contains(superClassifierSC2)) {
            return true;
        }
        for (SC2Tournament tournament: SC2Tournament.values()) {
            String[] splitTournament = tournament.toString().split(separator);
            for (String elem: splitTournament) {
                if(title.contains(elem)) {
                    return true;
                }
            }
        }
        for (KnownSC2Players players: KnownSC2Players.values()) {
            if(title.contains(players.toString())) {
                return true;
            }
        }
        return false;
    }
}

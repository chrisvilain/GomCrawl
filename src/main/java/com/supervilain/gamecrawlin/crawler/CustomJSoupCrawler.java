package com.supervilain.gamecrawlin.crawler;

import java.util.HashMap;

import com.supervilain.gamecrawlin.Enums.KnownSC2Players;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.supervilain.gamecrawlin.Enums.SC2Tournament;

/**
 * @author a600413 - Christophe Vilain
 *         01/09/2016
 */
public class CustomJSoupCrawler {

    private String separator = String.valueOf('_');

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

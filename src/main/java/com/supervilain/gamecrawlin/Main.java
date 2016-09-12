package com.supervilain.gamecrawlin;

import com.supervilain.gamecrawlin.crawler.CrawlerLogic;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

/**
 * @author a600413 - Christophe Vilain
 *         26/08/2016
 */
@SpringBootApplication
@EnableScheduling
public class Main implements CommandLineRunner{

    public static void main(String[] args) {

        SpringApplication.run(Main.class);
        //String title = "Life vs herO ZvP Code S Ro4 Match 2 Set 1, 2015 GSL Season 1 - StarCraft 2";
/*        SC2Game game = GameClassifier.classifySC2Game(title);
        System.out.println(game.toString());*/
/*        System.setProperty("https.proxyHost", "proxy-internet.localnet");
        System.setProperty("https.proxyPort", "3128");
        String url = "https://www.youtube.com/watch?v=Qf1YkGmlstc";
        try {
            Document document = Jsoup.connect(url).get();
            Elements links = document.getElementsByTag("a");
            for (Element element: links) {
                System.out.println(element.attr("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CrawlerLogic crawlerLogic;

    private static final String dropIfExists = "DROP TABLE sc2games IF EXISTS";
    private static final String createTable = "CREATE TABLE sc2games ("
            + "id SERIAL, title VARCHAR(255), href VARCHAR(255))";

    @Override
    public void run(String... strings) {
        jdbcTemplate.execute(dropIfExists);
        jdbcTemplate.execute(createTable);
        String url = "https://www.youtube.com/watch?v=Qf1YkGmlstc";
        System.setProperty("https.proxyHost", "proxy-internet.localnet");
        System.setProperty("https.proxyPort", "3128");
        crawlerLogic.nodeBoot(url);
    }

}

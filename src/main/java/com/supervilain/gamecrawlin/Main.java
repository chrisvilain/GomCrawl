package com.supervilain.gamecrawlin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

//        SpringApplication.run(Main.class);
        String title = "Life vs herO ZvP Code S Ro4 Match 2 Set 1, 2015 GSL Season 1 - StarCraft 2";
/*        SC2Game game = GameClassifier.classifySC2Game(title);
        System.out.println(game.toString());*/
        String url = "https://www.youtube.com/watch?v=Qf1YkGmlstc";
        try {
            System.setProperty("https.proxyHost", "proxy-internet.localnet");
            System.setProperty("https.proxyPort", "3128");
            Document doc = Jsoup.connect(url).get();
            String pageTitle = doc.title();
            System.out.println(pageTitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String dropIfExists = "DROP TABLE sc2games IF EXISTS";
    private static final String createTable = "CREATE TABLE sc2games ("
            + "id SERIAL, title VARCHAR(255), href VARCHAR(255))";

    @Override
    public void run(String... strings) {
        jdbcTemplate.execute(dropIfExists);
        jdbcTemplate.execute(createTable);
    }

}

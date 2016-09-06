package com.supervilain.gamecrawlin;

import com.supervilain.gamecrawlin.Model.SC2Game;
import com.supervilain.gamecrawlin.crawler.analyze.GameClassifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author a600413 - Christophe Vilain
 *         26/08/2016
 */
@SpringBootApplication
@EnableScheduling
public class Main {

    public static void main(String[] args) {

        //SpringApplication.run(Main.class);
        String title = "Life vs herO ZvP Code S Ro4 Match 2 Set 1, 2015 GSL Season 1 - StarCraft 2";
        SC2Game game = GameClassifier.classifySC2Game(title);
        System.out.println(game.toString());
    }

}

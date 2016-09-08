package com.supervilain.gamecrawlin.jdbc;

import com.supervilain.gamecrawlin.Model.SC2Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author a600413 - Christophe Vilain
 *         08/09/2016
 */
@Component
public class CustomDbSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String queryOneTitle = "SELECT id FROM sc2games WHERE title = '?'";
    private static final String queryOneHref = "SELECT id FROM sc2games WHERE href = '?'";
    private static final String insertASC2Game = "INSERT INTO sc2games (title, href) VALUES ('?', '?')";

    public boolean titleExistsInDb(String title) {
        Integer retrievedId = jdbcTemplate.queryForObject(queryOneTitle, Integer.class, title);
        return retrievedId != null;
    }

    public boolean hrefExistsInDb(String href) {
        Integer retrievedId = jdbcTemplate.queryForObject(queryOneHref, Integer.class, href);
        return retrievedId != null;
    }

    public void addAnSC2Game (SC2Game sc2Game) {
        jdbcTemplate.update(insertASC2Game, sc2Game.getTitle(), sc2Game.getHyperlink());
    }

}

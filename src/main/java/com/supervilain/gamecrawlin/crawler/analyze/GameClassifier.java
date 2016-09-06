package com.supervilain.gamecrawlin.crawler.analyze;

import com.supervilain.gamecrawlin.Enums.*;
import com.supervilain.gamecrawlin.Model.SC2Game;

/**
 * @author a600413 - Christophe Vilain
 *         01/09/2016
 */
public class GameClassifier {

    private static String separator = String.valueOf('_');
    private static String spacing = String.valueOf(' ');
    private static String[] editions = {"2015", "2016", "2017"};

    public static SC2Game classifySC2Game(String title) {
        SC2Game game = new SC2Game();
        boolean foundPlayer1 = false;
        for (int i = 0; i < KnownSC2Players.values().length; i++) {
            String player = KnownSC2Players.values()[i].toString();
            if(title.contains(player) && !foundPlayer1) {
                game.setPlayer1(player);
                foundPlayer1 = true;
            }
            else if(title.contains(player) && foundPlayer1) {
                game.setPlayer2(player);
                break;
            }
        }
        for (int i = 0; i < SC2Tournament.values().length; i++) {
            String tournament = SC2Tournament.values()[i].toString();
            boolean isGsl = tournament.split(separator).length > 1;
            if(isGsl) {
                tournament = tournament.split(separator)[1] + spacing + tournament.split(separator)[2];
            }
            if(title.contains(tournament)) {
                game.setCompetition(SC2Tournament.values()[i]);
                break;
            }
        }
        for (int i = 0; i < CompetitionStage.values().length; i++) {
            String stage = CompetitionStage.values()[i].toString();
            if(title.contains(stage)) {
                game.setStage(CompetitionStage.values()[i]);
                break;
            }
        }
        for (int i = 0; i < SC2TournamentSeason.values().length; i++) {
            String season =  SC2TournamentSeason.values()[i].toString().replace(separator, spacing);
            if(title.contains(season)) {
                game.setSeason(SC2TournamentSeason.values()[i]);
                break;
            }
        }
        for (int i = 0; i < editions.length; i++) {
            if(title.contains(editions[i])) {
                game.setEdition(editions[i]);
                break;
            }
        }
        for (int i = 0; i < MatchUps.values().length; i++) {
            String matchUp = MatchUps.values()[i].toString();
            if(title.contains(matchUp)) {
                game.setMatchUp(MatchUps.values()[i]);
                break;
            }
        }
        return game;
    }
}

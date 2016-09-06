package com.supervilain.gamecrawlin.Model;

import com.supervilain.gamecrawlin.Enums.CompetitionStage;
import com.supervilain.gamecrawlin.Enums.MatchUps;
import com.supervilain.gamecrawlin.Enums.SC2Tournament;
import com.supervilain.gamecrawlin.Enums.SC2TournamentSeason;

/**
 * @author a600413 - Christophe Vilain
 *         01/09/2016
 */
public class SC2Game {

    private String hyperlink;

    private String edition;

    private String player1;

    private String player2;

    private CompetitionStage stage;

    private SC2Tournament competition;

    private SC2TournamentSeason season;

    private MatchUps matchUp;

    public MatchUps getMatchUp() {
        return matchUp;
    }

    public void setMatchUp(MatchUps matchUp) {
        this.matchUp = matchUp;
    }

    public SC2TournamentSeason getSeason() {
        return season;
    }

    public void setSeason(SC2TournamentSeason season) {
        this.season = season;
    }

    public SC2Tournament getCompetition() {
        return competition;
    }

    public void setCompetition(SC2Tournament competition) {
        this.competition = competition;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public CompetitionStage getStage() {
        return stage;
    }

    public void setStage(CompetitionStage stage) {
        this.stage = stage;
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    public SC2Game() {}

    @Override
    public String toString() {
        return this.getCompetition().toString() + ", " + this.getEdition() + " " + this.getSeason().toString()
                + " - " + this.getStage() + ", " + this.getMatchUp() + " : " + this.getPlayer1() + " VS " + this.getPlayer2();
    }

}

package org.ebuka_ih;

public class SeasonStats {

    private final int season;

    private int gamesPlayed;

    private int points;
    private int shotAttempts;
    private int shotsMade;
    private int turnovers;
    private int steals;
    private int highest;
    private int team;
    private int overall;
    public SeasonStats(int year, int team) {
        this.season = year;
        this.team = team;
        this.highest = -1000;
    }
    public SeasonStats(int year, int gamesPlayed, int points, int shotAttempts, int shotsMade, int turnovers, int steals, int highest, int team, int overall) {
        this.season = year;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
        this.shotAttempts = shotAttempts;
        this.shotsMade = shotsMade;
        this.turnovers = turnovers;
        this.steals = steals;
        this.highest = highest;
        this.team = team;
        this.overall = overall;
    }
    public int getSeason() {
        return this.season;
    }
    public void addPoints(int points) {
        this.points+= points;

        if(points > this.highest) {
            this.highest = points;
        }
    }
    public void addAttempts(int attempts) {
        this.shotAttempts+=attempts;
    }
    public void addShotsMade(int made) {
        this.shotsMade+= made;
    }
    public void addTurnovers(int turnovers) {
        this.turnovers += turnovers;
    }
    public void addSteals(int steals) {
        this.steals += steals;
    }
    public void addGamesPlayed(int gamesPlayed) {
        this.gamesPlayed+=gamesPlayed;
    }
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }
    public int getPoints() {
        return this.points;
    }
    public int getShotAttempts() {
        return this.shotAttempts;
    }
    public int getShotsMade() {
        return this.shotsMade;
    }
    public int getTurnovers() {
        return this.turnovers;
    }
    public int getSteals() {
        return this.steals;
    }
    public int getHighestScore() {
        return this.highest;
    }
    public int getTeam() {
        return this.team;
    }
    public void setOverall(int overall) {
        this.overall = overall;
    }
    public int getOverall() {
        return this.overall;
    }

}



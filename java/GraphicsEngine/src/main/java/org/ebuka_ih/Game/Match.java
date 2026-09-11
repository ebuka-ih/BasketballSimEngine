package org.ebuka_ih.Game;

import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Court.MatchGrid;
import org.ebuka_ih.Player.Player;
import org.ebuka_ih.Player.Stats;
import org.ebuka_ih.Team;
import org.ebuka_ih.en.Offensive.GameSituation;
import org.ebuka_ih.en.Offensive.PossessionState;
import org.ebuka_ih.en.Offensive.ScoreSituation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Match {

    private Ball ball;

    private Team home;
    private Team away;

    private int homeScore;
    private int awayScore;
    private int dribbles;

    private List<Player> homeTeamLineup;
    private List<Player> awayTeamLineup;

    private PossessionState possessionState;
    private GameSituation gameSituation;
    private MatchGrid grid;

    private Map<Player, Player> defensiveMatchups = new HashMap<>();

    public Match(Team home, Team away) {
        this.grid = new MatchGrid(this, 150, 75);

        this.ball = new Ball(this, new Coordinate(75, 37));
        this.home = home;
        this.away = away;
        this.homeTeamLineup = home.getPlayers();
        this.awayTeamLineup = away.getPlayers();
        this.gameSituation = GameSituation.NORMAL;
    }

    public Team getHome() {
        return home;
    }
    public Team getAway() {
        return away;
    }


    public boolean isHome(Player player) {
        return home.getPlayers().contains(player);
    }
    public boolean isAway(Player player) {
        return away.getPlayers().contains(player);
    }
    public int getDribbles() {
        return dribbles;
    }

    public void incrementDribbles() {
        dribbles++;
    }

    public void resetDribbles() {
        dribbles = 0;
    }
    public void changePossession(Player player) {
        if (player == null) {
            return;
        }
        resetDribbles();

        ball.setHolder(player);
        possessionState = PossessionState.ESTABLISHED;
    }
    public Ball getBall() {
        return this.ball;
    }
    public Player getBallHolder() {
        return ball.getHolder();
    }
    public List<Player> getHomeTeamLineup() {
        return this.homeTeamLineup;
    }
    public List<Player> getAwayTeamLineup() {
        return this.awayTeamLineup;
    }
    public MatchGrid getGrid() {
        return this.grid;
    }
    public PossessionState getPossessionState() {
        return this.possessionState;
    }
    public GameSituation getGameSituation() {
        return this.gameSituation;
    }
    public ScoreSituation getScoreSituation(Player player){
        int difference;
        if(player.getTeam() == home) {
            difference = homeScore - awayScore;
        } else {
            difference = awayScore - homeScore;
        }
        if(difference >= 20) {
            return ScoreSituation.BLOWOUT_LEADING;
        } else if(difference >= 10) {
            return ScoreSituation.LEADING;
        } else if(difference > 0) {
            return ScoreSituation.CLOSE_LEADING;
        } else if(difference == 0) {
            return ScoreSituation.TIED;
        } else if(difference <= -20) {
            return ScoreSituation.BLOWOUT_TRAILING;
        } else if(difference <= -10) {
            return ScoreSituation.TRAILING;
        } else {
            return ScoreSituation.CLOSE_TRAILING;
        }
    }

    public Player getDefender(Player offensivePlayer) {
        return defensiveMatchups.get(offensivePlayer);
    }
    public double getDefensivePressure(Player player) {
        List<Player> nearby = grid.getNearbyPlayers(player, 5);

        double pressure = 0.0;

        if(!nearby.isEmpty()) {
            for (Player p : nearby) {
                if (p == player || p.getTeam() == player.getTeam()) {
                    continue;
                }

                Stats stats = p.getStats();
                double defense;
                double blocking = stats.getBlocking();
                double defenderDistance = grid.distanceBetweenObjects(player, p);

                if (grid.distanceFromBasket(player) >= 30) {
                    defense = stats.getPerimeterDefense();
                    if (defenderDistance <= 2 && stats.getPerimeterDefense() > stats.getPhysicalDefense()) {
                        blocking *= 1.5;
                    }
                } else {
                    defense = stats.getPhysicalDefense();
                    if (defenderDistance <= 2 && stats.getPhysicalDefense() > stats.getPerimeterDefense()) {
                        blocking *= 1.5;
                    }
                }
                pressure += blocking;
                pressure += defense;
                if (defenderDistance <= 2) {
                    pressure += 20;
                } else if(defenderDistance <= 3){
                    pressure += 15;
                } else if(defenderDistance <= 5) {
                    pressure += 10;
                }
            }

        }
        return pressure;
    }
    public double getDistancePressure(Player player) {
        return grid.distanceFromBasket(player) * 1.25;
    }
    public Player getBestPassTarget(Player player) {
        Player holder = ball.getHolder();
        List<Player> teammates;
        if(holder.getTeam().equals(home)) {
            teammates = homeTeamLineup;
        } else {
            teammates = awayTeamLineup;
        }
        double bestScore = Double.NEGATIVE_INFINITY;
        Player bestTarget = null;
        for(Player teammate : teammates) {
            if(teammate.equals(player)) {
                continue;
            }

            Stats stats = teammate.getStats();
            double distanceFromTeammate = grid.distanceBetweenObjects(player, teammate);
            double distanceFromBasket = grid.distanceFromBasket(teammate);
            double defensivePressure = getDefensivePressure(teammate);
            double ballHandling = stats.getBallHandling();
            double scoring;

            if(distanceFromBasket <= 5) {
                scoring = stats.getFinishing();
            } else if(distanceFromBasket < 30) {
                scoring = stats.getMidrange();
            } else if(distanceFromBasket < 35){
                scoring = stats.getThreePoint();
            } else {
                scoring = stats.getFourPoint();
            }
            double passScore = 0;
            passScore += scoring;
            passScore += ballHandling * 0.75;

            passScore -= defensivePressure;
            passScore -= distanceFromBasket * 0.25;
            passScore -= distanceFromTeammate;

            if(passScore > bestScore) {
                bestScore = passScore;
                bestTarget = teammate;
            }

        }
        return bestTarget;
    }
    public void setDefensiveMatchup(Player offensivePlayer, Player defender) {
        defensiveMatchups.put(offensivePlayer, defender);
    }
}
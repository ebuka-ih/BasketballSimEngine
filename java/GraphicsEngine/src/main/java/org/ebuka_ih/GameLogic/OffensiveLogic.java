package org.ebuka_ih.GameLogic;


import org.ebuka_ih.Court.MatchGrid;
import org.ebuka_ih.Game.Match;
import org.ebuka_ih.Player.Player;
import org.ebuka_ih.Player.Stats;
import org.ebuka_ih.Util.Util;
import org.ebuka_ih.en.DecisionMaking.offense.BallDecision;
import org.ebuka_ih.en.DecisionMaking.offense.OffBallDecision;
import org.ebuka_ih.en.Offensive.GameSituation;
import org.ebuka_ih.en.Offensive.ScoreSituation;
import org.ebuka_ih.en.Position;
import org.ebuka_ih.en.ShotType;

import java.util.ArrayList;
import java.util.List;

public class OffensiveLogic {


    public OffensiveLogic() {

    }
    public void ballMovement() {
        BallDecision a = null;
        // if statement shot type
        switch (a) {
            case NONE -> {

            }
            case HOLD -> {

            }
            case PASS -> {

            }
            case DRIVE -> {

            }
            case SHOOT -> {

            }
            case DRIBBLE -> {

            }
        }
        // else
        ShotType c;
        //DECIDE POINT
        // pathfinder

        // BallDecision - OffensiveDecision - Movement Point - Pathfinder

        // BallDecision - ShotType(shot only) - Movement Point - Pathfinder
    }
    public void offBallMovement() {

    }
    public OffBallDecision decideOffBallAction(Player player) {
        Stats stats = player.getStats();
        Match match = player.getMatch();
        MatchGrid grid = match.getGrid();

        int finishing = stats.getFinishing();
        int midrange = stats.getMidrange();
        int threePoint = stats.getThreePoint();
        int fourPoint = stats.getFourPoint();
        int ballHandling = stats.getBallHandling();

        int physicalDefense = stats.getPhysicalDefense();
        int speed = stats.getSpeed();
        Position position = player.getPosition();


        double cutScore = 0;
        double screenScore = 0;
        double postUpScore = 0;
        double spotUpScore = 0;

        cutScore    += speed * 0.1 + ballHandling * 0.5 + finishing * 0.4;
        screenScore += speed * 0.3  + physicalDefense * 0.6 + midrange * 0.1;
        postUpScore += speed * 0.2 + finishing * 0.2 + midrange * 0.6;
        spotUpScore += threePoint * 0.6 + fourPoint * 0.25 + midrange * 0.15;

        switch (position) {
            case C -> {
                cutScore *= 0.6;
                screenScore *= 1.25;
                postUpScore *= 1.15;
                spotUpScore *= 0.75;
            }
            case PF -> {
                cutScore *= 0.8;
                screenScore *= 1.25;
                postUpScore *= 1.15;
                spotUpScore *= 0.8;
            }
            case SF -> {
                cutScore *= 1.25;
                screenScore *= 0.85;
                postUpScore *= 1.15;
                spotUpScore *= 1.0;
            }
            case SG -> {
                cutScore *= 1.15;
                screenScore *= 0.95;
                postUpScore *= 0.85;
                spotUpScore *= 1.0;
            }
            case PG -> {
                cutScore *= 1.0;
                screenScore *= 0.75;
                postUpScore *= 0.6;
                spotUpScore *= 1.25;
            }
        }

        List<OffBallDecision> chances = new ArrayList<>();
        for(int i = 0; i < cutScore; i++) {
            chances.add(OffBallDecision.CUT);
        }
        for(int i = 0; i < screenScore; i++) {
            chances.add(OffBallDecision.SCREEN);
        }
        for(int i = 0; i < postUpScore; i++) {
            chances.add(OffBallDecision.POST_UP);
        }
        for(int i = 0; i < spotUpScore; i++) {
            chances.add(OffBallDecision.SPOT_UP);
        }
        int r = Util.RANDOM.nextInt(chances.size());
        return chances.get(r);
    }
    public BallDecision decideAction(Player player) {
        Stats stats = player.getStats();
        Match match = player.getMatch();
        MatchGrid grid = match.getGrid();
        int finishing = stats.getFinishing();
        int midrange = stats.getMidrange();
        int threePoint = stats.getThreePoint();
        int fourPoint = stats.getFourPoint();
        int ballHandling = stats.getBallHandling();
        int passing = stats.getPassing();
        int clutch = stats.getClutch();
        int confidence = stats.getConfidence();
        int consistency = stats.getConsistency();
        int stamina = stats.getStamina();
        int speed = stats.getSpeed();

        double holdScore = 0.0;
        double driveScore = finishing * 0.5 + speed * 0.25 + ballHandling * 0.25;
        double shootScore = confidence * 0.3 + clutch * 0.2;
        double dribbleScore = ballHandling + stamina + speed;

        double basketDistance = grid.distanceFromBasket(player);
        double passScore = match.getDribbles() * 2.5;
        if(basketDistance <= 50) {
            passScore = passing * 0.6 + consistency * 0.15 + ballHandling * 0.25;
        }

        double pressure = match.getDefensivePressure(player);
        passScore += pressure * 0.6;
        driveScore -= pressure * 0.5;
        shootScore -= pressure * 0.3;
        dribbleScore -= pressure * 0.4;

        if(basketDistance >= 65) {
            shootScore = (150 - basketDistance) / 2000;
            driveScore = 0;
        } else if(basketDistance >= 50) {
            shootScore = (150 - basketDistance) / 1000;
            driveScore = 0;
        } else if(basketDistance > 45) {
            dribbleScore += basketDistance;
            dribbleScore *= 5;
            passScore *= 3;
        } else if(basketDistance >= 35) {
            shootScore += fourPoint * 0.5;
        } else if (basketDistance >= 30) {
            shootScore += threePoint * 0.5;
        } else {
            shootScore += midrange * 0.5;
        }

        GameSituation gameSituation = match.getGameSituation();
        ScoreSituation scoreSituation = match.getScoreSituation(player);
        switch (gameSituation) {
            case CLUTCH -> {
                passScore *= 0.75;
                driveScore *=  1.5;
                shootScore *= 1.5;
                dribbleScore *=  1.05;
            }
            case LATE_GAME -> {
                passScore *= 0.75;
                driveScore *=  1.25;
                shootScore *= 1.25;
                dribbleScore *=  1.25;
            }
            case FAST_BREAK -> {
                passScore *= 0.6;
                driveScore *=  2;
                shootScore *= 1.25;
                dribbleScore *=  1.25;
            }
        }
        switch (scoreSituation) {
            case CLOSE_TRAILING -> {
                passScore *= 0.9;
                driveScore *=  1.75;
                shootScore *= 1.25;
                dribbleScore *=  1.1;
            }
            case TRAILING -> {
                passScore *= 0.8;
                driveScore *=  1.5;
                shootScore *= 1.5;
                dribbleScore *=  1.05;
            }
            case BLOWOUT_TRAILING -> {
                passScore *= 0.7;
                driveScore *=  1.25;
                shootScore *= 1.75;
                dribbleScore *=  1.05;
            }
            case CLOSE_LEADING -> {
                passScore *= 1.0;
                driveScore *=  1.25;
                shootScore *= 1.0;
                dribbleScore *=  1.0;
            }
            case LEADING -> {
                passScore *= 1.5;
                driveScore *=  1.0;
                shootScore *= 1.0;
                dribbleScore *=  1.5;
           }
            case BLOWOUT_LEADING -> {
                passScore *= 2.0;
                driveScore *=  1.0;
                shootScore *= 1.0;
                dribbleScore *=  1.25;
            }

        }
        List<BallDecision> chances = new ArrayList<>();
        for(int i = 0; i < passScore; i++) {
            chances.add(BallDecision.PASS);
        }
        for(int i = 0; i < driveScore; i++) {
            chances.add(BallDecision.DRIVE);
        }
        for(int i = 0; i < shootScore; i++) {
            chances.add(BallDecision.SHOOT);
        }
        for(int i = 0; i < dribbleScore; i++) {
            chances.add(BallDecision.DRIBBLE);
        }
        int r = Util.RANDOM.nextInt(chances.size());

        //System.out.println("DISTANCE: " + basketDistance + "(" + match.isHome(player) + ")" +  "\nPASS SCORE: " + passScore + "\nDRIVE SCORE: " + driveScore + "\nSHOOT SCORE: " + shootScore + "\nDRIBBLE SCORE: " + dribbleScore + "\nCHOSEN: " + chosen);
        // System.out.println("DISTANCE: " + (int) basketDistance + "(" + match.isHome(player) + ")" + " CHOSEN: " + chosen + "(" + player.getCoordinate().getX() + "," + player.getCoordinate().getY() + ")" + " | DRIBBLES: " + match.getDribbles() + " | PASS SCORE: " + passScore);
        return chances.get(r);
    }
}

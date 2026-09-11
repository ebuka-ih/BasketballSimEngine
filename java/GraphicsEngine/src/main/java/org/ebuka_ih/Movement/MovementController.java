package org.ebuka_ih.Movement;

import org.ebuka_ih.Game.Match;
import org.ebuka_ih.GameLogic.OffensiveLogic;
import org.ebuka_ih.Player.Player;
import org.ebuka_ih.Player.PlayerAction;
import org.ebuka_ih.Util.Util;
import org.ebuka_ih.en.DecisionMaking.offense.BallDecision;
import org.ebuka_ih.en.DecisionMaking.offense.OffBallDecision;
import org.ebuka_ih.en.OffensiveSpots;
import org.ebuka_ih.en.PathType;
import org.ebuka_ih.en.ShotType;

public class MovementController {


    public void move(Player player) {
        int spaces = Util.RANDOM.nextInt(player.getStats().getSpeed() / 20) + 1;
        OffensiveLogic offensiveLogic = new OffensiveLogic();
        Match match = player.getMatch();
        Pathfinder pathfinder = new Pathfinder(match.getGrid());
        if(player.getRoute()  == null) {
            if(match.getHome().getPlayers().contains(player)) {
                player.setRoute(pathfinder.mapPath(player, PathType.OFFENSIVE_DECISION, player.getCoordinate(), getDefaultOffensiveSpot(player).getHomePosition(), 1, 1, null));
            } else {
                player.setRoute(pathfinder.mapPath(player, PathType.OFFENSIVE_DECISION, player.getCoordinate(), getDefaultOffensiveSpot(player).getAwayPosition(), 1, 1, null));
            }
            return;
        }
        Route route = player.getRoute();
        // System.out.println(route.getType() + route.getPoints().getLast().toString() + ":" + player.getCoordinate().toString());

        for (int i = 0; i < spaces; i++) {
            if (route.isFinished()) {
                break;
            } else {
                player.setCoordinate(route.getPoints().get(route.currentIndex()));
                route.advance();
            }
        }

        if (match.getBallHolder() != null && match.getBallHolder().equals(player)) {
            if (route.getType().equals(PathType.OFFENSIVE_DECISION)) {
                BallDecision decision = offensiveLogic.decideAction(player);
                if (decision.equals(BallDecision.DRIBBLE)) {
                    player.getMatch().incrementDribbles();
                } else {
                    Route r;
                    switch (decision) {
                        case PASS -> {
                            PlayerAction.pass(player);
                        }
                        case SHOOT -> {
                            ShotType shot = pathfinder.getShotType(player);
                            OffensiveSpots t = pathfinder.getBestShot(player, shot);
                            if (match.getHome().getPlayers().contains(player)) {
                                r = pathfinder.mapPath(player, PathType.SHOT, player.getCoordinate(), t.getHomePosition(), 0, 0, null);
                            } else {
                                r = pathfinder.mapPath(player, PathType.SHOT, player.getCoordinate(), t.getAwayPosition(), 0, 0, null);
                            }
                            route.terminate();
                            player.setRoute(r);
                            return;
                        }
                        case DRIVE -> {

                        }
                    }
                    player.getMatch().resetDribbles();
                }
            }
        }
        if(player != match.getBallHolder()) {
            if (route.getType().equals(PathType.OFFENSIVE_DECISION) || route.getType().equals(PathType.OFFBALL_MOVEMENT)) {
                OffBallDecision decision = offensiveLogic.decideOffBallAction(player);
                Route r;
                OffensiveSpots t = pathfinder.getOffballSpot(player, decision);
                if (match.getHome().getPlayers().contains(player)) {
                    r = pathfinder.mapPath(player, PathType.OFFBALL_MOVEMENT, player.getCoordinate(), t.getHomePosition(), 0, 0, null);
                } else {
                    r = pathfinder.mapPath(player, PathType.OFFBALL_MOVEMENT, player.getCoordinate(), t.getAwayPosition(), 0, 0, null);
                }

                route.terminate();
                player.setRoute(r);
            }
        }

        if (route.getType().equals(PathType.SHOT) || route.getType().equals(PathType.DRIVE)) {
            if (route.isFinished()) {
                System.out.println("FINISHED" + "(" + route.getType() + ")");
                route.runEndAction(player);
                player.setRoute(null);
            }
        }
    }
    public OffensiveSpots getDefaultOffensiveSpot(Player player) {
        OffensiveSpots[] spots = {
                OffensiveSpots.THREE_POINT_TOP,
                OffensiveSpots.THREE_POINT_LEFT,
                OffensiveSpots.THREE_POINT_RIGHT,
                OffensiveSpots.THREE_POINT_TOP_CORNER,
                OffensiveSpots.THREE_POINT_BOTTOM_CORNER
        };

        return spots[Util.RANDOM.nextInt(spots.length)];
    }
}
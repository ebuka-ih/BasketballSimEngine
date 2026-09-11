package org.ebuka_ih.Movement;


import org.ebuka_ih.Court.Coordinate;
import org.ebuka_ih.Court.GridNode;
import org.ebuka_ih.Court.MatchGrid;
import org.ebuka_ih.Game.GameObject;
import org.ebuka_ih.Game.Match;
import org.ebuka_ih.Player.Player;
import org.ebuka_ih.Player.Stats;
import org.ebuka_ih.Util.Util;
import org.ebuka_ih.en.*;
import org.ebuka_ih.en.DecisionMaking.offense.OffBallDecision;
import org.ebuka_ih.en.Offensive.ScoreSituation;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Pathfinder {

    private MatchGrid grid;

    public Pathfinder(MatchGrid grid) {
        this.grid = grid;
    }

    private boolean isClear(Route route) {

        for (GameObject gameObject : grid.getObjects()) {
            if (route.getPoints().contains(gameObject.getCoordinate())) {
                return false;
            }
        }
        return true;
    }

    private List<GridNode> neighbors(GridNode node) {
        List<GridNode> neighbors = new ArrayList<>();
        Coordinate coordinate = node.getCoordinate();
        int x = coordinate.getX();
        int y = coordinate.getY();

        for(Direction dir : Direction.values()) {
            GridNode neighbor = grid.getNode(new Coordinate(x + dir.getDx(), y + dir.getDy()));

            if(neighbor != null) {
                neighbors.add(neighbor);
            }
        }
        return neighbors;
    }
    private Direction getDirection(Coordinate start, Coordinate end) {
        int dx = Integer.compare(start.getX(), end.getX());
        int dy = Integer.compare(start.getY(), end.getY());

       for(Direction dir : Direction.values()) {
           if(dir.getDx() == dx && dir.getDy() == dy) {
               return dir;
           }
       }
       return null;
    }
    private double evaluateDirection(Direction desired, Direction actual) {
        if (actual == null) {
            return -100;
        }
        int difference = Math.abs(desired.getAngle() - actual.getAngle());
        if (difference > 180) {
            difference = 360 - difference;
        }
        int dot = 4 - (difference / 45);

        return dot * 12.5;
    }

    private double evaluateNode(Player evaluator, Direction direction, GridNode endPoint, GridNode parent, GridNode node, int teammateRadius, int defenderRadius, Color color) {
        Direction nodeDir = getDirection(parent.getCoordinate(), node.getCoordinate());
        double score = 0;
        if (nodeDir == null) {
            return -500;
        }

        if(grid.hasObject(node)) {
            return -500;
        }
        score += evaluateDirection(direction, nodeDir);

        for(GameObject object : grid.getObjects()) {
            if(grid.getNode(object.getCoordinate()) == parent) {
                continue;
            }
            if(object instanceof Player && neighbors(node).contains(grid.getNode(object.getCoordinate()))) {
                return -500;
            }
        }
        if(node.equals(endPoint)) {
            return 500;
        }

        Coordinate a = node.getCoordinate();
        Coordinate b = endPoint.getCoordinate();

        double distance = Math.hypot(
                b.getX() - a.getX(),
                b.getY() - a.getY()
        );
        double distanceScore = distance * 1.75;
        score -= distanceScore;

        double teamScore = 25;
        double defenderScore = 25;


        for(GameObject obj : grid.getObjects()) {
            if(obj instanceof Player player) {
                if(player == evaluator) {
                    continue;
                }
                if(player.getTeam() != null && player.getTeam().equals(evaluator.getTeam())) {
                    double distanceTeammate = grid.distanceBetweenPoints(node.getCoordinate(), player.getCoordinate());
                    if(distanceTeammate <= teammateRadius) {
                        double percentage = 1.0 - (distanceTeammate / teammateRadius);
                        teamScore -= percentage * 25;
                        teamScore -= 30;
                    }
                } else {
                    double distanceDefender = grid.distanceBetweenPoints(node.getCoordinate(), player.getCoordinate());
                    if(distanceDefender <= defenderRadius) {
                        double percentage = 1.0 - (distanceDefender / defenderRadius);
                        defenderScore -= percentage * 25;
                        defenderScore -= 30;
                    }
                }
            }
        }
        score += teamScore;
        score += defenderScore;

        parent.setColor(color);
        return score;
    }

    public Route mapPath(Player player, PathType type, Coordinate start, Coordinate end, int teammateRadius, int defenderRadius, Color color) {
        GridNode current = grid.getNode(start);
        GridNode endNode = grid.getNode(end);
        List<Coordinate> points = new ArrayList<>();
        Set<GridNode> visited = new HashSet<>();

        points.add(start);
        visited.add(current);
        Route route = new Route();

        while (current != endNode) {
            List<GridNode> neighbors = neighbors(current);
            Direction dir = getDirection(current.getCoordinate(), end);
            neighbors.removeIf(visited::contains);

            if (neighbors.isEmpty()) {
                return null;
            }

            GridNode finalCurrent = current;
            neighbors.sort(Comparator.comparingDouble(node -> (evaluateNode(player, dir, endNode, finalCurrent, (GridNode) node, teammateRadius, defenderRadius, color))).reversed());
            GridNode best = neighbors.getFirst();

            points.add(best.getCoordinate());
            visited.add(best);
            current = best;
        }
        grid.getNode(start).setColor(Color.RED);
        endNode.setColor(Color.MAGENTA);

        route.setPoints(points);
        route.setType(type);
        return route;
    }

        public OffensiveSpots getBestShot(Player player, ShotType type) {
        OffensiveSpots[] spots = new OffensiveSpots[0];
        Match match = player.getMatch();

        switch (type) {
            case CLOSE_RANGE,MID_RANGE -> {
                spots = new OffensiveSpots[]{OffensiveSpots.MID_RANGE};
            }
            case THREE_POINT -> {
                spots = new OffensiveSpots[]{
                        OffensiveSpots.THREE_POINT_LEFT,
                        OffensiveSpots.THREE_POINT_RIGHT,
                        OffensiveSpots.THREE_POINT_TOP_CORNER,
                        OffensiveSpots.THREE_POINT_BOTTOM_CORNER,
                        OffensiveSpots.THREE_POINT_TOP};
            }
            case FOUR_POINT -> {
                spots = new OffensiveSpots[]{
                        OffensiveSpots.FOUR_POINT_LEFT,
                        OffensiveSpots.FOUR_POINT_RIGHT,
                        OffensiveSpots.FOUR_POINT_TOP_CORNER,
                        OffensiveSpots.FOUR_POINT_BOTTOM_CORNER,
                        OffensiveSpots.FOUR_POINT_TOP,
                        OffensiveSpots.FOUR_POINT_DEEP};
            }
        }
        double bestScore = Double.NEGATIVE_INFINITY;
        OffensiveSpots best = null;
        for(OffensiveSpots spot : spots) {
            Coordinate pos;
            if(player.getTeam().equals(match.getHome())) {
                pos = spot.getHomePosition();
            } else {
                pos = spot.getAwayPosition();
            }
            double score;
            double distance = match.getGrid().distanceBetweenPoints(player.getCoordinate(), pos);
            double defenderScore = 0;
            double spacingScore = 0;
            for(GameObject obj : match.getGrid().getObjects()) {
                if(obj instanceof Player p) {
                    if(p == player) {
                        continue;
                    }
                    double dis = match.getGrid().distanceBetweenPoints(p.getCoordinate(), pos);
                    if(!p.getTeam().equals(player.getTeam())) {
                        if(dis <= 3) {
                            defenderScore -= 35;
                        } else if(dis <= 5) {
                            defenderScore -= 30;
                        } else if(dis <= 7) {
                            defenderScore -= 25;
                        } else if(dis <= 10) {
                            defenderScore -= 10;
                        }
                    } else {
                        if(dis <= 3) {
                            spacingScore -= 20;
                        } else if(dis <= 5) {
                            spacingScore -= 15;
                        } else if(dis <= 7) {
                            spacingScore -= 10;
                        } else if(dis <= 10) {
                            spacingScore -= 5;
                        }
                    }
                }
            }
            score = -distance * 1.25 + spacingScore + defenderScore;
            if(score > bestScore) {
                bestScore = score;
                best = spot;
            }

        }
        return best;
    }

    public OffensiveSpots getOffballSpot(Player player, OffBallDecision decision) {
        OffensiveSpots[] spots = new OffensiveSpots[0];
        Match match = player.getMatch();

        switch (decision) {
            case CUT -> {
                spots = new OffensiveSpots[]{OffensiveSpots.BASKET};
            }
            case SCREEN -> {
                spots = new OffensiveSpots[]{
                        OffensiveSpots.MID_RANGE};
            }
            case POST_UP -> {
                spots = new OffensiveSpots[]{
                        OffensiveSpots.POST_LEFT,
                        OffensiveSpots.POST_RIGHT};
            }
            case SPOT_UP -> {
                spots = new OffensiveSpots[]{
                        OffensiveSpots.FOUR_POINT_LEFT,
                        OffensiveSpots.FOUR_POINT_RIGHT,
                        OffensiveSpots.FOUR_POINT_TOP_CORNER,
                        OffensiveSpots.FOUR_POINT_BOTTOM_CORNER,
                        OffensiveSpots.FOUR_POINT_TOP,
                        OffensiveSpots.FOUR_POINT_DEEP,
                        OffensiveSpots.THREE_POINT_LEFT,
                        OffensiveSpots.THREE_POINT_RIGHT,
                        OffensiveSpots.THREE_POINT_TOP_CORNER,
                        OffensiveSpots.THREE_POINT_BOTTOM_CORNER,
                        OffensiveSpots.THREE_POINT_TOP};
            }
        }
        double bestScore = Double.NEGATIVE_INFINITY;
        OffensiveSpots best = null;
        for(OffensiveSpots spot : spots) {
            Coordinate pos;
            if(player.getTeam().equals(match.getHome())) {
                pos = spot.getHomePosition();
            } else {
                pos = spot.getAwayPosition();
            }
            double score;
            double distance = match.getGrid().distanceBetweenPoints(player.getCoordinate(), pos);
            double defenderScore = 0;
            double spacingScore = 0;
            for(GameObject obj : match.getGrid().getObjects()) {
                if(obj instanceof Player p) {
                    if(p == player) {
                        continue;
                    }
                    double dis = match.getGrid().distanceBetweenPoints(p.getCoordinate(), pos);
                    if(!p.getTeam().equals(player.getTeam())) {
                        if(dis <= 3) {
                            defenderScore -= 35;
                        } else if(dis <= 5) {
                            defenderScore -= 30;
                        } else if(dis <= 7) {
                            defenderScore -= 25;
                        } else if(dis <= 10) {
                            defenderScore -= 10;
                        }
                    } else {
                        if(dis <= 3) {
                            spacingScore -= 20;
                        } else if(dis <= 5) {
                            spacingScore -= 15;
                        } else if(dis <= 7) {
                            spacingScore -= 10;
                        } else if(dis <= 10) {
                            spacingScore -= 5;
                        }
                    }
                }
            }
            score = -distance * 1.25 + spacingScore + defenderScore;
            if(score > bestScore) {
                bestScore = score;
                best = spot;
            }

        }
        return best;
    }

    public ShotType getShotType(Player player) {
        Stats stats = player.getStats();

        double closeRangeChance = stats.getFinishing();
        double midRangeChance = stats.getMidrange();
        double threePointChance = stats.getThreePoint();
        double fourPointChance = stats.getFourPoint();

        Match match = player.getMatch();
        ScoreSituation scoring = match.getScoreSituation(player);
        switch (scoring) {
            case CLOSE_TRAILING -> {
                threePointChance *= 1.1;
                fourPointChance *= 1.05;
            }

            case TRAILING -> {
                threePointChance *= 1.25;
                fourPointChance *= 1.20;
            }

            case BLOWOUT_TRAILING -> {
                threePointChance *= 1.35;
                fourPointChance *= 1.5;
            }

            case CLOSE_LEADING -> {
                closeRangeChance *= 1.05;
                midRangeChance *= 1.05;
            }

            case LEADING -> {
                closeRangeChance *= 1.15;
                midRangeChance *= 1.10;
                threePointChance *= 0.95;
                fourPointChance *= 0.90;
            }

            case BLOWOUT_LEADING -> {
                closeRangeChance *= 1.20;
                midRangeChance *= 1.15;
                threePointChance *= 0.85;
                fourPointChance *= 0.75;
            }
        }

        double total = closeRangeChance
                + midRangeChance
                + threePointChance
                + fourPointChance;

        double roll = Util.RANDOM.nextDouble() * total;

        if (roll < closeRangeChance) {
            return ShotType.CLOSE_RANGE;
        }

        roll -= closeRangeChance;

        if (roll < midRangeChance) {
            return ShotType.MID_RANGE;
        }

        roll -= midRangeChance;

        if (roll < threePointChance) {
            return ShotType.THREE_POINT;
        }

        return ShotType.FOUR_POINT;
    }

    public DriveType getDriveDestination(Player player) {
        Stats stats = player.getStats();

        double closeRangeChance = stats.getFinishing();
        Match match = player.getMatch();
        ScoreSituation scoring = match.getScoreSituation(player);

        return DriveType.LAYUP;
    }

}

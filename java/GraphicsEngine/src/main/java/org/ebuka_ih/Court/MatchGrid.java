package org.ebuka_ih.Court;

import org.ebuka_ih.Game.Ball;
import org.ebuka_ih.Game.GameObject;
import org.ebuka_ih.Game.Match;
import org.ebuka_ih.Player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MatchGrid {

    private final List<GameObject> objects;
    private GridNode[][] nodes;
    private final int x;
    private final int y;
    private final Match match;
    private final Coordinate HOME_BASKET;
    private final Coordinate AWAY_BASKET;

    public MatchGrid(Match match, int xSize, int ySize) {
        this.match = match;
        this.x = xSize;
        this.y = ySize;
        this.objects = new ArrayList<>();

        this.HOME_BASKET = new Coordinate(0, 37);
        this.AWAY_BASKET = new Coordinate(150, 37);

        createNodes();
    }
    private double calculateDistance(GridNode obj1, GridNode obj2) {
        Coordinate pos1 = obj1.getCoordinate();
        Coordinate pos2 = obj2.getCoordinate();
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }
    private double calculateDistance(Coordinate pos1, Coordinate pos2) {
        double dx = pos2.getX() - pos1.getX();
        double dy = pos2.getY() - pos1.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }
    public Coordinate locateObject(GameObject object) {
        for(GameObject o : getObjects()) {
            if(o.equals(object)) {
                return o.getCoordinate();
            }
        }
        return null;
    }
    public double distanceFromBasket(Player player) {
        if(match.isHome(player)) {
            return calculateDistance(player.getCoordinate(), HOME_BASKET);
        } else {
            return calculateDistance(player.getCoordinate(), AWAY_BASKET);
        }
    }
    public Match getMatch() {
        return this.match;
    }
    public double distanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return calculateDistance(obj1.getCoordinate(), obj2.getCoordinate());
    }
    public double distanceBetweenPoints(Coordinate pos1, Coordinate pos2) {
        return calculateDistance(pos1, pos2);
    }
    public List<Player> getNearbyPlayers(GameObject obj, double radius) {
        ArrayList<Player> a = new ArrayList<>();
        for (GameObject o : objects) {
            if (o instanceof Player p) {
                if(p.getCoordinate() != null) {
                    if (distanceBetweenObjects(obj, p) <= radius) {
                        a.add(p);
                    }
                }
            }
        }
        return a;
    }
    public Player getClosestTeammate(Player player) {
        return null;
    }
    public Player getClosestDefender(Player player) {
        return null;
    }
    public void addObject(GameObject object) {
        objects.add(object);
    }
    public void removeObject(GameObject object) {
        objects.remove(object);
    }
    public GameObject getObject(GridNode node) {
        for(GameObject obj : getObjects()) {
            if(locateObject(obj).equals(node.getCoordinate())) {
                return obj;
            }
        }
        return null;
    }
    public List<GameObject> getObjects() {
        return objects;
    }
    public boolean hasObject(GridNode node) {
        for(GameObject obj : getObjects()) {
            Coordinate coordinate = locateObject(obj);
            if(coordinate != null && coordinate.equals(node.getCoordinate()) && !(obj instanceof Ball)) {
                return true;
            }
        }
        return false;
    }
    public GridNode getNode(Coordinate coordinate) {
        int x = Math.clamp(coordinate.getX(), 0, this.x);
        int y = Math.clamp(coordinate.getY(), 0, this.y);
        return nodes[x][y];
    }
    public void createNodes() {
        nodes = new GridNode[x + 1][y + 1];

        for (int x = 0; x <= this.x; x++) {
            for (int y = 0; y <= this.y; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                nodes[x][y] = new GridNode(coordinate);
            }
        }
    }

    public GridNode[][] getNodes() {
        return nodes;
    }
    public void setColor(GridNode node, Color color) {
        node.setColor(color);
    }
}
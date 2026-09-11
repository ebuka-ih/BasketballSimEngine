package org.ebuka_ih.Court;

import java.awt.*;

public class GridNode {

    private final Coordinate coordinate;

    private double arrCost;
    private double depCost;

    private Color color;
    private GridNode parent;

    public GridNode(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
    public void setDepartureCost(double cost) {
        this.depCost = cost;
    }
    public void setArrivalCost(double cost) {
        this.arrCost = cost;
    }
    public double getArrivalCost() {
        return this.arrCost;
    }
    public double getDepartureCost() {
        return this.depCost;
    }
    public double getFinalCost() {
        return arrCost + depCost;
    }
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
    public void setParent(GridNode parent) {
        this.parent = parent;
    }
    public GridNode getParent() {
        return this.parent;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
}

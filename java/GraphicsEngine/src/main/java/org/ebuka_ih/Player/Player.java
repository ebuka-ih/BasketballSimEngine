package org.ebuka_ih.Player;

import org.ebuka_ih.Contract;
import org.ebuka_ih.Game.GameObject;
import org.ebuka_ih.Generation.StatFactory;
import org.ebuka_ih.Movement.Route;
import org.ebuka_ih.SeasonStats;
import org.ebuka_ih.Team;
import org.ebuka_ih.Util.Util;
import org.ebuka_ih.en.PathType;
import org.ebuka_ih.en.Personality;
import org.ebuka_ih.en.Position;

import java.util.Map;

public class Player extends GameObject {


    private int experience;
    private int potential;
    private int talent;

    private int age;
    private int health;
    private int developmentHealth;
    private Route route;

    private PathType movementGoal;
    private Position position;
    private Contract contract;
    private Team team;

    private Personality personality;

    private Stats baseStats;
    private Stats stats;
    private Map<Integer, SeasonStats> seasonStats;


    public Player() {

    }
    public Player(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return this.position;
    }
    public void setMovementGoal(PathType movementGoal) {
        this.movementGoal = movementGoal;
    }
    public PathType getMovementGoal() {
        return movementGoal;
    }

    public Team getTeam() {
        return this.team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public Stats getStats() {
        return this.stats;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    public Route getRoute() {
        return this.route;
    }




    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private int potential;
        private int talent;
        private Stats base;
        private Stats stats;
        private Position position;
        private Personality personality;
        private Team team;

        public Builder position(Position position) {
            this.position = position;
            return this;
        }

        public Builder potential(int potential) {
            this.potential = potential;
            return this;
        }

        public Builder talent(int talent) {
            this.talent = talent;
            return this;
        }
        public Builder baseStats(Stats stats) {
            this.base = stats;
            return this;
        }
        public Builder stats(Stats stats) {
            this.stats = stats;
            return this;
        }
        public Builder personality(Personality personality) {
            this.personality = personality;
            return this;
        }
        public Builder team(Team team) {
            this.team = team;
            return this;
        }
        public Builder generatePosition() {
            position = Position.values()[Util.RANDOM.nextInt(Position.values().length)];
            return this;
        }
        public Builder generateStats() {
            Stats s = StatFactory.build(position);
            this.base = s;
            this.stats = s;
            return this;
        }

        public Player build() {
            Player player = new Player();

            player.potential = potential;
            player.talent = talent;
            player.position = position;
            player.baseStats = base;
            player.stats = stats;
            player.personality = personality;
            team.addPlayer(player);

            return player;
        }
    }
}
package org.ebuka_ih.Player;

import org.ebuka_ih.Game.Match;

public class PlayerAction {

    public static void shoot(Player player) {
        Match match = player.getMatch();
        double distance = match.getGrid().distanceFromBasket(player);
        Stats stats = player.getStats();

        double chance;
        double pressure = match.getDefensivePressure(player) + match.getDistancePressure(player);
        double pressurePenalty = pressure / 3.25;

        if (distance < 30) {
            chance = stats.getMidrange();
        } else if (distance < 35) {
            chance = stats.getThreePoint();
        } else {
            chance = stats.getFourPoint();
        }

        chance -= pressurePenalty;
        chance = Math.clamp(chance, 2, 100) / 100;

        double roll = Math.random();
        System.out.println("DISTANCE: " + Math.floor(distance) + " | PRESSURE STATS: " + pressure + " | PRESSURE PENALTY: " + pressurePenalty + " | CHANCE: " + chance + " | ROLL: " + roll + "(" + (roll <= chance) + ")");
        if(roll <= chance) {
            // MADE SHOT
        }

    }
    public static void pass(Player player) {
        Match match = player.getMatch();
        Player target = match.getBestPassTarget(player);
        match.getBall().setHolder(target);
    }
    public static void drive(Player player) {

    }
}

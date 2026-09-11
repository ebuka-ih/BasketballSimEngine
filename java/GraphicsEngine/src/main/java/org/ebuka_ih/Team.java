package org.ebuka_ih;

import org.ebuka_ih.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {


    private List<Player> players;

    public Team() {
        this.players = new ArrayList<>();
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
        player.setTeam(this);
    }
}
package org.ebuka_ih;

import org.ebuka_ih.Player.Player;
import org.ebuka_ih.en.ContractOption;
import org.ebuka_ih.en.ContractType;


public class Contract {

    private final int id;
    private final Player player;
    private Team team;
    private final double totalMoney;
    private final int duration;
    private final int startYear;
    private boolean active;
    private final ContractType type;
    private final ContractOption option;
    public Contract(int id, Player player, Team team, int start, int duration, double totalMoney, ContractType type, ContractOption option, boolean active) {
        this.id = id;
        this.player = player;
        this.team = team;
        this.startYear = start;
        this.duration = duration;
        this.totalMoney = totalMoney;
        this.type = type;
        this.option = option;
        this.active = active;
    }
    public int getId() {
        return this.id;
    }
    public int getDuration() {
        return duration;
    }
    public int getStartYear() {
        return this.startYear;
    }
    public int getEndYear() {
        return this.startYear + this.duration;
    }
    public double getTotalMoney() {
        return this.totalMoney;
    }
    public double getYearlyCost() {
        return (double) this.totalMoney / this.duration;
    }
    public Player getPlayer() {
        return this.player;
    }
    public Team getTeam() {
        return this.team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    public ContractType getType() {
        return this.type;
    }
    public ContractOption getOption() {
        return this.option;
    }
    public boolean isActive() {
        return this.active;
    }
    public void terminate() {

        this.active = false;
    }
}

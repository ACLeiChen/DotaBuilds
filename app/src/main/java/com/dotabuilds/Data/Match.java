package com.dotabuilds.Data;

import java.util.List;

/**
 * Created by Lei Chen on 2017/10/25.
 */

public class Match {

    private String matchId;
    private boolean isWon;
    private int kill;
    private int death;
    private int assist;
    private int gpm;
    private Hero myHero;

    List<Item> items;
    List<Item> backPacks;

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean isWon) {
        this.isWon = isWon;
    }

    public Hero getMyHero() {
        return myHero;
    }

    public void setMyHero(Hero myHero) {
        this.myHero = myHero;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getGpm() {
        return gpm;
    }

    public void setGpm(int gpm) {
        this.gpm = gpm;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getBackPacks() {
        return backPacks;
    }

    public void setBackPacks(List<Item> backPacks) {
        this.backPacks = backPacks;
    }

    public String getKDA() {
        return kill + "/" + death + "/" + assist;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}

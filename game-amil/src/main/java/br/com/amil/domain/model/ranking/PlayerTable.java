package br.com.amil.domain.model.ranking;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;

public class PlayerTable {
    Player player;
    int deathAll;
    int murdersAll;
    boolean isWinner;
    private int mostMurderSeqWithoutDying;
    private boolean award;

    public GunType getPreferredGun() {
        return preferredGun;
    }

    public void setPreferredGun(GunType preferredGun) {
        this.preferredGun = preferredGun;
    }

    private GunType preferredGun;
    private PlayerTable(Player player,
                        int deathAll,
                        int murdersAll) {
        this.player = player;
        this.deathAll = deathAll;
        this.murdersAll = murdersAll;
    }

    public boolean isWinner() {
        return this.isWinner;
    }
    public boolean isAward() {
        return award;
    }

    public void setAward(boolean award) {
        this.award = award;
    }

    public int getMostMurderSeqWithoutDying() {
        return this.mostMurderSeqWithoutDying;
    }

    public void setMostMurderSeqWithoutDying(int mostMurderSeqWithoutDying) {
        this.mostMurderSeqWithoutDying = mostMurderSeqWithoutDying;
    }

    public void asWinner(boolean asWinner) {
        this.isWinner = asWinner;
    }

    public boolean asWinner() {
        return isWinner;
    }

    public PlayerTable() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getDeathAll() {
        return deathAll;
    }

    public void setDeathAll(int deathAll) {
        this.deathAll = deathAll;
    }

    public int getMurdersAll() {
        return murdersAll;
    }

    public void setMurdersAll(int murdersAll) {
        this.murdersAll = murdersAll;
    }

    public static PlayerTable create(Player player, int deathAll, int murdersAll) {
        return new PlayerTable(player, deathAll, murdersAll);
    }

    public static PlayerTable create() {
        return new PlayerTable();
    }
}

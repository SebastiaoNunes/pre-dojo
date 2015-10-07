package br.com.amil.domain.model.ranking.builder;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;

public class PlayerTableBuilder {

    PlayerTable playerTable;


    private PlayerTableBuilder() {
        playerTable = new PlayerTable();
    }

    public static PlayerTableBuilder createTable() {
        return new PlayerTableBuilder();
    }

    public PlayerTableBuilder withPlayer(Player player) {
        playerTable.setPlayer(player);
        return this;
    }

    public PlayerTableBuilder withDeaths(int deaths) {
        playerTable.setDeathAll(deaths);
        return this;
    }

    public PlayerTableBuilder withMulder(int mulder) {
        playerTable.setMurdersAll(mulder);
        return this;
    }

    public PlayerTableBuilder asWinner() {
        playerTable.getPlayer().asWinner(true);
        playerTable.asWinner(true);
        return this;
    }

    public PlayerTableBuilder withPreferredGun(GunType gunType) {
        playerTable.setPreferredGun(gunType);
        return this;
    }

    public PlayerTableBuilder withMostMurderSequenceWithoutDying(int sequence) {
        playerTable.setMostMurderSeqWithoutDying(sequence);
        return this;
    }

    public PlayerTableBuilder withAward(boolean award) {
        playerTable.setAward(award);
        return this;
    }

    public PlayerTable create() {
        return this.playerTable;
    }
}

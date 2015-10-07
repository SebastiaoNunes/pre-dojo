package br.com.amil.domain.model.ranking;

import br.com.amil.domain.model.game.Action;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.service.RankingService;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ranking {

    private GameTime gameTime;
    private List<PlayerTable> playerTables;

    public Ranking(List<PlayerTable> playerTables, GameTime gameTime) {
        this.playerTables = playerTables;
        this.gameTime = gameTime;
    }

    public int deathsForPlayer(Player player) {
        for (PlayerTable playerTable : playerTables) {
            Player playerFromTable = playerTable.getPlayer();

            if (playerFromTable.getUUID().equals(player.getUUID())) {
                return playerTable.getDeathAll();
            }
        }
        return 0;
    }

    public int murdersForPlayer(Player player) {
        for (PlayerTable playerTable : playerTables) {
            Player playerFromTable = playerTable.getPlayer();

            if (playerFromTable.getUUID().equals(player.getUUID())) {
                return playerTable.getMurdersAll();
            }
        }
        return 0;
    }

    public GunType preferredGun() {
        for (PlayerTable playerTable : playerTables) {
            if (playerTable.asWinner()) {
                return playerTable.getPreferredGun();
            }
        }
        return null;
    }

    public PlayerTable gimmeWinner(){
        for (PlayerTable playerTable : playerTables) {
            if (playerTable.isWinner()) {
                return playerTable;
            }
        }
        return null;
    }

    public int mostMurderSequenceWithoutDying() {
        List<Integer> seqMurders = new LinkedList<Integer>();

        for (PlayerTable playerTable : playerTables) {
            seqMurders.add(playerTable.getMostMurderSeqWithoutDying());
        }

        Collections.sort(seqMurders);
        return seqMurders.get(seqMurders.size() - 1);
    }

    public static Ranking create(List<PlayerTable> playerTables, GameTime gameTime) {
        return new Ranking(playerTables, gameTime);
    }
}

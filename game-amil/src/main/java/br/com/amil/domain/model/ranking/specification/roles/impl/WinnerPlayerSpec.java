package br.com.amil.domain.model.ranking.specification.roles.impl;


import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.sort.SortPlayerByMurderer;
import br.com.amil.domain.model.ranking.sort.SortType;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

import java.util.Collections;
import java.util.List;

public class WinnerPlayerSpec implements RoleSpec {

    private Player current;
    private List<Player> players;

    public WinnerPlayerSpec(Player current, List<Player> players) {
        this.current = current;
        this.players = players;
    }

    @Override
    public boolean isSatisfied() {
        SortPlayerByMurderer sortPlayer = SortPlayerByMurderer.create(SortType.DESC);
        Collections.sort(players, sortPlayer);

        // We Don't have two winners
        Player winnerPlayer = players.get(0);
        return current.getUUID().equals(winnerPlayer.getUUID());
    }
}

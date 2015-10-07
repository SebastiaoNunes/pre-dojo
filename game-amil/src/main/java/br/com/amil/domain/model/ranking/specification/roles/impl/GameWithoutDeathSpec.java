package br.com.amil.domain.model.ranking.specification.roles.impl;

import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

public class GameWithoutDeathSpec implements RoleSpec {

    private Player player;

    public GameWithoutDeathSpec(Player player) {
        this.player = player;
    }

    @Override
    public boolean isSatisfied() {
        if (player == null) {
            return false;
        }

        History history = player.getHistory();
        return (history.getDeaths() == 0);
    }
}

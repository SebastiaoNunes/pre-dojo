package br.com.amil.domain.model.ranking.specification.roles.impl;

import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

import java.util.List;

public class DefaultSpec implements RoleSpec {

    Player player;
    List<Player> players;

    public DefaultSpec(Player player, List<Player> players) {
        this.player = player;
        this.players = players;
    }

    @Override
    public boolean isSatisfied() {
        RoleSpec winnerPlayerSpec = new WinnerPlayerSpec(player, players);
        return (!winnerPlayerSpec.isSatisfied());
    }
}

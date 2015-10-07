package br.com.amil.domain.model.ranking.specification.creation.impl;

import br.com.amil.domain.model.gun.GunKit;
import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.specification.creation.CreatorSpec;
import br.com.amil.domain.model.ranking.specification.roles.shared.Roles;

import static br.com.amil.domain.model.ranking.builder.PlayerTableBuilder.createTable;

public class CreatorSpecImpl implements CreatorSpec {

    private Player player;
    private Roles role;

    public CreatorSpecImpl(Player player, Roles role) {
        this.player = player;
        this.role = role;
    }

    @Override
    public PlayerTable create() {
        History history = player.getHistory();
        GunKit kit = player.kit();

         if (role.toString().equals(Roles.WINNER_PLAYER_AWARD.toString())) {
            return createTable()
                    .withPlayer(player)
                    .withDeaths(history.getDeaths())
                    .withMulder(history.getMurders())
                    .withMostMurderSequenceWithoutDying(history.mostMurderSequenceWithoutDying())
                    .withAward(true)
                    .asWinner()
                    .withPreferredGun(kit.preferredGun().type())
                    .create();
        } else if (role.toString().equals(Roles.WINNER_PLAYER.toString())) {
            return createTable()
                    .withPlayer(player)
                    .withDeaths(history.getDeaths())
                    .withMulder(history.getMurders())
                    .withMostMurderSequenceWithoutDying(history.mostMurderSequenceWithoutDying())
                    .asWinner()
                    .withAward(false)
                    .withPreferredGun(kit.preferredGun().type())
                    .create();
        } else {
            return createTable()
                    .withPlayer(player)
                    .withDeaths(history.getDeaths())
                    .withMulder(history.getMurders())
                    .withMostMurderSequenceWithoutDying(history.mostMurderSequenceWithoutDying())
                    .create();
        }
    }
}

package br.com.amil.domain.model.ranking.service;

import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.specification.roles.shared.Roles;
import br.com.amil.domain.model.ranking.specification.roles.impl.DefaultSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.GameWithoutDeathSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.MurdersByWorldSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.WinnerPlayerSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.AndSpec;


import static br.com.amil.domain.model.ranking.specification.roles.shared.RoleEngine.toTablePlayer;
import static br.com.amil.domain.model.ranking.specification.creation.CreatorSpecMatcher.*;

import java.util.ArrayList;
import java.util.List;

public class RankingService {

    private List<Player> players;
    private RankingService(List<Player> players) {
        this.players = players;
    }

    public static RankingService create(List<Player> players) {
        return new RankingService(players);
    }

    public List<PlayerTable> summarize() {
        List<PlayerTable> playerTables = new ArrayList<PlayerTable>();

        for (Player player : players) {
            PlayerTable playerTab = toTablePlayer("Creating Player Table")
                          .caseForValidRole(
                                  new AndSpec(new WinnerPlayerSpec(player, players),
                                  new GameWithoutDeathSpec(player)),
                                  aPlayerTable(player, Roles.WINNER_PLAYER_AWARD))
                          .caseForValidRole(new WinnerPlayerSpec(player, players),
                                  aPlayerTable(player, Roles.WINNER_PLAYER))
                          .caseForValidRole(new DefaultSpec(player, players),
                                  aPlayerTable(player, Roles.DEFAULT))
                                  .create();

            playerTables.add(playerTab);
        }
        return playerTables;
    }
}

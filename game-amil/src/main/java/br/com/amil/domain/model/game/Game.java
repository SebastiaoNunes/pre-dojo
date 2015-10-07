package br.com.amil.domain.model.game;

import br.com.amil.domain.model.ranking.GameTime;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.PlayerTable;
import br.com.amil.domain.model.ranking.Ranking;
import br.com.amil.domain.model.ranking.service.RankingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Game {

    private UUID id;
    private Date startTime;
    private Date endTime;
    private GameSpec gameSpec;

    private Game(UUID id, GameSpec gameSpec) {
        this.id = id;
        this.startTime = startTime;
        this.gameSpec = gameSpec;
    }

    public static Game openNewGame(GameSpec gameSpec) {
        return new Game(UUID.randomUUID(), gameSpec);
    }

    public Ranking start() {
        startTime = currentTime();

        List<Player> players = givePlayerList(gameSpec);

        RankingService rankingService = RankingService.create(players);
        List<PlayerTable> playerTables = rankingService.summarize();

        endTime = currentTime();
        GameTime gameTime = GameTime.create(startTime, endTime);

        return Ranking.create(playerTables, gameTime);
    }

    private List<Player> givePlayerList(GameSpec gameSpec) {
        List<Player> players = new ArrayList<Player>();

        for (Scenario scenario :  gameSpec.getScenarios()) {
            if (!players.contains(scenario.getHunter())) {
                players.add(scenario.getHunter());
            }

            if (!players.contains(scenario.getHunt())) {
                players.add(scenario.getHunt());
            }
        }
        return players;
    }

    private boolean contains(Player player, List<Player> players) {
        for (Player pl : players) {
            return players.contains(pl);
        }

        return false;
    }

    private Date currentTime() {
        return new Date();
    }
}

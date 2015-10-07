package br.com.amil.application;

import br.com.amil.domain.model.game.Game;
import br.com.amil.domain.model.game.GameSpec;
import br.com.amil.domain.model.ranking.Ranking;

import java.io.File;

public class GameService {

    private GameSpec gameSpec;

    private GameService(GameSpec gameSpec) {
        this.gameSpec = gameSpec;
    }

    public static GameService create(GameSpec gameSpec) {
        return new GameService(gameSpec);
    }

    public Ranking start() {
        Game game = Game.openNewGame(gameSpec);
        return game.start();
    }
}

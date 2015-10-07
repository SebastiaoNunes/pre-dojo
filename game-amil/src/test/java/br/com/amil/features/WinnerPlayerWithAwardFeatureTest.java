package br.com.amil.features;

import br.com.amil.application.GameService;
import br.com.amil.domain.model.game.Game;
import br.com.amil.domain.model.game.GameSpec;
import br.com.amil.domain.model.game.Scenario;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.Ranking;
import org.junit.Test;

import static br.com.amil.assertions.RankingAssert.ensureThat;
import static br.com.amil.domain.model.game.Scenario.scenario;
import static br.com.amil.domain.model.game.ScenarioMatcher.using;

public class WinnerPlayerWithAwardFeatureTest {

    @Test
    public void theWinnerShouldHaveAward() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick);

        GameService gameService = GameService.create(gameSpec);
        Ranking ranking = gameService.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(1)
                    .shouldHaveDeath(0)
                    .shouldBeWinner()
                    .shouldHaveAward(true)
                    .shouldHavePreferredGun(GunType.KNIFE)
                    .shouldHaveMostMurderSequenceWithoutDying(0);
    }

}

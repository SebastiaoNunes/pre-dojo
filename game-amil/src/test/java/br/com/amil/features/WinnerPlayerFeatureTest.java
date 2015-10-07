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

public class WinnerPlayerFeatureTest {

    @Test
    public void shouldShowWinnerPlayer() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");
        Player john = Player.newPlayer("John");
        Player ben = Player.newPlayer("Ben");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario romanKilledJohn = scenario("Roman killed John")
                .player(roman)
                .killed(john, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario romanKilledBen = scenario("Roman killed Ben")
                .player(roman)
                .killed(ben, using(Gun.newGun("MAGNUM", GunType.MAGNUM)));

        Scenario benKilledNick = scenario("Ben killed Nick")
                .player(ben)
                .killed(nick, using(Gun.newGun("M16", GunType.M16)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick,
                romanKilledJohn,
                romanKilledBen,
                benKilledNick);

        GameService gameService = GameService.create(gameSpec);
        Ranking ranking = gameService.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(3)
                    .shouldHaveDeath(0)
                    .shouldBeWinner();
    }

    @Test
    public void shouldShowWinnerPlayerWithOnlyOneMurder() {
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
                    .shouldBeWinner();
    }
}

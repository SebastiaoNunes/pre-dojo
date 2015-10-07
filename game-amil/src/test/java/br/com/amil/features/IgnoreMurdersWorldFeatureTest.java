package br.com.amil.features;

import br.com.amil.application.GameService;
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

public class IgnoreMurdersWorldFeatureTest {

    @Test
    public void shouldIgnoreMurdersByWorld() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");
        Player world = Player.newPlayer("world");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("M16", GunType.M16)));

        Scenario worldKilledNick = scenario("World killed Nick")
                .player(world)
                .killed(nick, using(Gun.newGun("DROWN", GunType.DROWN)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick, worldKilledNick);

        GameService gameService = GameService.create(gameSpec);
        Ranking ranking = gameService.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(1)
                    .shouldHaveDeath(0)
                .forPlayer(nick)
                    .shouldHaveDeath(2)
                    .shouldHaveMurders(0)
                .forPlayer(world)
                    .shouldHaveMurders(0)
                    .shouldHaveDeath(0);
    }
}

package br.com.amil;

import br.com.amil.domain.model.game.Game;
import br.com.amil.domain.model.game.GameSpec;
import br.com.amil.domain.model.game.ScenarioMatcher;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.game.Scenario;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.Ranking;
import org.junit.Test;

import static br.com.amil.assertions.RankingAssert.ensureThat;
import static br.com.amil.domain.model.game.Scenario.*;
import static br.com.amil.domain.model.game.ScenarioMatcher.using;
import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void shouldShowTotalDeathsAndMurders() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                                       .player(roman)
                                       .killed(nick, using(Gun.newGun("M16", GunType.M16)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick);

        Game game = Game.openNewGame(gameSpec);

        Ranking ranking = game.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(1)
                    .shouldHaveDeath(0)
                .forPlayer(nick)
                    .shouldHaveDeath(1)
                    .shouldHaveMurders(0);
    }

    @Test
    public void shouldDisregardDeathsByWorld() {
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

        Game game = Game.openNewGame(gameSpec);
        Ranking ranking = game.start();

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

    @Test
    public void shouldShowPreferredGun() {
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

        Game game = Game.openNewGame(gameSpec);
        Ranking ranking = game.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(3)
                    .shouldHaveDeath(0)
                    .shouldBeWinner()
                    .shouldHavePreferredGun(GunType.KNIFE)
                .forPlayer(ben)
                    .shouldHaveMurders(1)
                    .shouldHaveDeath(1)
                .forPlayer(john)
                    .shouldHaveMurders(0)
                    .shouldHaveDeath(1);
    }

    @Test
    public void shouldShowMostMurderSequenceWithoutDying() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");
        Player john = Player.newPlayer("John");
        Player ben = Player.newPlayer("Ben");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario benKilledRoman = scenario("Roman killed Ben")
                .player(ben)
                .killed(roman, using(Gun.newGun("MAGNUM", GunType.MAGNUM)));

        Scenario romanKilledJohn = scenario("Roman killed John")
                .player(roman)
                .killed(john, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario romanKilledBen = scenario("Roman killed Ben")
                .player(roman)
                .killed(ben, using(Gun.newGun("MAGNUM", GunType.KNIFE)));

        Scenario benKilledNick = scenario("Ben killed Nick")
                .player(ben)
                .killed(nick, using(Gun.newGun("M16", GunType.M16)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick,
                romanKilledJohn,
                romanKilledBen,
                benKilledNick);

        Game game = Game.openNewGame(gameSpec);
        Ranking ranking = game.start();

        ensureThat(ranking)
                .forPlayer(roman)
                    .shouldHaveMurders(3)
                    .shouldHaveDeath(1)
                    .shouldBeWinner()
                    .shouldHavePreferredGun(GunType.KNIFE)
                    .shouldHaveMostMurderSequenceWithoutDying(2);
    }

    @Test
    public void theWinnerShouldHaveAward() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick);

        Game game = Game.openNewGame(gameSpec);
        Ranking ranking = game.start();

        ensureThat(ranking)
                .forPlayer(roman)
                .shouldHaveMurders(1)
                .shouldHaveDeath(0)
                .shouldBeWinner()
                .shouldHaveAward(true)
                .shouldHavePreferredGun(GunType.KNIFE)
                .shouldHaveMostMurderSequenceWithoutDying(0);
    }

    @Test
    public void theWinnerShouldNotHaveAward() {
        Player roman = Player.newPlayer("Roman");
        Player nick = Player.newPlayer("Nick");
        Player john = Player.newPlayer("John");

        Scenario romanKilledNick = scenario("Roman killed Nick")
                .player(roman)
                .killed(nick, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario romanKilledJohn = scenario("Roman killed John")
                .player(roman)
                .killed(john, using(Gun.newGun("KNIFE", GunType.KNIFE)));

        Scenario nickKilledRoman = scenario("Nick killed Roman")
                .player(nick)
                .killed(roman, using(Gun.newGun("KNIFE", GunType.KNIFE)));


        GameSpec gameSpec = GameSpec.create();
        gameSpec.addScenario(romanKilledNick);

        Game game = Game.openNewGame(gameSpec);
        Ranking ranking = game.start();

        ensureThat(ranking)
                .forPlayer(roman)
                .shouldHaveMurders(2)
                .shouldHaveDeath(1)
                .shouldBeWinner()
                .shouldHaveAward(false)
                .shouldHavePreferredGun(GunType.KNIFE)
                .shouldHaveMostMurderSequenceWithoutDying(2);
    }
}

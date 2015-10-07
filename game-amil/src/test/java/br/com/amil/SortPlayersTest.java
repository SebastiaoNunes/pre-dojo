package br.com.amil;

import br.com.amil.domain.model.game.Action;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.sort.SortPlayerByMurderer;
import br.com.amil.domain.model.ranking.sort.SortType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class SortPlayersTest {

    @Test
    public void shouldSortPlayersByAsc() {
        Player roman = Player.newPlayer("Roman");
        roman.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.AK47));

        Player nick = Player.newPlayer("Nick");
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.AK47));
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.AK47));
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.AK47));

        Player rock = Player.newPlayer("Rock");
        rock.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.DROWN));

        List<Player> players = Arrays.asList(roman, nick, rock);

        SortPlayerByMurderer sortPlayer = SortPlayerByMurderer.create(SortType.ASC);
        Collections.sort(players, sortPlayer);

        assertThat(players, containsInAnyOrder(rock, roman, nick));
    }

    @Test
    public void shouldSortPlayersByDesc() {
        Player roman = Player.newPlayer("Roman");
        roman.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.MAGNUM));

        Player nick = Player.newPlayer("Nick");
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.MAGNUM));
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.KNIFE));
        nick.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.M16));

        Player rock = Player.newPlayer("Rock");
        rock.addEventToHistory(Action.MURDERED, Gun.newGun("any gun", GunType.AK47));

        List<Player> players = Arrays.asList(roman, nick, rock);

        SortPlayerByMurderer sortPlayer = SortPlayerByMurderer.create(SortType.DESC);
        Collections.sort(players, sortPlayer);

        assertThat(players, containsInAnyOrder(nick, roman, rock));
    }
}

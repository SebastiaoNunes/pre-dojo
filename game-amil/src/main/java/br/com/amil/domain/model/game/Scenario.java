package br.com.amil.domain.model.game;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;

public class Scenario {
    private Player hunter;
    private Player hunt;

    public static Scenario scenario(String description) {
        return new Scenario();
    }

    public Scenario player(Player player) {
        this.hunter = player;
        return this;
    }

    public Scenario killed(Player hunt, Gun gun) {
        this.hunt = hunt;
        this.hunt.addEventToHistory(Action.KILLED, gun);

        this.hunter.addEventToHistory(Action.MURDERED, gun);
        return this;
    }

    public Player getHunter() {
        return this.hunter;
    }

    public Player getHunt() {
        return this.hunt;
    }
}



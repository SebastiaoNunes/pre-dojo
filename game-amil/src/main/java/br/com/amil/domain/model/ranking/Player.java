package br.com.amil.domain.model.ranking;

import br.com.amil.domain.model.game.Action;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunKit;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.MurdersByWorldSpec;

import java.util.UUID;

public class Player {
    private UUID uuid;
    private String name;
    private History history;
    private GunKit kit;
    private boolean isWinner;

    public GunKit kit() {
        return this.kit;
    }
    public boolean isWinner() {
        return isWinner;
    }

    public void asWinner(boolean asWinner) {
        this.isWinner = asWinner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    private Player(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public static Player newPlayer(String name) {
        return new Player(name);
    }

    public void addEventToHistory(Action action, Gun gun) {
        if (this.history == null) {
            this.history = History.create();
        }
        this.history.addAction(action, gun);

        updateGunKit(gun);
    }

    private void updateGunKit(Gun gun) {
        if (kit == null) {
            kit = GunKit.create();
        }

        RoleSpec spec = new MurdersByWorldSpec(gun);

        if (!spec.isSatisfied()) {
            kit.using(gun);
        }
    }

    @Override
    public boolean equals(Object obj) {
        Player newPlayer = null;
        if (obj != null && obj instanceof Player) {
            newPlayer = (Player) obj;
        }
        return this.getUUID().toString().equals(newPlayer.getUUID().toString());
    }
}

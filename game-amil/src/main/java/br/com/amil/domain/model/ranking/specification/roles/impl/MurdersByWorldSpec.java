package br.com.amil.domain.model.ranking.specification.roles.impl;


import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunKit;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.Player;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MurdersByWorldSpec implements RoleSpec {

    private Gun gun;

    public MurdersByWorldSpec(Gun gun) {
        this.gun = gun;
    }

    @Override
    public boolean isSatisfied() {
        Gun gunByWorld = Gun.newGun("DROWN", GunType.DROWN);
        return gun.sameGun(gunByWorld);
    }
}

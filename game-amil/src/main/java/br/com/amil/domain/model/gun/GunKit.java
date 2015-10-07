package br.com.amil.domain.model.gun;

import br.com.amil.domain.model.ranking.sort.SortGunByFrequency;
import br.com.amil.domain.model.ranking.sort.SortType;

import java.util.*;

public class GunKit {

    Map<GunType, Gun> guns;

    private GunKit(Map<GunType, Gun> guns) {
        this.guns = guns;
    }

    private GunKit() {

    }

    public void using(Gun gun) {
        if (guns == null) {
            guns = new HashMap<GunType, Gun>();
        }

        if (guns.get(gun.type()) != null) {
            guns.get(gun.type()).updateFrequency();
        } else {
            gun.updateFrequency();
            guns.put(gun.type(), gun);
        }
    }

    public Gun preferredGun() {
        SortGunByFrequency sort = SortGunByFrequency.create(SortType.DESC);
        List<Gun> gunsList = new ArrayList(guns.values());

        Collections.sort(gunsList, sort);

        Gun gun = gunsList.get(0);
        return gun;
    }

    public Map guns() {
        return this.guns;
    }

    public static GunKit create(Map<GunType, Gun> guns) {
        return new GunKit(guns);
    }

    public static GunKit create() {
        return new GunKit();
    }
}

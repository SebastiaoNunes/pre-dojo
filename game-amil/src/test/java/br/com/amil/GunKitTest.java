package br.com.amil;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunHistory;
import br.com.amil.domain.model.gun.GunKit;
import br.com.amil.domain.model.gun.GunType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GunKitTest {

    @Test
    public void shouldUpdateOnlyHistoryIfGunAlreadyExist() {
        Gun m16 = Gun.newGun("M16", GunType.M16);
        Gun knife = Gun.newGun("KNIFE", GunType.KNIFE);
        Gun ak47 = Gun.newGun("KNIFE", GunType.AK47);

        Map<GunType, Gun> guns = new HashMap<GunType, Gun>();
        guns.put(GunType.M16, m16);
        guns.put(GunType.KNIFE, knife);
        guns.put(GunType.AK47, ak47);

        GunKit kit = GunKit.create(guns);
        kit.using(m16);

        Map<GunType, Gun> gunsMap = kit.guns();
        Gun gunFromMap = gunsMap.get(m16.type());
        GunHistory history = gunFromMap.history();

        assertThat(history.frequency(), equalTo(1));
        assertThat(kit.guns().size(), equalTo(3));
    }

    @Test
    public void shouldShowFrequencyOfUse() {
        Gun m16 = Gun.newGun("M16", GunType.M16);
        GunKit kit = GunKit.create();
        kit.using(m16);
        kit.using(m16);
        kit.using(m16);

        Map<GunType, Gun> guns = kit.guns();
        Gun gun = guns.get(m16.type());
        GunHistory history = gun.history();

        assertThat(history.frequency(), equalTo(3));
        assertThat(kit.guns().size(), equalTo(1));
    }

    @Test
    public void shouldImproveGuns() {
        Gun m16 = Gun.newGun("M16", GunType.M16);
        Gun knife = Gun.newGun("KNIFE", GunType.KNIFE);
        Gun ak47 = Gun.newGun("KNIFE", GunType.AK47);

        Map<GunType, Gun> guns = new HashMap<GunType, Gun>();
        guns.put(GunType.M16, m16);
        guns.put(GunType.KNIFE, knife);
        guns.put(GunType.AK47, ak47);
        GunKit kit = GunKit.create(guns);

        Gun magnum = Gun.newGun("MAGNUM", GunType.MAGNUM);
        kit.using(magnum);

        assertThat(kit.guns().size(), equalTo(4));
    }

    @Test
    public void shouldShowPreferredGun() {
        Gun m16 = Gun.newGun("M16", GunType.M16);
        Gun knife = Gun.newGun("KNIFE", GunType.KNIFE);
        Gun ak47 = Gun.newGun("KNIFE", GunType.AK47);

        GunKit gunKit = GunKit.create();
        gunKit.using(m16);
        gunKit.using(knife);
        gunKit.using(ak47);
        gunKit.using(ak47);

        Gun gun = gunKit.preferredGun();

        assertThat(gun.type().value(), equalTo(GunType.AK47.value()));
    }
}

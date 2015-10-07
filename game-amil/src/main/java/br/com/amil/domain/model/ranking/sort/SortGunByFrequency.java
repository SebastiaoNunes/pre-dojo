package br.com.amil.domain.model.ranking.sort;

import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunHistory;
import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;

import java.util.Comparator;

public class SortGunByFrequency implements Comparator<Gun>  {

    private SortType sortType;

    public SortGunByFrequency(SortType sortType) {
        this.sortType = sortType;
    }

    public int compare(Gun gun1, Gun gun2) {
        GunHistory history1 = gun1.history();
        GunHistory history2 = gun2.history();

        //ascending order
        if (sortType.value().equals(SortType.ASC.value())) {
            return history1.frequency().compareTo(history2.frequency());
        }

        //descending order
        return history2.frequency().compareTo(history1.frequency());
    }


    public static SortGunByFrequency create(SortType sortType) {
        return new SortGunByFrequency(sortType);
    }
}

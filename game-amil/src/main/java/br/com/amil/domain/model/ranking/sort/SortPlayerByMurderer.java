package br.com.amil.domain.model.ranking.sort;

import br.com.amil.domain.model.ranking.History;
import br.com.amil.domain.model.ranking.Player;

import java.util.Comparator;

public class SortPlayerByMurderer implements Comparator<Player> {

    private SortType sortType;

    private SortPlayerByMurderer(SortType sortType) {
        this.sortType = sortType;
    }

    public int compare(Player player1, Player player2) {
        History history1 = player1.getHistory();
        History history2 = player2.getHistory();

        //ascending order
        if (sortType.value().equals(SortType.ASC.value())) {
            return history1.getMurders().compareTo(history2.getMurders());
        }

        //descending order
        return history2.getMurders().compareTo(history1.getMurders());
    }

    public static SortPlayerByMurderer create(SortType sortType) {
        return new SortPlayerByMurderer(sortType);
    }
}

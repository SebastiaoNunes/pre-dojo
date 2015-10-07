package br.com.amil.domain.model.ranking;

import br.com.amil.domain.model.game.Action;
import br.com.amil.domain.model.gun.Gun;
import br.com.amil.domain.model.gun.GunType;
import br.com.amil.domain.model.ranking.specification.roles.RoleSpec;
import br.com.amil.domain.model.ranking.specification.roles.impl.MurdersByWorldSpec;

import java.util.*;

public class History {
    private int murders;
    private int deaths;
    private List<Action> actionHistory = new LinkedList<Action>();

    private History() {

    }

    public void addMurders(int murders) {
        this.murders = this.murders + murders;
    }

    public void addDeaths(int deaths) {
        this.deaths = this.deaths + deaths;
    }

    public Integer getMurders() {
        return this.murders;
    }

    public Integer getDeaths() {
        return this.deaths;
    }

    public static History create() {
        return new History();
    }

    public int mostMurderSequenceWithoutDying() {
        if (actionHistory == null || actionHistory.isEmpty()) {
            return 0;
        }

        List<Integer> frequencyMurders = new ArrayList<Integer>();

        int firstElement = 0;
        int secondElement = 1;
        int lastElement = actionHistory.size();
        int repeatedElements = 1;

        Action supportAction = actionHistory.get(firstElement);

        for (Action currentAction : actionHistory.subList(secondElement, lastElement)) {
            if (currentAction.toString().equals(supportAction.toString())) {
                repeatedElements++;
            }
            else {
                if ((repeatedElements > 1) && (supportAction.toString().equals(Action.MURDERED.toString()))) {
                   frequencyMurders.add(repeatedElements);
                }
                supportAction = currentAction;
            }
        }

        if( repeatedElements > 1 && supportAction.toString().equals(Action.MURDERED.toString())) {
            frequencyMurders.add(repeatedElements);
        }

        Collections.sort(frequencyMurders);

        int lastFreq = 0;

        if (frequencyMurders.size() > 1) {
            return (frequencyMurders.size() - 1);
        }

        if (frequencyMurders.size() == 0) {
            return 0;
        }
        return frequencyMurders.get(0);
    }

    public void addAction(Action action, Gun currentGun) {
        RoleSpec spec = new MurdersByWorldSpec(currentGun);

        if (action.value().equals(Action.MURDERED.value()) && !spec.isSatisfied()) {
            this.addMurders(1);
        } else if (action.value().equals(Action.KILLED.value())) {
            this.addDeaths(1);
        }
        actionHistory.add(action);
    }
}

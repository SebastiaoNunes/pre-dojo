package br.com.amil.domain.model.game;

import java.util.Arrays;
import java.util.List;

public enum Action {
    MURDERED("MURDERED"),
    KILLED("KILLED");
    private String value;

    private Action(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static List<Action> asList() {
        return Arrays.asList(MURDERED, KILLED);
    }
}

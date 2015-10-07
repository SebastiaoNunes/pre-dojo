package br.com.amil.domain.model.game;

import java.util.ArrayList;
import java.util.List;

public class GameSpec {

    private List<Scenario> scenarios;

    public void addScenario(Scenario ... scenarios) {
        if (this.scenarios == null || this.scenarios.isEmpty()) {
            this.scenarios = new ArrayList<Scenario>();
        }

        for (Scenario scenario : scenarios) {
            this.scenarios.add(scenario);
        }
    }

    public List<Scenario> getScenarios() {
        return this.scenarios;
    }

    public static GameSpec create() {
        return new GameSpec();
    }
}

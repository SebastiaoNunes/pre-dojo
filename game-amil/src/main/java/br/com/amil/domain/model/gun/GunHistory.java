package br.com.amil.domain.model.gun;

public class GunHistory {

    private int frequency;

    public static GunHistory create() {
        return new GunHistory();
    }

    public void updateFrequency() {
        this.frequency = frequency + 1;
    }

    public Integer frequency() {
        return this.frequency;
    }
}

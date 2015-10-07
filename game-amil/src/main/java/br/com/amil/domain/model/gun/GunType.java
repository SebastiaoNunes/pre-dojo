package br.com.amil.domain.model.gun;

import java.util.Arrays;
import java.util.List;

public enum GunType {
    M16("M16"),
    AK47("AK47"),
    MAGNUM("MAGNUM"),
    KNIFE("KNIFE"),
    DROWN("DROWN");

    private String value;

    private GunType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
    public static List<GunType> asList() {
        return Arrays.asList(M16, AK47, MAGNUM, KNIFE, DROWN);
    }
}

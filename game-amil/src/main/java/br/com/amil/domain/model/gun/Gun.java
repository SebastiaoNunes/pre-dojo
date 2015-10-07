package br.com.amil.domain.model.gun;

public class Gun {

    private GunType type;
    private String name;
    private GunHistory history = GunHistory.create();

    private Gun(String name, GunType type) {
        this.name = name;
        this.type = type;
    }

    public static Gun newGun(String name, GunType type) {
        return new Gun(name, type);
    }

    public GunType type() {
        return this.type;
    }

    public boolean sameGun(Gun currentGun) {
        return (this.type().value().equals(currentGun.type().value()));

    }

    public GunHistory history() {
        return history;
    }

    public void updateFrequency(){
        history.updateFrequency();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Gun) {
            Gun newGun = (Gun) obj;
            return this.type().value().equals(newGun.type().value());
        }
        return false;
    }
}

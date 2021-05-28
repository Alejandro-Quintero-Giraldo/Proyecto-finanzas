package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class BolsilloId extends Identity {
    public BolsilloId(String uuid) {
        super(uuid);
    }

    public BolsilloId() {
    }

    public BolsilloId from(String uuid) {
        return new BolsilloId(uuid);
    }
}

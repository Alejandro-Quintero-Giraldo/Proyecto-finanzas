package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class IngresoId extends Identity {
    public IngresoId(String uuid) {
        super(uuid);
    }

    public IngresoId() {
    }

    public IngresoId from(String uuid){
        return new IngresoId(uuid);
    }
}

package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class EgresoId extends Identity {
    public EgresoId(String uuid) {
        super(uuid);
    }

    public EgresoId() {
    }

    public EgresoId from(String uuid) {
        return  new EgresoId(uuid);
    }
}

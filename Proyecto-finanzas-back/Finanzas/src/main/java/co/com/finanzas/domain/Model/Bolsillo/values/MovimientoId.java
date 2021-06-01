package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.finanzas.domain.Model.Bolsillo.Movimiento;
import co.com.sofka.domain.generic.Identity;

public class MovimientoId extends Identity {
    public MovimientoId(String uuid) {
        super(uuid);
    }

    public MovimientoId() {
    }

    public static MovimientoId from(String uuid){
        return new MovimientoId(uuid);
    }
}

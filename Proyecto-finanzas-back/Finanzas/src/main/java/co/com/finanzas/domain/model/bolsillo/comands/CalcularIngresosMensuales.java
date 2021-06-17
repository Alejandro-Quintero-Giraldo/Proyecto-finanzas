package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.sofka.domain.generic.Command;
import java.util.Map;

public class CalcularIngresosMensuales implements Command {
    private final Map<MovimientoId, Movimiento> movimientos;


    public CalcularIngresosMensuales(Map<MovimientoId, Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Map<MovimientoId, Movimiento> getMovimientos() {
        return movimientos;
    }
}

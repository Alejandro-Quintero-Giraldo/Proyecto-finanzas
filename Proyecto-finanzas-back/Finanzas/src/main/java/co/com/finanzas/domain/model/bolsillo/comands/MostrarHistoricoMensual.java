package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.useCase.CalcularEgresosMensualesUseCase;
import co.com.finanzas.useCase.CalcularIngresosMensualesUseCase;
import co.com.sofka.domain.generic.Command;

public class MostrarHistoricoMensual implements Command {
    private final Integer ingresosMensuales;
    private final Integer egresosMensuales;


    public MostrarHistoricoMensual(Integer ingresosMensuales, Integer egresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
        this.egresosMensuales = egresosMensuales;
    }

    public Integer getIngresosMensuales() {
        return ingresosMensuales;
    }

    public Integer getEgresosMensuales() {
        return egresosMensuales;
    }
}

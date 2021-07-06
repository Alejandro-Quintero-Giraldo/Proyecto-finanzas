package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.CalcularEgresoMensual;
import co.com.finanzas.domain.model.bolsillo.comands.CalcularIngresosMensuales;
import co.com.finanzas.domain.model.bolsillo.events.EgresoMensualCalculado;
import co.com.finanzas.domain.model.bolsillo.events.IngresoMensualCalculado;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Service
public class CalcularEgresosMensualesUseCase extends UseCase<RequestCommand<CalcularEgresoMensual>, CalcularEgresosMensualesUseCase.Response> {

    @Override
    public void executeUseCase(RequestCommand<CalcularEgresoMensual> calcularEgresoMensualRequestCommand) {

        CalcularEgresoMensual command = calcularEgresoMensualRequestCommand.getCommand();

        var movimientos = command.getMovimientos();

        var egresoParcial = agruparValores(movimientos);

        var egresosMensuales = Calcular(egresoParcial);

        emit().onResponse(new Response());
    }

    public Set<Integer> agruparValores(Map<MovimientoId, Movimiento> movimientos){
        Set<Integer> egresoParcial= new HashSet<>();
        movimientos.forEach((movimientoId, movimiento) -> egresoParcial.add(movimiento.getSaldo().value()));
        return  egresoParcial;
    }

    public Integer Calcular(Set<Integer> egresoParcial) {
        Integer egresosMensuales = 0;

        for (Integer element : egresoParcial) {
            egresosMensuales += element;
        }
        return egresosMensuales;
    }

    public static class Response implements UseCase.ResponseValues {
    }
}


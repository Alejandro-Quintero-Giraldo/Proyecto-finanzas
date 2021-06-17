package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.CalcularIngresosMensuales;
import co.com.finanzas.domain.model.bolsillo.events.IngresoMensualCalculado;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Service
public class CalcularIngresosMensualesUseCase implements Function<CalcularIngresosMensuales, Mono<DomainEvent>> {
    @Override
    public Mono<DomainEvent> apply(CalcularIngresosMensuales calcularIngresosMensuales) {
        var movimientos = calcularIngresosMensuales.getMovimientos();

        var ingresoParcial = agruparValores(movimientos);

        var ingresosMensuales = Calcular(ingresoParcial);

        return Mono.just(new IngresoMensualCalculado(ingresosMensuales));
    }

    public Set<Integer> agruparValores(Map<MovimientoId, Movimiento> movimientos){
        Set<Integer> ingresoParcial= new HashSet<>();
        movimientos.forEach((movimientoId, movimiento) -> ingresoParcial.add(movimiento.getSaldo().value()));
        return  ingresoParcial;
    }

    public Integer Calcular(Set<Integer> ingresoParcial) {
        Integer ingresosMensuales = 0;

        for (Integer element : ingresoParcial) {
            ingresosMensuales += element;
        }
        return ingresosMensuales;
    }
}

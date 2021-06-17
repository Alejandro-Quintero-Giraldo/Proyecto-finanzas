package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarHistoricoMensual;
import co.com.finanzas.domain.model.bolsillo.events.HistoricoMensualMostrado;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class MostrarHistoricoMensualUseCase implements Function<MostrarHistoricoMensual, Mono<DomainEvent>> {
    @Override
    public Mono<DomainEvent> apply(MostrarHistoricoMensual mostrarHistoricoMensual) {
        var ingresosMensuales = mostrarHistoricoMensual.getIngresosMensuales();

        var egresosMensuales = mostrarHistoricoMensual.getEgresosMensuales();

        return Mono.just(new HistoricoMensualMostrado(ingresosMensuales,egresosMensuales));
    }
}

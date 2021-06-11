package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.SacarDinero;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component
public class SacarDineroUseCase implements Function<SacarDinero, Flux<DomainEvent>>{

    @Override
    public Flux<DomainEvent> apply(SacarDinero sacarDinero) {
        var movimiento = new Movimiento(
                sacarDinero.getMovimientoId(),
                sacarDinero.getTipo(),
                sacarDinero.getFecha(),
                sacarDinero.getSaldo(),
                sacarDinero.getBolsilloId(),
                sacarDinero.getUid()
        );
    }
}

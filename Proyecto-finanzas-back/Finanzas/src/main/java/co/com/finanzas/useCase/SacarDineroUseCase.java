package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.SacarDinero;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class SacarDineroUseCase implements Function<SacarDinero, Flux<DomainEvent>>{

    @Autowired
    DomainEventRepository repository;

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
        var bolsillo = Bolsillo.from(sacarDinero.getBolsilloId(),repository.getEventsBy("Bolsillo",sacarDinero.getBolsilloId().value()));
        bolsillo.sacarDinero(movimiento);
        return Flux.fromIterable(bolsillo.getUncommittedChanges());
    }
}

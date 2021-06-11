package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.IngresarDinero;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.values.Fecha;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.finanzas.domain.model.bolsillo.values.Saldo;
import co.com.finanzas.domain.model.bolsillo.values.Tipo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Component
public class IngresarDineroUseCase implements Function<IngresarDinero, Flux<DomainEvent>>{

    @Autowired
    DomainEventRepository repository;

    @Override
    public Flux<DomainEvent> apply(IngresarDinero ingresarDinero){
        var movimiento = new Movimiento(
                ingresarDinero.getMovimientoId(),
                ingresarDinero.getTipo(),
                ingresarDinero.getFecha(),
                ingresarDinero.getSaldo(),
                ingresarDinero.getBolsilloId(),
                ingresarDinero.getUid()
                );
        //TODO: Encontrar el método para retornar el List<DomainEvent>
        var bolsillo = Bolsillo.from(ingresarDinero.getBolsilloId(),repository.getEventsBy("Bolsillo",ingresarDinero.getBolsilloId().value()));
        bolsillo.ingresarDinero(movimiento);
        return  Flux.fromIterable(bolsillo.getUncommittedChanges());
    }
}

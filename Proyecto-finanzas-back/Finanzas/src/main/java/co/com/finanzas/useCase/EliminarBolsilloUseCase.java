package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.EliminarBolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Component
public class EliminarBolsilloUseCase implements Function<EliminarBolsillo, Flux<DomainEvent>>{
    @Autowired
    DomainEventRepository repository;

    @Override
    public Flux<DomainEvent> apply(EliminarBolsillo eliminarBolsillo){
       var bolsillo = Bolsillo.from(eliminarBolsillo.getBolsilloId(),repository.getEventsBy("Bolsillo",eliminarBolsillo.getBolsilloId().value()));
        bolsillo.eliminarBolsillo(eliminarBolsillo.getEsEliminado());
        return Flux.fromIterable(bolsillo.getUncommittedChanges());
    }
}

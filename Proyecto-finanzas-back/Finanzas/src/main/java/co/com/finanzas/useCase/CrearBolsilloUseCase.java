package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;
@Component
public class CrearBolsilloUseCase implements Function<CrearBolsillo, Flux<DomainEvent>> {
    @Override
    public Flux<DomainEvent> apply(CrearBolsillo crearBolsillo){
        var bolsillo = new Bolsillo(
                crearBolsillo.getBolsilloId(),
                crearBolsillo.getNombre(),
                crearBolsillo.getSaldoDisponible(),
                crearBolsillo.getuId(),
                crearBolsillo.getEsAhorro(),
                crearBolsillo.getPorcentajeAhorro()
        );
        return Flux.fromIterable(bolsillo.getUncommittedChanges());
    }
}

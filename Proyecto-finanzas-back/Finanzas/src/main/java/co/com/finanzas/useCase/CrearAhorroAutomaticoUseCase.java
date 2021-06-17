package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.CrearAhorroAutomatico;
import co.com.finanzas.domain.model.bolsillo.events.AhorroCreado;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class CrearAhorroAutomaticoUseCase implements Function<CrearAhorroAutomatico, Mono<DomainEvent>> {
    @Override
    public Mono<DomainEvent> apply(CrearAhorroAutomatico crearAhorroAutomatico) {
        new Bolsillo(
                crearAhorroAutomatico.getBolsilloId(),
                crearAhorroAutomatico.getNombre(),
                crearAhorroAutomatico.getSaldoDisponible(),
                crearAhorroAutomatico.getUid(),
                crearAhorroAutomatico.getEsAhorro(),
                crearAhorroAutomatico.getPorcentajeAhorro()
        );

        return Mono.just(
                new AhorroCreado(
                        crearAhorroAutomatico.getNombre(),
                        crearAhorroAutomatico.getSaldoDisponible(),
                        crearAhorroAutomatico.getUid(),
                        crearAhorroAutomatico.getEsAhorro(),
                        crearAhorroAutomatico.getPorcentajeAhorro()
                )
        );

    }
}
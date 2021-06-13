package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioMostrado;
import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class MostrarUsuarioUseCase implements Function<MostrarUsuario, Mono<DomainEvent>> {

    @Override
    public Mono<DomainEvent> apply(MostrarUsuario mostrarUsuario) {

        return Mono.just(new UsuarioMostrado(mostrarUsuario.getUid(), mostrarUsuario.getNombre(), mostrarUsuario.getEmail()));
    }
}

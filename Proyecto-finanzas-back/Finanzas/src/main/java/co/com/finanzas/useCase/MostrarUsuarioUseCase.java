package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioMostrado;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class MostrarUsuarioUseCase implements Function<MostrarUsuario, Mono<DomainEvent>> {

    @Override
    public Mono<DomainEvent> apply(MostrarUsuario mostrarUsuario) {

        return Mono.just(new UsuarioMostrado(mostrarUsuario.getUid(), mostrarUsuario.getNombre(), mostrarUsuario.getEmail()));
    }
}

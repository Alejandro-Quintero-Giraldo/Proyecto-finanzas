package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Usuario;
import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioCreado;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class CrearUsuarioUseCase implements Function<CrearUsuario, Mono<DomainEvent>> {

    @Override
    public Mono<DomainEvent> apply(CrearUsuario crearUsuario) {
        new Usuario(crearUsuario.getUid(), crearUsuario.getNombre(), crearUsuario.getEmail(), crearUsuario.getEsEliminado());

        return Mono.just(
                new UsuarioCreado(
                crearUsuario.getUid(),
                crearUsuario.getNombre(),
                crearUsuario.getEmail()
                )
        );
    }
}
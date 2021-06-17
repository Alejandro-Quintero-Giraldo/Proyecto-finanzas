package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Usuario;
import co.com.finanzas.domain.model.bolsillo.comands.EliminarUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioEliminado;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class EliminarUsuarioUseCase implements Function<EliminarUsuario, Mono<DomainEvent>> {

    @Override
    public  Mono<DomainEvent> apply(EliminarUsuario eliminarUsuario){
        new Usuario(eliminarUsuario.getUid(),eliminarUsuario.getNombre(),eliminarUsuario.getEmail(),eliminarUsuario.getEsEliminado());

        return Mono.just(new UsuarioEliminado(eliminarUsuario.getUid(),eliminarUsuario.getNombre(),eliminarUsuario.getEmail(),eliminarUsuario.getEsEliminado()));
    }
}

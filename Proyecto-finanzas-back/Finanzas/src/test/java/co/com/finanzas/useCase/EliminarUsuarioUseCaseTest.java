package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.EliminarUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioEliminado;
import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class EliminarUsuarioUseCaseTest {

    EliminarUsuarioUseCase eliminarUsuarioUseCase;

    @BeforeEach
    public  void setUp(){
        eliminarUsuarioUseCase = new EliminarUsuarioUseCase();
    }

    @Test
    void eliminarUsuario(){
        var command = new EliminarUsuario(UsuarioId.of("123"), new Nombre("Alfredo Quintana"), new Email("AlfreQ@gmail.com"));

        var event = new UsuarioEliminado(command.getUid(), command.getNombre(), command.getEmail(), command.getEsEliminado());

        StepVerifier
                .create(eliminarUsuarioUseCase.apply(command))
                .expectNextMatches(domainEvent ->  domainEvent.equals(event))
                .expectComplete();
    }
}
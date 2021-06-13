package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioMostrado;
import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MostrarUsuarioUseCaseTest {

    MostrarUsuarioUseCase mostrarUsuarioUseCase;

    @BeforeEach
    public  void setUp()
    {
        mostrarUsuarioUseCase = new MostrarUsuarioUseCase();
    }

    @Test
    void mostrarUsuario(){
        var command = new MostrarUsuario(UsuarioId.of("123"),new Nombre("Alfredo Quintana"), new Email("AlfreQ@gmail.com"));

        var event = new UsuarioMostrado(command.getUid(), command.getNombre(), command.getEmail());

        StepVerifier
                .create(mostrarUsuarioUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();
    }
}
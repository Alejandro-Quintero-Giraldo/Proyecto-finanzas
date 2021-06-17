package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarHistoricoMensual;
import co.com.finanzas.domain.model.bolsillo.events.HistoricoMensualMostrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MostrarHistoricoMensualUseCaseTest {
    MostrarHistoricoMensualUseCase mostrarHistoricoMensualUseCase;

    @BeforeEach
    public void setUp(){
        mostrarHistoricoMensualUseCase = new MostrarHistoricoMensualUseCase();
    }

    @Test
    void mostrarHistoricoMensual(){
        var command = new MostrarHistoricoMensual(3000, 2000);

        var event = new HistoricoMensualMostrado(command.getIngresosMensuales(), command.getEgresosMensuales());

        StepVerifier
                .create(mostrarHistoricoMensualUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();
    }



}
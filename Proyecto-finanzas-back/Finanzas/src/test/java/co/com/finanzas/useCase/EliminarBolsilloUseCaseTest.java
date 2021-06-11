package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.EliminarBolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class EliminarBolsilloUseCaseTest {

    EliminarBolsilloUseCase eliminarBolsilloUseCase;

    @BeforeEach
    public  void setUp(){
        eliminarBolsilloUseCase = new EliminarBolsilloUseCase();
    }

    @Test
    void eliminarUnBolsillo(){
        var command = new EliminarBolsillo(BolsilloId.of("xxxxx"));

        var event = new BolsilloEliminado(command.getEsEliminado());

        StepVerifier.create(eliminarBolsilloUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();
    }

}
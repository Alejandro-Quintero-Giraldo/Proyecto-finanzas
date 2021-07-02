package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.PorcentajeAhorro;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CrearBolsilloUseCaseTest {

    @Test
    void CrearBolsilloUseCaseTest(){
        var command = new CrearBolsillo(
                BolsilloId.of("xxxxx"),
                new Nombre("Transporte"),
                UsuarioId.of("123"),
                new PorcentajeAhorro(10)
        );
        var useCase = new CrearBolsilloUseCase();

        CrearBolsilloUseCase.Response response = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command)).orElseThrow();
        var events = response;


        Assertions.assertEquals("xxxxx", events.getResponse().identity().value());
        Assertions.assertEquals("Transporte",events.getResponse().getNombre().value());
        Assertions.assertEquals(0, events.getResponse().getSaldoDisponible().value());
        Assertions.assertEquals(Boolean.FALSE,events.getResponse().getEsAhorro().value());
        Assertions.assertEquals("123",events.getResponse().getUid().value());
        Assertions.assertEquals(10, events.getResponse().getPorcentajeAhorro().value());
/*
        StepVerifier
                .create(crearBolsilloUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();*/
    }

}
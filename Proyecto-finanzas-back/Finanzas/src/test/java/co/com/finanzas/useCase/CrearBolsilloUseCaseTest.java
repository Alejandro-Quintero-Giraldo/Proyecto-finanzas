package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.PorcentajeAhorro;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CrearBolsilloUseCaseTest {

    CrearBolsilloUseCase crearBolsilloUseCase;

    @BeforeEach
    public void setUp(){
        crearBolsilloUseCase = new CrearBolsilloUseCase();
    }
    @Test
    void CrearBolsilloUseCaseTest(){
        var command = new CrearBolsillo(
                BolsilloId.of("xxxxx"),
                new Nombre("Transporte"),
                UsuarioId.of("123"),
                new PorcentajeAhorro(10)
        );
        var event = new BolsilloCreado(
                command.getNombre(),
                command.getSaldoDisponible(),
                command.getMovimientos(),
                command.getuId(),
                command.getEsAhorro(),
                command.getPorcentajeAhorro()
        );

        StepVerifier
                .create(crearBolsilloUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();
    }
}
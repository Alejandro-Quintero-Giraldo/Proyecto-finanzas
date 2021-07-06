package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.CalcularIngresosMensuales;
import co.com.finanzas.domain.model.bolsillo.events.IngresoMensualCalculado;
import co.com.finanzas.domain.model.bolsillo.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import reactor.test.StepVerifier;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalcularIngresosMensualesUseCaseTest {

    CalcularIngresosMensualesUseCase calcularIngresosMensualesUseCase;

    @BeforeEach
    public  void setUp(){
        calcularIngresosMensualesUseCase = new CalcularIngresosMensualesUseCase();
    }

    @Test
    void calcularIngresoMensual(){
        var movimientos = Map.of(
                MovimientoId.of("123"),
                new Movimiento(
                MovimientoId.of("123"), new Tipo("Ingreso"), new Fecha(), new Saldo(10000), BolsilloId.of("xxxxx"), UsuarioId.of("123")
                ),
                MovimientoId.of("456"),
                new Movimiento(
                        MovimientoId.of("456"), new Tipo("Ingreso"), new Fecha(), new Saldo(25000), BolsilloId.of("xxxxx"), UsuarioId.of("123")
                )
        );

        var command = new CalcularIngresosMensuales(movimientos);

        var event = new IngresoMensualCalculado(calcularIngresosMensualesUseCase.Calcular(calcularIngresosMensualesUseCase.agruparValores(movimientos)));

        Assertions.assertEquals(35000,event.getIngresosMensuales());
/*
        StepVerifier.create(calcularIngresosMensualesUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();*/
    }

}
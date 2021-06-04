package co.com.finanzas.domain.useCase;

import co.com.finanzas.domain.Model.Bolsillo.Movimiento;
import co.com.finanzas.domain.Model.Bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.Model.Bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.Model.Bolsillo.values.*;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CrearBolsilloUseCaseTest {

    @Test
    public void crearBolsilloTest() {
        var movimientos = Map.of(
                MovimientoId.from("xxxxx"),
                new Movimiento(new MovimientoId("zzzzz"), new Tipo("Ingreso"), new Fecha(), new Saldo(200000), new BolsilloId("xxxxx"), new UsuarioId("123")),
                MovimientoId.from("yyyyy"),
                new Movimiento(new MovimientoId("aaaaa"), new Tipo("Egreso"), new Fecha(), new Saldo(50000), new BolsilloId("xxxxx"), new UsuarioId("123"))
                );
        var command = new CrearBolsillo(BolsilloId.from("123"), new Nombre("Transporte"), new SaldoDisponible(), movimientos, new UsuarioId(), new TipoAhorro(false), new PorcentajeAhorro(10));

        BolsilloCreado bolsilloCreado = casoDeUsoEjecutado(command);

        Assertions.assertEquals("123",bolsilloCreado.getBolsilloId().value());
        Assertions.assertEquals("Transporte",bolsilloCreado.getNombre().value());
        Assertions.assertNotNull(bolsilloCreado.getSaldoDisponible());
        Assertions.assertNotNull(bolsilloCreado.getMovimientos());
        Assertions.assertNotNull(bolsilloCreado.getuId());
        Assertions.assertEquals(false,bolsilloCreado.getTipoAhorro().value());
        Assertions.assertEquals(10,bolsilloCreado.getPorcentajeAhorro().value());


    }
    private BolsilloCreado casoDeUsoEjecutado(CrearBolsillo command){
        var useCase = new CrearBolsilloUseCase();
        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command)).orElseThrow().getDomainEvents();
        var bolsilloCreado = (BolsilloCreado)events.get(0);
        return  bolsilloCreado;
    }

}
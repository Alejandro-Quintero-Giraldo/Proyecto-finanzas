package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.*;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.finanzas.useCase.*;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BolsilloController {

    @Autowired
    private CrearBolsilloUseCase crearBolsilloUseCase;

    @Autowired
    private ActualizarBolsilloUseCase actualizarBolsilloUseCase;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Autowired
    private IngresarDineroUseCase ingresarDineroUseCase;

    @Autowired
    private TransformacionMovimientoUseCase transformacionMovimientoUseCase;

    @Autowired
    private SacarDineroUseCase sacarDineroUseCase;

    @PostMapping(value = "api/crearbolsillo/{bolsilloId}/{nombre}/{uid}/{porcentajeAhorro}")
    public String saveBolsillo(
            @PathVariable("bolsilloId")String bolsilloId,
            @PathVariable("nombre")String nombre,
            @PathVariable("uid")String uid,
            @PathVariable("porcentajeAhorro")Integer porcentajeAhorro){

        var command = new CrearBolsillo(
                BolsilloId.of(bolsilloId),
                new Nombre(nombre),
                UsuarioId.of(uid),
                new PorcentajeAhorro(porcentajeAhorro));

        CrearBolsilloUseCase.Response bolsilloCreado = executedCrearBolsilloUseCase(command);
        String string = "{" + "\"BolsilloId\":"+ "\""+bolsilloCreado.getResponse().getIdPro()+"\""+","
                +"\"Nombre\":"+"\""+bolsilloCreado.getResponse().getNombre().value()+"\""+","
                +"\"Saldo disponible\":"+"\""+bolsilloCreado.getResponse().getSaldoDisponible().value()+"\""+","
                +"\"UsuarioId\":"+"\""+bolsilloCreado.getResponse().getUid().value()+"\""+","
                +"\"¿Es Ahorro?\":"+"\""+bolsilloCreado.getResponse().getEsAhorro().value()+"\""+","
                +"\"Porcentaje de ahorro\":"+"\""+bolsilloCreado.getResponse().getPorcentajeAhorro().value()
                +"}";

        return string;
    }

    private CrearBolsilloUseCase.Response executedCrearBolsilloUseCase(CrearBolsillo command){
        var events = UseCaseHandler.getInstance()
                .syncExecutor(crearBolsilloUseCase,new RequestCommand<>(command)).orElseThrow();
        var bolsilloCreado = events;
        return bolsilloCreado;
    }

    @PutMapping(value = "api/actualizarBolsillo/{id}/{nombre}/{saldoDisponible}/{uid}/{esAhorro}/{porcentajeAhorro}")
    public String actualizar(@PathVariable("id") String id,
                             @PathVariable("nombre") String nombre,
                             @PathVariable("saldoDisponible") Integer saldoDisponible,
                             @PathVariable("uid") String uid,
                             @PathVariable("esAhorro") Boolean esAhorro,
                             @PathVariable("porcentajeAhorro") Integer porcentajeAhorro){
        var command = new ActualizarBolsillo(BolsilloId.of(id), new Nombre(nombre), new SaldoDisponible(saldoDisponible), UsuarioId.of(uid), new EsAhorro(esAhorro), new PorcentajeAhorro(porcentajeAhorro));
        ActualizarBolsilloUseCase.Response bolsilloActualizado = executedActualizarBolsilloUseCase(command);

        String string = "{" + "\"BolsilloId\":"+ "\""+bolsilloActualizado.getResponse().getIdPro()+"\""+","
                +"\"Nombre\":"+ "\""+bolsilloActualizado.getResponse().getNombre().value()+"\""+","
                +"\"Saldo disponible\":"+"\""+bolsilloActualizado.getResponse().getSaldoDisponible().value()+"\""+","
                +"\"UsuarioId\":"+"\""+bolsilloActualizado.getResponse().getUid().value()+"\""+","
                +"\"¿Es Ahorro?\":"+"\""+bolsilloActualizado.getResponse().getEsAhorro().value()+"\""+","
                +"\"Porcentaje de ahorro\":"+"\""+bolsilloActualizado.getResponse().getPorcentajeAhorro().value()+"}";

        return string;
    }

    public  ActualizarBolsilloUseCase.Response executedActualizarBolsilloUseCase(ActualizarBolsillo command){
        var events = UseCaseHandler.getInstance()
                .syncExecutor(actualizarBolsilloUseCase, new RequestCommand<>(command)).orElseThrow();
        var bolsilloActualizado = events;
        return  bolsilloActualizado;
    }

    @GetMapping(value = "api/mostrarBolsillos")
    public Iterable<BolsilloData> listar(){
        return (transformacionBolsilloUseCase.listar());
    }

    @GetMapping(value = "api/mostrarBolsillo/{id}")
    public BolsilloData listarPorId(@PathVariable("id") String id){
        return (transformacionBolsilloUseCase.listarPorId(id));
    }

    @DeleteMapping(value = "api/eliminarBolsillo/{id}")
    public String eliminar(@PathVariable("id") String id){
        return (transformacionBolsilloUseCase.eliminar(id));
    }

    @PostMapping(value = "api/ingresarDinero/{movimientoId}/{saldo}/{bolsilloId}/{uid}")
    public String saveIngresarDinero(
            @PathVariable("movimientoId") String movimientoId,
            @PathVariable("saldo") Integer saldo,
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("uid") String uid) {

        IngresarDinero command = new IngresarDinero(MovimientoId.of(movimientoId), new Saldo(saldo), BolsilloId.of(bolsilloId), UsuarioId.of(uid));

        IngresarDineroUseCase.Response dineroIngresado = executedIngresarDineroUseCase(command);

        String string = "{" + "\"MovimientoId\":" + "\"" + dineroIngresado.getResponse().identity().value() + "\"" + ","
                + "\"Saldo\":" + "\"" + dineroIngresado.getResponse().getSaldo().value() + "\"" + ","
                + "\"Tipo\":" + "\"" + dineroIngresado.getResponse().getTipo().value() + "\"" + ","
                + "\"Fecha\":" + "\"" + dineroIngresado.getResponse().getFecha().value() + "\"" + ","
                + "\"UsuarioId\":" + "\"" + dineroIngresado.getResponse().getUid().value() + "\"" + ","
                + "\"BolsilloId\":" + "\"" + dineroIngresado.getResponse().getBolsilloId().value() + "}";
        return string;
    }

    public IngresarDineroUseCase.Response executedIngresarDineroUseCase(IngresarDinero command){

        IngresarDineroUseCase.Response dineroIngresado = UseCaseHandler.getInstance()
                .syncExecutor(ingresarDineroUseCase,new RequestCommand<>(command)).orElseThrow();
        return  dineroIngresado;
    }

    @GetMapping(value = "api/mostrarMovimientos/{bolsilloId}")
    public Iterable<MovimientoData> mostrarMovimientosPorBolsillo(@PathVariable("bolsilloId") String bolsilloId){
        return transformacionMovimientoUseCase.listarPorBolsilloId(bolsilloId);
    }

    @PostMapping(value = "api/sacarDinero/{movimientoId}/{saldo}/{bolsilloId}/{uid}")
    public String saveSacarDinero(
            @PathVariable("movimientoId") String movimientoId,
            @PathVariable("saldo") Integer saldo,
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("uid") String uid) {

        SacarDinero command = new SacarDinero(MovimientoId.of(movimientoId), new Saldo(saldo), BolsilloId.of(bolsilloId), UsuarioId.of(uid));

        SacarDineroUseCase.Response dineroSacado = executedSacarDineroUseCase(command);

        String string = "{" + "\"MovimientoId\":" + "\"" + dineroSacado.getResponse().identity().value() + "\"" + ","
                + "\"Saldo\":" + "\"" + dineroSacado.getResponse().getSaldo().value() + "\"" + ","
                + "\"Tipo\":" + "\"" + dineroSacado.getResponse().getTipo().value() + "\"" + ","
                + "\"Fecha\":" + "\"" + dineroSacado.getResponse().getFecha().value() + "\"" + ","
                + "\"UsuarioId\":" + "\"" + dineroSacado.getResponse().getUid().value() + "\"" + ","
                + "\"BolsilloId\":" + "\"" + dineroSacado.getResponse().getBolsilloId().value() + "}";
        return string;
    }

    public SacarDineroUseCase.Response executedSacarDineroUseCase(SacarDinero command){
        SacarDineroUseCase.Response events = UseCaseHandler.getInstance()
                .syncExecutor(sacarDineroUseCase,new RequestCommand<>(command)).orElseThrow();

        SacarDineroUseCase.Response dineroSacado = events;
        return  dineroSacado;
    }
}

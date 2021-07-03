package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.model.bolsillo.comands.ActualizarBolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.finanzas.useCase.ActualizarBolsilloUseCase;
import co.com.finanzas.useCase.CrearBolsilloUseCase;
import co.com.finanzas.useCase.CrearUsuarioUseCase;
import co.com.finanzas.useCase.TransformacionBolsilloUseCase;
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
        String string = "{" + "\"BolsilloId\":"+ bolsilloCreado.getResponse().getIdPro()+"\""+","
                +"\"Nombre\":"+bolsilloCreado.getResponse().getNombre().value()+"\""+","
                +"\"Saldo disponible\":"+bolsilloCreado.getResponse().getSaldoDisponible().value()+"\""+","
                +"\"UsuarioId\":"+bolsilloCreado.getResponse().getUid().value()+"\""+","
                +"\"¿Es Ahorro?\":"+bolsilloCreado.getResponse().getEsAhorro().value()+"\""+","
                +"\"Porcentaje de ahorro\":"+bolsilloCreado.getResponse().getPorcentajeAhorro().value()+"}";

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

        String string = "{" + "\"BolsilloId\":"+ bolsilloActualizado.getResponse().getIdPro()+"\""+","
                +"\"Nombre\":"+bolsilloActualizado.getResponse().getNombre().value()+"\""+","
                +"\"Saldo disponible\":"+bolsilloActualizado.getResponse().getSaldoDisponible().value()+"\""+","
                +"\"UsuarioId\":"+bolsilloActualizado.getResponse().getUid().value()+"\""+","
                +"\"¿Es Ahorro?\":"+bolsilloActualizado.getResponse().getEsAhorro().value()+"\""+","
                +"\"Porcentaje de ahorro\":"+bolsilloActualizado.getResponse().getPorcentajeAhorro().value()+"}";

        return string;
    }

    public  ActualizarBolsilloUseCase.Response executedActualizarBolsilloUseCase(ActualizarBolsillo command){
        var events = UseCaseHandler.getInstance()
                .syncExecutor(actualizarBolsilloUseCase, new RequestCommand<>(command)).orElseThrow();
        var bolsilloActualizado = events;
        return (ActualizarBolsilloUseCase.Response) bolsilloActualizado;
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
}

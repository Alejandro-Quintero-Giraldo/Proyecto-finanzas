package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.BolsilloBuilder;
import co.com.finanzas.domain.model.bolsillo.comands.CrearAhorroAutomatico;
import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.domain.generic.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.UUID;
import java.util.function.Function;

@Service
public class CrearBolsilloUseCase extends UseCase<RequestCommand<CrearBolsillo>, CrearBolsilloUseCase.Response>{

    @Autowired
    private IBolsilloDataRepository data;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Autowired
    private CrearAhorroAutomaticoUseCase crearAhorroAutomaticoUseCase;

    @Override
    public void executeUseCase(RequestCommand<CrearBolsillo> crearBolsilloRequestCommand) {

        var crearBolsillo = crearBolsilloRequestCommand.getCommand();

        var bolsillo = new Bolsillo(
                crearBolsillo.getBolsilloId(),
                crearBolsillo.getNombre(),
                crearBolsillo.getSaldoDisponible(),
                crearBolsillo.getuId(),
                crearBolsillo.getEsAhorro(),
                crearBolsillo.getPorcentajeAhorro()
        );
        data.save(transformar(bolsillo));
        Boolean ahorroEncontrado = detectarBolsilloAhorro(crearBolsillo.getuId().value());
        if (ahorroEncontrado == Boolean.FALSE){
            String id = new Random().toString();
            CrearAhorroAutomatico crearAhorroAutomatico = new CrearAhorroAutomatico(BolsilloId.of(id), UsuarioId.of(crearBolsillo.getuId().value()));
            executedCrearAhorroUseCase(crearAhorroAutomatico);
        }

        emit().onResponse(new Response(bolsillo));
    }

    public CrearAhorroAutomaticoUseCase.Response executedCrearAhorroUseCase(CrearAhorroAutomatico crearAhorroAutomatico){
        CrearAhorroAutomaticoUseCase.Response events = UseCaseHandler.getInstance()
                .syncExecutor(crearAhorroAutomaticoUseCase, new RequestCommand<>(crearAhorroAutomatico)).orElseThrow();
        CrearAhorroAutomaticoUseCase.Response ahorroCreado = events;
        return  ahorroCreado;
    }


    public Boolean detectarBolsilloAhorro(String uid){
        Iterable<BolsilloData> bolsillos = transformacionBolsilloUseCase.listarPorUid(uid);
        Boolean ahorroEncontrado = Boolean.FALSE;
        for (BolsilloData bolsillo : bolsillos){
            if (bolsillo.getNombre() == "Ahorro") {
                ahorroEncontrado = Boolean.TRUE;
            }
        }
        return ahorroEncontrado;
    }

    public BolsilloData transformar(Bolsillo bolsillo){
        BolsilloData bolsilloData = new BolsilloData(bolsillo.getIdPro(),bolsillo.getNombre().value(), bolsillo.getSaldoDisponible().value(),bolsillo.getUid().value(),bolsillo.getEsAhorro().value(),bolsillo.getPorcentajeAhorro().value());
        return bolsilloData;
    }

    public static class Response implements UseCase.ResponseValues{
        private Bolsillo response;

        public Response(Bolsillo bolsillo){
            this.response = bolsillo;
        }

        public Bolsillo getResponse(){
            return  response;
        }

        public void setResponse(Bolsillo response){
            this.response = response;
        }
    }
}

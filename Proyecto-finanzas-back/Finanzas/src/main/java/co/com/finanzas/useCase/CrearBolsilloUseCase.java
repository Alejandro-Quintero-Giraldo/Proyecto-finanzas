package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.BolsilloBuilder;
import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class CrearBolsilloUseCase extends UseCase<RequestCommand<CrearBolsillo>, CrearBolsilloUseCase.Response>{

    @Autowired
    private IBolsilloDataRepository data;

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
        emit().onResponse(new Response(bolsillo));

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

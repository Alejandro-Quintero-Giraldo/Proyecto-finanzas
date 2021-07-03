package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.IUsuarioDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.ActualizarBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarBolsilloUseCase extends UseCase<RequestCommand<ActualizarBolsillo>, ActualizarBolsilloUseCase.Response> {

    @Autowired
    private IBolsilloDataRepository data;

    @Override
    public void executeUseCase(RequestCommand<ActualizarBolsillo> actualizarBolsilloRequestCommand) {
        var command = actualizarBolsilloRequestCommand.getCommand();
        var bolsillo = new Bolsillo(command.getBolsilloId(), command.getNombre(),command.getSaldoDisponible(), command.getUid(),command.getEsAhorro(),command.getPorcentajeAhorro());
        data.save(transformar(bolsillo));
        emit().onResponse(new Response(bolsillo));
    }

    public BolsilloData transformar(Bolsillo bolsillo){
        BolsilloData bolsilloData = new BolsilloData(bolsillo.identity().value(),bolsillo.getNombre().value(), bolsillo.getSaldoDisponible().value(),bolsillo.getUid().value(),bolsillo.getEsAhorro().value(),bolsillo.getPorcentajeAhorro().value());
        return  bolsilloData;
    }

    public static class Response implements  UseCase.ResponseValues{
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

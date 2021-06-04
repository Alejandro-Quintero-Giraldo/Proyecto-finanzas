package co.com.finanzas.domain.useCase;

import co.com.finanzas.domain.Model.Bolsillo.Bolsillo;
import co.com.finanzas.domain.Model.Bolsillo.comands.EliminarBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class EliminarBolsilloUseCase extends UseCase<RequestCommand<EliminarBolsillo>, ResponseEvents>{
    @Override
    public void executeUseCase(RequestCommand<EliminarBolsillo> eliminarBolsilloRequestCommand ){
        var command = eliminarBolsilloRequestCommand.getCommand();
        var bolsillo =new Bolsillo(
                command.getBolsilloId(),
                command.getNombre(),
                command.getSaldoDisponible(),
                command.getMovimientos(),
                command.getuId(),
                command.getTipoAhorro(),
                command.getPorcentajeAhorro()
        );
        emit().onResponse(new ResponseEvents(bolsillo.getUncommittedChanges()));
    }
}

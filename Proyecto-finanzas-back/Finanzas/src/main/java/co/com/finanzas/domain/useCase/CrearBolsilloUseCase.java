package co.com.finanzas.domain.useCase;

import co.com.finanzas.domain.Model.Bolsillo.Bolsillo;
import co.com.finanzas.domain.Model.Bolsillo.comands.CrearBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CrearBolsilloUseCase extends UseCase<RequestCommand<CrearBolsillo>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearBolsillo> crearBolsilloRequestCommand ){
        var command = crearBolsilloRequestCommand.getCommand();
        var bolsillo = new Bolsillo(
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

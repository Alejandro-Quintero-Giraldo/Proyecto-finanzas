package co.com.finanzas.domain.useCase;

import co.com.finanzas.domain.Model.Bolsillo.comands.CrearBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CrearBolsilloUseCase extends UseCase<RequestCommand<CrearBolsillo>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearBolsillo> command ){

    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.BolsilloBuilder;
import co.com.finanzas.domain.model.bolsillo.comands.EliminarBolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class EliminarBolsilloUseCase extends UseCase<RequestCommand<EliminarBolsillo>, EliminarBolsilloUseCase.Response> {
    @Autowired
    IBolsilloDataRepository iBolsilloRepository;

    @Override
    public void executeUseCase(RequestCommand<EliminarBolsillo> eliminarBolsilloRequestCommand) {
        BolsilloBuilder builder = BolsilloBuilder.unBolsillo();

        var command = eliminarBolsilloRequestCommand.getCommand();


        var bolsillo = builder.withEsEliminado(command.getEsEliminado());

    }


    public class Response implements UseCase.ResponseValues{

    }
}

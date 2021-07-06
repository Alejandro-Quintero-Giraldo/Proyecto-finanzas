package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.MostrarHistoricoMensual;
import co.com.finanzas.domain.model.bolsillo.events.HistoricoMensualMostrado;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
@Service
public class MostrarHistoricoMensualUseCase extends UseCase<RequestCommand<MostrarHistoricoMensual>, MostrarHistoricoMensualUseCase.Response> {

    @Override
    public void executeUseCase(RequestCommand<MostrarHistoricoMensual> mostrarHistoricoMensualRequestCommand) {

        MostrarHistoricoMensual command = mostrarHistoricoMensualRequestCommand.getCommand();

        var ingresosMensuales = command.getIngresosMensuales();

        var egresosMensuales = command.getEgresosMensuales();

        emit().onResponse(new Response());
    }
    public static  class  Response implements UseCase.ResponseValues{
    }
}

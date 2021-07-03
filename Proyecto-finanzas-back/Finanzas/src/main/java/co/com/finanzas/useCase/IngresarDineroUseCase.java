package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.IngresarDinero;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.function.Function;
@Service
public class IngresarDineroUseCase extends UseCase<RequestCommand<IngresarDinero>, IngresarDineroUseCase.Response> {

    @Autowired
    private IMovimientoDataRepository data;

    @Autowired
    private IBolsilloDataRepository dataBolsillo;

    @Override
    public void executeUseCase(RequestCommand<IngresarDinero> command){
        IngresarDinero ingresarDinero = command.getCommand();

        Movimiento movimiento = new Movimiento(
                ingresarDinero.getMovimientoId(),
                ingresarDinero.getTipo(),
                ingresarDinero.getFecha(),
                ingresarDinero.getSaldo(),
                ingresarDinero.getBolsilloId(),
                ingresarDinero.getUid()
                );
        data.save(transformar(movimiento));
        emit().onResponse(new Response(movimiento));
    }

    public MovimientoData transformar(Movimiento movimiento){
        MovimientoData movimientoData = new MovimientoData(movimiento.identity().value(),movimiento.getTipo().value(),movimiento.getSaldo().value(),movimiento.getBolsilloId().value(),movimiento.getUid().value());
        return  movimientoData;
    }

    public static class Response implements UseCase.ResponseValues{
        private Movimiento movimiento;

        public Response(Movimiento movimiento){
            this.movimiento = movimiento;
        }

        public Movimiento getResponse() {
            return movimiento;
        }

        public void setMovimiento(Movimiento movimiento) {
            this.movimiento = movimiento;
        }
    }


}

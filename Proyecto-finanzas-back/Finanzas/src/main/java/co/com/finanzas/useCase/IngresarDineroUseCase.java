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

import java.util.Map;
import java.util.function.Function;
@Service
public class IngresarDineroUseCase extends UseCase<RequestCommand<IngresarDinero>, IngresarDineroUseCase.Response> {

    @Autowired
    private IMovimientoDataRepository data;

    @Autowired
    private IBolsilloDataRepository dataBolsillo;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

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
        MovimientoData movimientoData = transformar(movimiento);
        data.save(movimientoData);
        BolsilloData bolsilloData = aumentarSaldoDisponible(ingresarDinero.getBolsilloId(),ingresarDinero.getSaldo().value(), movimientoData, movimiento.identity().value());
        dataBolsillo.save(bolsilloData);
        emit().onResponse(new Response(movimiento));

    }

    public BolsilloData aumentarSaldoDisponible(BolsilloId bolsilloId, Integer saldo, MovimientoData movimiento, String movimientoId){
        BolsilloData bolsilloData = encontrarBolsillo(bolsilloId);
        Integer saldoDisponible = bolsilloData.getSaldoDisponible();
        Integer nuevoSaldo = saldoDisponible + saldo;
        Map<String, MovimientoData> movimientos = bolsilloData.getMovimientos();

        //if(movimientos.get(null)==null){movimiento.remove(null, null);}
        movimientos.put(movimientoId,movimiento);

        bolsilloData.setMovimientos(movimientos);
        bolsilloData.setSaldoDisponible(nuevoSaldo);
        return  bolsilloData;
    }

    public BolsilloData encontrarBolsillo(BolsilloId bolsilloId){
         return transformacionBolsilloUseCase.listarPorId(bolsilloId.value());
    }

    public MovimientoData transformar(Movimiento movimiento){
        MovimientoData movimientoData = new MovimientoData(movimiento.identity().value(),movimiento.getTipo().value(),movimiento.getSaldo().value(),movimiento.getFecha().value(), movimiento.getBolsilloId().value(),movimiento.getUid().value());
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

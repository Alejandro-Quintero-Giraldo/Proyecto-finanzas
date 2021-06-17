package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.events.DineroSacado;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Document(collation="Bolsillo")
public class Bolsillo extends AggregateEvent<BolsilloId> {
    protected Nombre nombre;
    protected SaldoDisponible saldoDisponible;
    protected Map<MovimientoId, Movimiento> movimientos;
    protected UsuarioId Uid;
    protected EsAhorro esAhorro;
    protected PorcentajeAhorro porcentajeAhorro;
    protected EsEliminado esEliminado;

    private Bolsillo(BolsilloId bolsilloId){
        super(bolsilloId);
        subscribe(new BolsilloChange(this));
    }

    public Bolsillo(BolsilloId bolsilloId, Nombre nombre,SaldoDisponible saldoDisponible ,UsuarioId uid, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        super(bolsilloId);
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.Uid = uid;
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;

        appendChange(new BolsilloCreado( nombre, SaldoDisponible.inicializarSaldo(), new HashMap<>(),uid, esAhorro,porcentajeAhorro)).apply();

    }

    public void eliminarBolsillo(EsEliminado esEliminado){
        this.esEliminado = esEliminado;
        appendChange(new BolsilloEliminado(esEliminado)).apply();
    }

    public void ingresarDinero(Movimiento movimiento){
        this.movimientos.put(movimiento.identity(),movimiento);
        appendChange(new DineroIngresado(movimiento.identity(),movimiento.getTipo(),movimiento.getFecha(),movimiento.getSaldo(),movimiento.getUid())).apply();
    }

    public void sacarDinero(Movimiento movimiento){
        appendChange(new DineroSacado(movimiento.identity(), movimiento.getTipo(), movimiento.getFecha(),movimiento.getSaldo(),movimiento.getUid())).apply();
    }

    public static  Bolsillo from(BolsilloId bolsilloId, List<DomainEvent> events){
        var bolsillo = new Bolsillo(bolsilloId);
        events.forEach(bolsillo::applyEvent);
        return  bolsillo;
    }

}

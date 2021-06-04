package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.Model.Bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.Model.Bolsillo.values.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Bolsillo extends AggregateEvent<BolsilloId> {
    protected Nombre nombre;
    protected SaldoDisponible saldoDisponible;
    protected Map<MovimientoId,Movimiento> movimientos;
    protected UsuarioId Uid;
    protected TipoAhorro tipoAhorro;
    protected PorcentajeAhorro porcentajeAhorro;

    private Bolsillo(BolsilloId bolsilloId){
        super(bolsilloId);
        subscribe(new BolsilloChange(this));
    }

    public Bolsillo(BolsilloId bolsilloId, Nombre nombre, SaldoDisponible saldoDisponible, Map<MovimientoId,Movimiento> movimientos, UsuarioId uid, TipoAhorro tipoAhorro, PorcentajeAhorro porcentajeAhorro) {
        super(bolsilloId);
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.movimientos = movimientos;
        this.Uid = uid;
        this.tipoAhorro = tipoAhorro;
        this.porcentajeAhorro = porcentajeAhorro;

        appendChange(new BolsilloCreado(bolsilloId, nombre, saldoDisponible, movimientos, uid,tipoAhorro,porcentajeAhorro)).apply();
        appendChange(new BolsilloEliminado(bolsilloId, nombre, saldoDisponible, movimientos, uid, tipoAhorro, porcentajeAhorro)).apply();
    }

    public void ingresarNombre(Nombre nombre){
        this.nombre = nombre;
    }

    public void modificarNombre(Nombre nombre){
        this.nombre = nombre;
    }

    public static  Bolsillo from(BolsilloId bolsilloId, List<DomainEvent> events){
        var bolsillo = new Bolsillo(bolsilloId);
        events.forEach(bolsillo::applyEvent);
        return  bolsillo;
    }

}

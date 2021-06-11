package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;

public class BolsilloEliminado extends DomainEvent {
    private final EsEliminado esEliminado;


    public BolsilloEliminado(EsEliminado esEliminado ) {
        super("finanzas.bolsillo.eliminado");
        this.esEliminado = esEliminado;
        aggregateRootId();
    }

    public BolsilloId getBolsilloId() {
        return BolsilloId.of(aggregateRootId());
    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }

    /*public Nombre getNombre() {
        return nombre;
    }

    public SaldoDisponible getSaldoDisponible() {
        return saldoDisponible;
    }

    public Map<MovimientoId, Movimiento> getMovimientos() {
        return movimientos;
    }

    public UsuarioId getuId() {
        return uId;
    }

    public EsAhorro getTipoAhorro() {
        return esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }*/
}

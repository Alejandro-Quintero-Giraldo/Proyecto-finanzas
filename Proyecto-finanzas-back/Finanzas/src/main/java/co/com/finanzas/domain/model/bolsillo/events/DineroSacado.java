package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;

public class DineroSacado extends DomainEvent {
    public final BolsilloId bolsilloId;
    public Nombre nombre;
    public SaldoDisponible saldoDisponible;
    public final Map<MovimientoId, Movimiento> movimientos;
    public final UsuarioId uId;
    public final EsAhorro esAhorro;
    public PorcentajeAhorro porcentajeAhorro;


    public DineroSacado(BolsilloId bolsilloId, Nombre nombre, SaldoDisponible saldoDisponible, Map<MovimientoId, Movimiento> movimientos, UsuarioId uId, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        super("finanzas.dinero.sacado");
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.movimientos = movimientos;
        this.uId = uId;
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

    public Nombre getNombre() {
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
    }
}

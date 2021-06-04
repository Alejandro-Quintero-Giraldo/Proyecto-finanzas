package co.com.finanzas.domain.Model.Bolsillo.events;

import co.com.finanzas.domain.Model.Bolsillo.Movimiento;
import co.com.finanzas.domain.Model.Bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;

import java.util.Map;

public class BolsilloCreado extends DomainEvent {
    public final BolsilloId bolsilloId;
    public Nombre nombre;
    public SaldoDisponible saldoDisponible;
    public final Map<MovimientoId, Movimiento> movimientos;
    public final UsuarioId uId;
    public final TipoAhorro tipoAhorro;
    public PorcentajeAhorro porcentajeAhorro;


    public BolsilloCreado(BolsilloId bolsilloId, Nombre nombre, SaldoDisponible saldoDisponible, Map<MovimientoId, Movimiento> movimientos, UsuarioId uId, TipoAhorro tipoAhorro, PorcentajeAhorro porcentajeAhorro) {
        super("finanzas.bolsillo.creado");
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.movimientos = movimientos;
        this.uId = uId;
        this.tipoAhorro = tipoAhorro;
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

    public TipoAhorro getTipoAhorro() {
        return tipoAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }
}

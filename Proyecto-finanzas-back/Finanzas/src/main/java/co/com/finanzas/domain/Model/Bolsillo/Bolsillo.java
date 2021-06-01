package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.values.*;
import co.com.sofka.domain.generic.AggregateEvent;

import java.util.List;

public class Bolsillo extends AggregateEvent<BolsilloId> {
    public final BolsilloId bolsilloId;
    public final Nombre nombre;
    public final SaldoDisponible saldoDisponible;
    public final List<Movimiento> movimientos;
    public final UsuarioId Uid;
    public final TipoAhorro tipoAhorro;
    public final PorcentajeAhorro porcentajeAhorro;

    public Bolsillo(BolsilloId entityId, BolsilloId bolsilloId, Nombre nombre, SaldoDisponible saldoDisponible, List<Movimiento> movimientos, UsuarioId uid, TipoAhorro tipoAhorro, PorcentajeAhorro porcentajeAhorro) {
        super(entityId);
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.movimientos = movimientos;
        Uid = uid;
        this.tipoAhorro = tipoAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
    }
}

package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.values.*;
import co.com.sofka.domain.generic.Entity;

public class Movimiento extends Entity<MovimientoId> {
    public final Tipo tipo;
    public final Fecha fecha;
    public final Saldo saldo;
    public final BolsilloId bolsilloId;
    private final UsuarioId uid;

    public Movimiento(MovimientoId entityId, Tipo tipo, Fecha fecha, Saldo saldo, BolsilloId bolsilloId, UsuarioId uid) {
        super(entityId);
        this.tipo = tipo;
        this.fecha = fecha;
        this.saldo = saldo;
        this.bolsilloId = bolsilloId;
        this.uid = uid;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

    public UsuarioId getUid() {
        return uid;
    }
}

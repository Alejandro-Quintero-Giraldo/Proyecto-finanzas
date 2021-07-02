package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

public class ActualizarBolsillo implements Command {

    private final BolsilloId bolsilloId;
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final UsuarioId uid;
    private final EsAhorro esAhorro;
    private final PorcentajeAhorro porcentajeAhorro;


    public ActualizarBolsillo(BolsilloId bolsilloId, Nombre nombre, SaldoDisponible saldoDisponible, UsuarioId uid, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.uid = uid;
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

    public UsuarioId getUid() {
        return uid;
    }

    public EsAhorro getEsAhorro() {
        return esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }
}

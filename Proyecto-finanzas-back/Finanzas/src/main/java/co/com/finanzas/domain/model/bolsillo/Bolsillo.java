package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.events.DineroSacado;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.AggregateRoot;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "Bolsillo")
public class Bolsillo extends AggregateRoot<BolsilloId> {

    @Id
    protected String idPro;

    protected Nombre nombre;
    protected SaldoDisponible saldoDisponible;
    protected Map<MovimientoId, Movimiento> movimientos;
    protected UsuarioId Uid;
    protected EsAhorro esAhorro;
    protected PorcentajeAhorro porcentajeAhorro;
    protected EsEliminado esEliminado;

    public Bolsillo(BolsilloId entityId, Nombre nombre,SaldoDisponible saldoDisponible ,UsuarioId uid, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        super(entityId);
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.Uid = uid;
        this.idPro = entityId.value();
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }

    public void EliminarBolsillo(EsEliminado esEliminado){
        this.esEliminado = esEliminado;
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

    public UsuarioId getUid() {
        return Uid;
    }

    public EsAhorro getEsAhorro() {
        return esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }
}

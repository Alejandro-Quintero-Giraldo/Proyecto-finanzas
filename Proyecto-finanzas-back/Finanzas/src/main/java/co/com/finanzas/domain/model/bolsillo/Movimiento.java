package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Movimiento")
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

    public Movimiento crearMovimiento(MovimientoId movimientoId, Tipo tipo, Fecha fecha, Saldo saldo, BolsilloId bolsilloId,UsuarioId uid){
        return new Movimiento(movimientoId,tipo,fecha,saldo,bolsilloId,uid);
    }

    public String mostrarMovimiento() {
        return "Movimiento{" +
                "tipo=" + tipo +
                ", fecha=" + fecha +
                ", saldo=" + saldo +
                ", bolsilloId=" + bolsilloId +
                ", uid=" + uid +
                ", entityId=" + entityId +
                '}';
    }

    public Movimiento modificarMovimiento(MovimientoId movimientoId, Tipo tipo, Fecha fecha, Saldo saldo, BolsilloId bolsilloId,UsuarioId uid){
        return new Movimiento(movimientoId,tipo,fecha,saldo,bolsilloId,uid);
    }

    public String eliminarMovimiento(){
        String eliminarMovimiento = "El movimiento ha sido eliminado";
        return  eliminarMovimiento;
    }
}

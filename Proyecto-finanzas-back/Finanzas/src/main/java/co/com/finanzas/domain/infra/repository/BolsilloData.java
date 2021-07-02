package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Document(collection = "Bolsillo")
public class BolsilloData {

    @Id
    protected String bolsilloId;
    protected String nombre;
    protected Integer saldoDisponible;
    protected Boolean esAhorro;
    protected String uid;
    protected Map<String, Movimiento> movimientos;
    protected Integer porcentajeAhorro;

    public BolsilloData(String bolsilloId, String nombre, Integer saldoDisponible, String uid, Boolean esAhorro, Integer porcentajeAhorro){
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.uid = uid;
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public BolsilloData(){
    }

    public String getBolsilloId() {
        return bolsilloId;
    }

    public void setBolsilloId(String bolsilloId) {
        this.bolsilloId = bolsilloId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Integer saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Boolean getEsAhorro() {
        return esAhorro;
    }

    public void setEsAhorro(Boolean esAhorro) {
        this.esAhorro = esAhorro;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Map<String, Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Integer getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

    public void setPorcentajeAhorro(Integer porcentajeAhorro) {
        this.porcentajeAhorro = porcentajeAhorro;
    }
}
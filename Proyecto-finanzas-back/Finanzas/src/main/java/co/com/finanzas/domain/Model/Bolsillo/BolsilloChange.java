package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.Model.Bolsillo.events.BolsilloEliminado;
import co.com.sofka.domain.generic.EventChange;

public class BolsilloChange extends EventChange {
    public BolsilloChange(Bolsillo bolsillo){
        apply((BolsilloCreado event) ->{
            bolsillo.nombre = event.getNombre();
            bolsillo.saldoDisponible = event.getSaldoDisponible();
            bolsillo.movimientos = event.getMovimientos();
            bolsillo.Uid = event.getuId();
            bolsillo.tipoAhorro = event.getTipoAhorro();
            bolsillo.porcentajeAhorro = event.getPorcentajeAhorro();
        });

        apply((BolsilloEliminado event)->{
            bolsillo.nombre = event.getNombre();
            bolsillo.saldoDisponible = event.getSaldoDisponible();
            bolsillo.movimientos = event.getMovimientos();
            bolsillo.Uid = event.getuId();
            bolsillo.tipoAhorro = event.getTipoAhorro();
            bolsillo.porcentajeAhorro = event.getPorcentajeAhorro();
        });
    }
}

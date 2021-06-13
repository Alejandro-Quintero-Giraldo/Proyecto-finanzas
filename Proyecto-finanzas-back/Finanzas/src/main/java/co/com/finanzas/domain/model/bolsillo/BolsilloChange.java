package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.events.BolsilloCreado;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.events.DineroSacado;
import co.com.sofka.domain.generic.EventChange;

public class BolsilloChange extends EventChange {
        public BolsilloChange(Bolsillo bolsillo){
        apply((BolsilloCreado event) ->{
            if(event.getPorcentajeAhorro().value() < 1 || event.getPorcentajeAhorro().value() > 99) {
                throw new IllegalArgumentException("Debes ingresar un porcentaje de Ahorro entre 1 y 99");
            }
            bolsillo.nombre = event.getNombre();
            bolsillo.saldoDisponible = event.getSaldoDisponible();
            bolsillo.movimientos = event.getMovimientos();
            bolsillo.Uid = event.getuId();
            bolsillo.esAhorro = event.getTipoAhorro();
            bolsillo.porcentajeAhorro = event.getPorcentajeAhorro();
        });

        apply((BolsilloEliminado event)->{
            if (bolsillo.saldoDisponible.value > 0){
                throw  new IllegalArgumentException("No puedes eliminar un bolsillo, si tienes saldo disponible dentro de él");
            }
            /*bolsillo.eliminarBolsillo(event.getBolsilloId(),
                    event.getNombre(),
                    event.getSaldoDisponible(),
                    event.getMovimientos(),
                    event.getuId(),
                    event.getTipoAhorro(),
                    event.getPorcentajeAhorro());*/

            bolsillo.esEliminado = event.getEsEliminado();
        });

        apply((DineroIngresado event)->{
            bolsillo.movimientos.put(event.getMovimientoId(),new Movimiento(
                    event.getMovimientoId(),
                    event.getTipo(),
                    event.getFecha(),
                    event.getSaldo(),
                    event.getBolsilloId(),
                    event.getUid()
                    ));
            bolsillo.saldoDisponible.AumentarSaldo(event.getSaldo().value());

        });

        apply((DineroSacado event)-> {

            if (bolsillo.saldoDisponible.value() < event.getSaldo().value()){
                throw new IllegalArgumentException("ERROR: El valor a sacar supera el saldo disponible");
            }
            bolsillo.movimientos.put(event.getMovimientoId(),new Movimiento(
                    event.getMovimientoId(),
                    event.getTipo(),
                    event.getFecha(),
                    event.getSaldo(),
                    event.getBolsilloId(),
                    event.getUid()
            ));
            bolsillo.saldoDisponible.DisminuirSaldo(event.getSaldo().value());
        });
    }
}

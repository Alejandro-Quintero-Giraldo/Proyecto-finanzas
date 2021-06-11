package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

public class EliminarBolsillo implements Command {
    private final EsEliminado esEliminado;
    private final BolsilloId bolsilloId;
    /*private final UsuarioId uid;
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final EsAhorro esAhorro;*/

    public EliminarBolsillo(BolsilloId bolsilloId) {
        this.bolsilloId = bolsilloId;
        /*this.uid = uid;*/
        this.esEliminado = new EsEliminado(Boolean.FALSE);

    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

/*    public UsuarioId getUid() {
        return uid;
    }*/
}

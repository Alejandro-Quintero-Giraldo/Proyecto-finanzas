package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.values.BolsilloId;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.Identity;

public class Bolsillo extends AggregateEvent<BolsilloId> {

    public Bolsillo(Identity entityId) {
        super(entityId);
    }
}

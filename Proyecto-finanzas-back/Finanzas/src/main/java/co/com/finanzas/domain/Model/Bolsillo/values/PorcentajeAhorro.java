package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

public class PorcentajeAhorro implements ValueObject<Integer> {
    public final Integer value;

    public PorcentajeAhorro(Integer value) {
        this.value = value;
    }

    @Override
    public java.lang.Integer value() {
        return value;
    }
}

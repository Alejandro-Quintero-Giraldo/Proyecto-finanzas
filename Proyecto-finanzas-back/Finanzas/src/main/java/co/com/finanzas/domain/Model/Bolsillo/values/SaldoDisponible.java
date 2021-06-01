package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

public class SaldoDisponible implements ValueObject<Integer> {
    public final Integer value;

    public SaldoDisponible(Integer value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}

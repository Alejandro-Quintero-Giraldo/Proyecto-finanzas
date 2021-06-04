package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class SaldoDisponible implements ValueObject<Integer> {
    public final Integer value;

    public SaldoDisponible() {
        this.value = 0;
    }

    @Override
    public Integer value() {
        return 0;
    }
}

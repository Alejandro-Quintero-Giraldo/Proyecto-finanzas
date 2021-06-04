package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Saldo implements ValueObject<Integer> {
    public final Integer value;

    public Saldo(Integer value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return value;
    }
}

package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

public class Email implements ValueObject<String> {
    public final String value;

    public Email(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}

package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDateTime;

public class Fecha implements ValueObject<LocalDateTime> {
    public final LocalDateTime value;

    public Fecha(LocalDateTime value) {
        this.value = value;
    }

    @Override
    public LocalDateTime value() {
        return value;
    }
}

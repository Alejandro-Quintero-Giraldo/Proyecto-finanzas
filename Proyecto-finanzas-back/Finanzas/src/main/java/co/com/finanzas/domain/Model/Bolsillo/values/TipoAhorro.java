package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

public class TipoAhorro  implements ValueObject<Boolean> {
    public final Boolean value;

    public TipoAhorro(Boolean value) {
        this.value = value;
    }

    @Override
    public Boolean value() {
        return null;
    }
}

package co.com.finanzas.domain.Model.Bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class UsuarioId extends Identity {
    public UsuarioId(String uid) {
        super(uid);
    }

    public UsuarioId() {
    }

    public UsuarioId from(String uid){
        return new UsuarioId(uid);
    }
}

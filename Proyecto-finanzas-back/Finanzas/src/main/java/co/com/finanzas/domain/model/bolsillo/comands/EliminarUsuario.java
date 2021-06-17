package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.sofka.domain.generic.Command;

public class EliminarUsuario implements Command {
    private final UsuarioId uid;
    private final Nombre nombre;
    private final Email email;
    private final EsEliminado esEliminado;


    public EliminarUsuario(UsuarioId uid, Nombre nombre, Email email) {
        this.uid = uid;
        this.nombre = nombre;
        this.email = email;
        this.esEliminado = new EsEliminado(Boolean.TRUE);
    }

    public UsuarioId getUid() {
        return uid;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }
}

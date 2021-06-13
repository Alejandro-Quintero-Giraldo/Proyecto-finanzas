package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.EsEliminado;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.sofka.domain.generic.Entity;

public class Usuario extends Entity<UsuarioId> {
    public final Nombre nombre;
    public final Email email;
    public final EsEliminado esEliminado;

    public Usuario(UsuarioId entityId, Nombre nombre, Email email) {
        super(entityId);
        this.nombre = nombre;
        this.email = email;
        this.esEliminado = new EsEliminado(Boolean.FALSE);
    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }
}

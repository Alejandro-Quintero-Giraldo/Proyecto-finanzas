package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.values.Email;
import co.com.finanzas.domain.Model.Bolsillo.values.UsuarioId;
import co.com.finanzas.domain.Model.Bolsillo.values.Nombre;
import co.com.sofka.domain.generic.Entity;

public class Usuario extends Entity<UsuarioId> {
    public final Nombre nombre;
    public final Email email;

    public Usuario(UsuarioId entityId, Nombre nombre, Email email) {
        super(entityId);
        this.nombre = nombre;
        this.email = email;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }
}

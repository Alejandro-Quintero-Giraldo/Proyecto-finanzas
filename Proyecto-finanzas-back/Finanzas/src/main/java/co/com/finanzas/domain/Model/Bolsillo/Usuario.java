package co.com.finanzas.domain.Model.Bolsillo;

import co.com.finanzas.domain.Model.Bolsillo.values.Email;
import co.com.finanzas.domain.Model.Bolsillo.values.UsuarioId;
import co.com.finanzas.domain.Model.Bolsillo.values.Nombre;
import co.com.sofka.domain.generic.Entity;

public class Usuario extends Entity<UsuarioId> {
    Nombre nombre;
    Email email;

    public Usuario(UsuarioId entityId) {
        super(entityId);
    }
}

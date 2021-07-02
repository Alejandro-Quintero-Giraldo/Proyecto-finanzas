package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.finanzas.useCase.CrearUsuarioUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private CrearUsuarioUseCase crearUsuarioUseCase;

    @PostMapping(value = "api/crearUsuario/{uid}/{nombre}/{email}")
    public String saveUsuario(
            @PathVariable("uid")String uid,
            @PathVariable("nombre")String nombre,
            @PathVariable("email")String email){

        var command = new CrearUsuario(
                UsuarioId.of(uid),
                new Nombre(nombre),
                new Email(email)
        );

        CrearUsuarioUseCase.Response usuarioCreado = executedCrearUsuarioUseCase(command);

        return ("Nombre: "+usuarioCreado.getResponse().getNombre().value()+"\n"+
                "Email: "+usuarioCreado.getResponse().getEmail().value()
        );
    }

    private CrearUsuarioUseCase.Response executedCrearUsuarioUseCase(CrearUsuario command){
        var events = UseCaseHandler.getInstance()
                .syncExecutor(crearUsuarioUseCase, new RequestCommand<>(command)).orElseThrow();

        var usuarioCreado = events;
        return usuarioCreado;
    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IUsuarioDataRepository;
import co.com.finanzas.domain.infra.repository.UsuarioData;
import co.com.finanzas.domain.model.bolsillo.Usuario;
import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioCreado;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class CrearUsuarioUseCase extends UseCase<RequestCommand<CrearUsuario>, CrearUsuarioUseCase.Response> {

    @Autowired
    private IUsuarioDataRepository iUsuarioRepository;

    @Override
    public void executeUseCase(RequestCommand<CrearUsuario> crearUsuarioRequestCommand) {

        var crearUsuario = crearUsuarioRequestCommand.getCommand();

        var usuario = new Usuario(crearUsuario.getUid(), crearUsuario.getNombre(), crearUsuario.getEmail(), crearUsuario.getEsEliminado());

        iUsuarioRepository.save(transformar(usuario));
        emit().onResponse(new Response(usuario));
    }

    public UsuarioData transformar(Usuario usuario){
        UsuarioData usuarioData = new UsuarioData(usuario.identity().value(),usuario.getNombre().value(), usuario.getEmail().value());
        return usuarioData;
    }

    public static class Response implements UseCase.ResponseValues{
        private Usuario response;

        public Response(Usuario usuario){
            this.response = usuario;
        }

        public Usuario getResponse(){
            return response;
        }

        public  void setResponse(){
            this.response = response;
        }
    }
}
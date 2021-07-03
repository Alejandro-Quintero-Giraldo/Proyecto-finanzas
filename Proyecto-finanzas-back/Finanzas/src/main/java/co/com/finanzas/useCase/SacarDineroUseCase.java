package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.model.bolsillo.comands.SacarDinero;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SacarDineroUseCase extends UseCase<RequestCommand<SacarDinero>, SacarDineroUseCase.Response>{

    @Autowired
    IMovimientoDataRepository iMovimientoRepository;


    @Override
    public void executeUseCase(RequestCommand<SacarDinero> sacarDineroRequestCommand) {

    }

    public static class Response implements UseCase.ResponseValues{

    }
}


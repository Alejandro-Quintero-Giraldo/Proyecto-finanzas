package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformacionMovimientoUseCase {

    @Autowired
    private IMovimientoDataRepository data;

    public MovimientoData transformar(Movimiento movimiento){
        MovimientoData movimientoData = new MovimientoData(movimiento.identity().value(),movimiento.getTipo().value(),movimiento.getSaldo().value(),movimiento.getBolsilloId().value(),movimiento.getUid().value());
        return  movimientoData;
    }

    public Iterable<MovimientoData> listar(){
        return data.findAll();
    }

    public MovimientoData listarPorId(String id){
        return data.findById(id).orElseThrow(RuntimeException::new);
    }
}

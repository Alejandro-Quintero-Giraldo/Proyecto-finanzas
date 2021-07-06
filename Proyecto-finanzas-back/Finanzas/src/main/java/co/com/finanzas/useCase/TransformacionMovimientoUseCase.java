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

    public Iterable<MovimientoData> listarPorBolsilloId(String bolsilloId){
        return data.findByBolsilloId(bolsilloId);
    }

    public String eliminarMovimientosPorBolsillo(Iterable<MovimientoData> movimientos){
        try {
            data.deleteAll(movimientos);
            return "Se eliminaron los movimientos del bolsillo con éxito";
        } catch (Exception e){
            return "Ocurrió un error. Por favor inténtelo nuevamente";
        }
    }
}

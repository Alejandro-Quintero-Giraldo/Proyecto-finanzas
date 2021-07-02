package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import org.springframework.data.repository.CrudRepository;

public interface IMovimientoRepository extends CrudRepository<Movimiento, MovimientoId> {
}

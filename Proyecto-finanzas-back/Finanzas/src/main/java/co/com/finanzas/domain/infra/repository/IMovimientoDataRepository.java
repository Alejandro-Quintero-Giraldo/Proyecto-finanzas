package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import org.springframework.data.repository.CrudRepository;

public interface IMovimientoDataRepository extends CrudRepository<MovimientoData, String> {
    Iterable<MovimientoData> findByBolsilloId(String bolsilloId);
}
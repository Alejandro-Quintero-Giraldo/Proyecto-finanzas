package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBolsilloRepository extends CrudRepository<Bolsillo, String> {

}

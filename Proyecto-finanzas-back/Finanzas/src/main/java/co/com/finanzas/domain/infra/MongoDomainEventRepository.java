package co.com.finanzas.domain.infra;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.events.BolsilloEliminado;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoDomainEventRepository implements DomainEventRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<DomainEvent> getEventsBy(String s) {
        return null;
    }

    @Override
    public List<DomainEvent> getEventsBy(String s, String s1) {
        return mongoTemplate.find(Query.query(Criteria.where("esEliminado").is(Boolean.TRUE)));
    }
}

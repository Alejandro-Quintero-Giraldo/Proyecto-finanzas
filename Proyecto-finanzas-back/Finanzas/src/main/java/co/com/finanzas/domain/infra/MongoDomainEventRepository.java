package co.com.finanzas.domain.infra;

import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoDomainEventRepository implements DomainEventRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<DomainEvent> getEventsBy(String s) {
        return null;
    }

    @Override
    public List<DomainEvent> getEventsBy( String bolsilloId,String nombreAgregado) {
        var query = new Query(Criteria.where("bolsilloId").is(bolsilloId));

        var find = mongoTemplate.find(query, DomainEvent.class,nombreAgregado);

        return  find.stream().sorted(Comparator.comparing(domainEvent -> domainEvent.when)).collect(Collectors.toList());

    }


/*
    @Override
    public List<DomainEvent> getEventsBy(String nombreAgregado, String bolsilloId) {
        return mongoTemplate.find(
                Query.query(Criteria.where("_id").is(bolsilloId)), DomainEvent.class,nombreAgregado
        );
    }*/
}

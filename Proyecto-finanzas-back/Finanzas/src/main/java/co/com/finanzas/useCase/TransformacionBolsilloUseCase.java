package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformacionBolsilloUseCase {

    @Autowired
    private IBolsilloDataRepository data;

    public BolsilloData transformar(Bolsillo bolsillo){
        BolsilloData bolsilloData = new BolsilloData(bolsillo.identity().value(),bolsillo.getNombre().value(), bolsillo.getSaldoDisponible().value(),bolsillo.getUid().value(),bolsillo.getEsAhorro().value(),bolsillo.getPorcentajeAhorro().value());
        return bolsilloData;
    }

    public Iterable<BolsilloData> listar(){
        return data.findAll();
    }

    public BolsilloData listarPorId(String id){
        return  data.findById(id).orElseThrow(RuntimeException::new);
    }

    public String eliminar(String id){
        try{
            data.deleteById(id);
            return  "Se eliminó el bolsillo con éxito";
        } catch (Exception e){
            return "No fue posible eliminar el bolsillo";
        }
    }
}

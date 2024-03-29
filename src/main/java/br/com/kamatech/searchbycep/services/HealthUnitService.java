package br.com.kamatech.searchbycep.services;

import br.com.kamatech.searchbycep.exceptions.FinalSmallerThenInitException;
import br.com.kamatech.searchbycep.exceptions.RangeCepException;
import br.com.kamatech.searchbycep.models.HealthUnit;
import br.com.kamatech.searchbycep.repositories.HealthUnitRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*
* Camada Service de Unidades de Saúde
*/
@Service
public class HealthUnitService {

  @Autowired
  HealthUnitRepository repository;

  // cria uma nova Unidade de Saúde
  public ResponseEntity<HealthUnit> save(HealthUnit healthUnit) {

    // se a faixa final do CEP for menor que a faixa inicial lança o erro
    if (healthUnit.getFinalCEP() < healthUnit.getInitCEP()) {
      throw new FinalSmallerThenInitException();
    }

    // chama método que busca unidades que estejam dentro da faixa inicial a final,
    // se existir lança erro de faixa já utilizada e se não existir salva a nova unidade
    List<HealthUnit> units = this.getEntitiesFromRangeCEP(healthUnit.getInitCEP(),
        healthUnit.getFinalCEP());

    if (units.isEmpty()) {
      HealthUnit saved = this.repository.save(healthUnit);
      return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    throw new RangeCepException();
  }

  // método para buscar unidades que estejam dentro da faixa inicial e final
  // utilizada para salvar uma nova unidade, alterar uma unidade existente e
  // atender a consulta do usuário
  public List<HealthUnit> getEntitiesFromRangeCEP(Integer initCEP, Integer finalCEP) {
    return repository.findHealthUnitsByCEPRange(initCEP, finalCEP);
  }

  public List<HealthUnit> findAll() {
    return repository.findAll();
  }

  public HealthUnit findByRangeCEP(Integer cep) {
    HealthUnit unit = repository.findByRangeCEP(cep);
    if (unit == null) {
      throw new RuntimeException("Nenhuma unidade atende o CEP informado");
    }
    return unit;
  }
}

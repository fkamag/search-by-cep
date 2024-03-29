package br.com.kamatech.searchbycep.controllers;

import br.com.kamatech.searchbycep.models.HealthUnit;
import br.com.kamatech.searchbycep.services.HealthUnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Camada Controller de Unidades de Saúde
*/
@RestController
@RequestMapping("/admin/busca-por-cep")
public class HealthUnitController {

  @Autowired
  HealthUnitService service;

  // Rota para listar todas as unidades de saúde cadastradas
  @GetMapping
  public ResponseEntity<List<HealthUnit>> findAll() {
    List<HealthUnit> units = service.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(units);
  }

  // Rota para criar uma nova unidade de saúde
  @PostMapping
  public ResponseEntity<HealthUnit> save(@RequestBody HealthUnit healthUnit) {
    HealthUnit unit = service.save(healthUnit).getBody();
    return ResponseEntity.status(HttpStatus.CREATED).body(unit);
  }

}

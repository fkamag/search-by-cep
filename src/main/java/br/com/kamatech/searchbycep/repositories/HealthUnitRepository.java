package br.com.kamatech.searchbycep.repositories;

import br.com.kamatech.searchbycep.models.HealthUnit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HealthUnitRepository extends JpaRepository<HealthUnit, String> {

  @Query("SELECT h FROM HealthUnit h WHERE (h.initCEP <= :finalCEP AND h.finalCEP >= :initCEP) OR (h.initCEP >= :initCEP AND h.finalCEP <= :finalCEP)")
  List<HealthUnit> findHealthUnitsByCEPRange(Integer initCEP, Integer finalCEP);

}

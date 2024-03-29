package br.com.kamatech.searchbycep.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_health_unit")
public class HealthUnit {

  @Id
  private String cnes;
  private String nameUnit;
  private Integer initCEP;
  private Integer finalCEP;

  public String getCnes() {
    return cnes;
  }

  public void setCnes(String cnes) {
    this.cnes = cnes;
  }

  public String getNameUnit() {
    return nameUnit;
  }

  public void setNameUnit(String nameUnit) {
    this.nameUnit = nameUnit;
  }

  public Integer getInitCEP() {
    return initCEP;
  }

  public void setInitCEP(Integer initCEP) {
    this.initCEP = initCEP;
  }

  public Integer getFinalCEP() {
    return finalCEP;
  }

  public void setFinalCEP(Integer finalCEP) {
    this.finalCEP = finalCEP;
  }
}

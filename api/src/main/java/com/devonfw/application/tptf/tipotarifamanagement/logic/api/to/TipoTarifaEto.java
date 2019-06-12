package com.devonfw.application.tptf.tipotarifamanagement.logic.api.to;

import com.devonfw.application.tptf.tipotarifamanagement.common.api.TipoTarifa;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * Entity transport object of TipoTarifa
 */
public class TipoTarifaEto extends AbstractEto implements TipoTarifa {

  private static final long serialVersionUID = 1L;

  private String TipodeTarifa;

  private String description;

  @Override
  public String getTipodeTarifa() {

    return TipodeTarifa;
  }

  @Override
  public void setTipodeTarifa(String TipodeTarifa) {

    this.TipodeTarifa = TipodeTarifa;
  }

  @Override
  public String getDescription() {

    return description;
  }

  @Override
  public void setDescription(String description) {

    this.description = description;
  }

  @Override
  public int hashCode() {

    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.TipodeTarifa == null) ? 0 : this.TipodeTarifa.hashCode());
    result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    // class check will be done by super type EntityTo!
    if (!super.equals(obj)) {
      return false;
    }
    TipoTarifaEto other = (TipoTarifaEto) obj;
    if (this.TipodeTarifa == null) {
      if (other.TipodeTarifa != null) {
        return false;
      }
    } else if (!this.TipodeTarifa.equals(other.TipodeTarifa)) {
      return false;
    }
    if (this.description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!this.description.equals(other.description)) {
      return false;
    }
    return true;
  }

}

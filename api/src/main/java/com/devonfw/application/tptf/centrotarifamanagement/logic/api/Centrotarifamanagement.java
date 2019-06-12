package com.devonfw.application.tptf.centrotarifamanagement.logic.api;

import com.devonfw.application.tptf.centrotarifamanagement.logic.api.usecase.UcFindCentroTarifa;
import com.devonfw.application.tptf.centrotarifamanagement.logic.api.usecase.UcManageCentroTarifa;

/**
 * Interface for Centrotarifamanagement component.
 */
public interface Centrotarifamanagement extends UcFindCentroTarifa, UcManageCentroTarifa {

  /**
   * @param centrotarifa
   * @return
   */
  // CentroTarifaEto updateCentroTarifa(CentroTarifaEto centrotarifa);

}

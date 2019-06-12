package com.devonfw.application.tptf.centrotarifamanagement.logic.api.usecase;

import java.math.BigInteger;

import com.devonfw.application.tptf.centrotarifamanagement.logic.api.to.CentroTarifaEto;

/**
 * Interface of UcManageCentroTarifa to centralize documentation and signatures of methods.
 */
public interface UcManageCentroTarifa {

  /**
   * Deletes a centroTarifa from the database by its id 'centroTarifaId'.
   *
   * @param centroTarifaId Id of the centroTarifa to delete
   * @return boolean <code>true</code> if the centroTarifa can be deleted, <code>false</code> otherwise
   */
  boolean deleteCentroTarifa(long centroTarifaId);

  boolean deletecentroassignment(BigInteger centro);

  /**
   * Saves a centroTarifa and store it in the database.
   *
   * @param centroTarifa the {@link CentroTarifaEto} to create.
   * @return the new {@link CentroTarifaEto} that has been saved with ID and version.
   */
  CentroTarifaEto saveCentroTarifa(CentroTarifaEto centroTarifa);

  /**
   * @param centrotarifa
   * @return
   */
  CentroTarifaEto updateCentroTarifa(CentroTarifaEto centrotarifa);

}

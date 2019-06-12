package com.devonfw.application.tptf.centrotarifamanagement.logic.impl.usecase;

import java.math.BigInteger;
import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.tptf.centrotarifamanagement.dataaccess.api.CentroTarifaEntity;
import com.devonfw.application.tptf.centrotarifamanagement.logic.api.to.CentroTarifaEto;
import com.devonfw.application.tptf.centrotarifamanagement.logic.api.usecase.UcManageCentroTarifa;
import com.devonfw.application.tptf.centrotarifamanagement.logic.base.usecase.AbstractCentroTarifaUc;

/**
 * Use case implementation for modifying and deleting CentroTarifas
 */
@Named
@Validated
@Transactional
public class UcManageCentroTarifaImpl extends AbstractCentroTarifaUc implements UcManageCentroTarifa {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageCentroTarifaImpl.class);

  @Override
  public boolean deleteCentroTarifa(long centroTarifaId) {

    CentroTarifaEntity centroTarifa = getCentroTarifaRepository().find(centroTarifaId);
    getCentroTarifaRepository().delete(centroTarifa);
    LOG.debug("The centroTarifa with id '{}' has been deleted.", centroTarifaId);
    return true;
  }

  @Override
  public CentroTarifaEto saveCentroTarifa(CentroTarifaEto centroTarifa) {

    Objects.requireNonNull(centroTarifa, "centroTarifa");

    CentroTarifaEntity centroTarifaEntity = getBeanMapper().map(centroTarifa, CentroTarifaEntity.class);

    // initialize, validate centroTarifaEntity here if necessary
    CentroTarifaEntity resultEntity = getCentroTarifaRepository().save(centroTarifaEntity);
    LOG.debug("CentroTarifa with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, CentroTarifaEto.class);
  }

  @Override
  public CentroTarifaEto updateCentroTarifa(CentroTarifaEto centrotarifa) {

    Objects.requireNonNull(centrotarifa, "centroTarifa");
    CentroTarifaEntity resultcentro = getCentroTarifaRepository().find(centrotarifa.getId());
    CentroTarifaEntity centroTarifaEntity = null;
    if (resultcentro != null) {
      centroTarifaEntity = getBeanMapper().map(centrotarifa, CentroTarifaEntity.class);
      centroTarifaEntity = getCentroTarifaRepository().save(centroTarifaEntity);
      LOG.debug("CentroTarifa with id '{}' has been created.", centroTarifaEntity.getId());
    }

    return getBeanMapper().map(centroTarifaEntity, CentroTarifaEto.class);
  }

  @Override
  public boolean deletecentroassignment(BigInteger centro) {

    // //CentroTarifaEntity entity = getCentroTarifaRepository().findbyCentros(centro);
    // getCentroTarifaRepository().delete(entity);
    // LOG.debug("The centroTarifa with id '{}' has been deleted.", entity.getId());
    return true;
  }
}

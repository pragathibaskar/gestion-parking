package com.devonfw.application.tptf.tipotarifamanagement.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.tptf.tipotarifamanagement.dataaccess.api.TipoTarifaEntity;
import com.devonfw.application.tptf.tipotarifamanagement.logic.api.to.TipoTarifaEto;
import com.devonfw.application.tptf.tipotarifamanagement.logic.api.usecase.UcManageTipoTarifa;
import com.devonfw.application.tptf.tipotarifamanagement.logic.base.usecase.AbstractTipoTarifaUc;

/**
 * Use case implementation for modifying and deleting TipoTarifas
 */
@Named
@Validated
@Transactional
public class UcManageTipoTarifaImpl extends AbstractTipoTarifaUc implements UcManageTipoTarifa {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageTipoTarifaImpl.class);

  @Override
  public boolean deleteTipoTarifa(long tipoTarifaId) {

    TipoTarifaEntity tipoTarifa = getTipoTarifaRepository().find(tipoTarifaId);
    getTipoTarifaRepository().delete(tipoTarifa);
    LOG.debug("The tipoTarifa with id '{}' has been deleted.", tipoTarifaId);
    return true;
  }

  @Override
  public TipoTarifaEto saveTipoTarifa(TipoTarifaEto tipoTarifa) {

    Objects.requireNonNull(tipoTarifa, "tipoTarifa");

    TipoTarifaEntity tipoTarifaEntity = getBeanMapper().map(tipoTarifa, TipoTarifaEntity.class);

    // initialize, validate tipoTarifaEntity here if necessary
    TipoTarifaEntity resultEntity = getTipoTarifaRepository().save(tipoTarifaEntity);
    LOG.debug("TipoTarifa with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, TipoTarifaEto.class);
  }

  @Override
  public TipoTarifaEto updateTipoTarifa(String tipodeTarifa, String description) {

    // TipoTarifaEto tipotarifa = new TipoTarifaEto();
    // TipoTarifaEntity tipoTarifaEntity1 = getBeanMapper().map(tipotarifa, TipoTarifaEntity.class);
    TipoTarifaEntity findByTipodeTarifa = getTipoTarifaRepository().findByTipodeTarifa(tipodeTarifa);
    TipoTarifaEntity resultEntity = null;
    if (findByTipodeTarifa != null) {
      findByTipodeTarifa.setDescription(description);
      resultEntity = getTipoTarifaRepository().save(findByTipodeTarifa);
      LOG.debug("TipoTarifa with id '{}' has been updated.", resultEntity.getId());

    } else {

      LOG.error("no data found");

    }
    return getBeanMapper().map(resultEntity, TipoTarifaEto.class);
  }

}

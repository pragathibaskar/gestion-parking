package com.devonfw.application.tptf.tipotarifamanagement.logic.impl.usecase;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.application.tptf.centrotarifamanagement.logic.api.to.CentroTarifaEto;
import com.devonfw.application.tptf.centrotarifamanagement.logic.impl.usecase.UcFindCentroTarifaImpl;
import com.devonfw.application.tptf.general.logic.api.to.CustomMaestraCentroEto;
import com.devonfw.application.tptf.general.logic.api.to.CustomParametroCentroEto;
import com.devonfw.application.tptf.tipotarifamanagement.dataaccess.api.TipoTarifaEntity;
import com.devonfw.application.tptf.tipotarifamanagement.logic.api.to.TipoTarifaEto;
import com.devonfw.application.tptf.tipotarifamanagement.logic.api.to.TipoTarifaSearchCriteriaTo;
import com.devonfw.application.tptf.tipotarifamanagement.logic.api.usecase.UcFindTipoTarifa;
import com.devonfw.application.tptf.tipotarifamanagement.logic.base.usecase.AbstractTipoTarifaUc;

/**
 * Use case implementation for searching, filtering and getting TipoTarifas
 */
@Named
@Validated
@Transactional
public class UcFindTipoTarifaImpl extends AbstractTipoTarifaUc implements UcFindTipoTarifa {

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindTipoTarifaImpl.class);

  UcFindCentroTarifaImpl impl = new UcFindCentroTarifaImpl();

  @Override
  public TipoTarifaEto findTipoTarifa(long id) {

    LOG.debug("Get TipoTarifa with id {} from database.", id);
    Optional<TipoTarifaEntity> foundEntity = getTipoTarifaRepository().findById(id);
    if (foundEntity.isPresent())
      return getBeanMapper().map(foundEntity.get(), TipoTarifaEto.class);
    else
      return null;
  }

  @Override
  public Page<TipoTarifaEto> findTipoTarifas(TipoTarifaSearchCriteriaTo criteria) {

    Page<TipoTarifaEntity> tipotarifas = getTipoTarifaRepository().findByCriteria(criteria);
    return mapPaginatedEntityList(tipotarifas, TipoTarifaEto.class);
  }

  @Override
  public TipoTarifaEto getByTipodeTarifa(String tipodeTarifa) {

    TipoTarifaEntity tarifa = getTipoTarifaRepository().findByTipodeTarifa(tipodeTarifa);
    return getBeanMapper().map(tarifa, TipoTarifaEto.class);

  }

  @Override
  public List<TipoTarifaEto> findAll() {

    List<TipoTarifaEntity> list = getTipoTarifaRepository().findAll();
    LOG.debug("The parametroTarifa  '{}' has been fetched.", list);
    return getBeanMapper().mapList(list, TipoTarifaEto.class);

  }

  @Override
  public List<CentroTarifaEto> listCentroTarifa(TipoTarifaEto tipotarifa) {

    TipoTarifaEntity entity = getTipoTarifaRepository().findByTipodeTarifaAndDescription(tipotarifa.getTipodeTarifa(),
        tipotarifa.getDescription());
    return getBeanMapper().mapList(entity.getCentrotarifa(), CentroTarifaEto.class);
  }

  @Override
  public List<CustomMaestraCentroEto> listCustomTarifa(TipoTarifaEto tipotarifa) {

    TipoTarifaEntity entity = getTipoTarifaRepository().findByTipodeTarifaAndDescription(tipotarifa.getTipodeTarifa(),
        tipotarifa.getDescription());
    List<CustomMaestraCentroEto> customList = new ArrayList();
    entity.getCentrotarifa().forEach(centroentity -> {
      CustomMaestraCentroEto centrEto = new CustomMaestraCentroEto();
      centrEto.setCentro(centroentity.getCentros().getCentro());
      centrEto.setDescripcion(centroentity.getCentros().getDescripcion());
      centrEto.setFechaDesdeVigencia(centroentity.getFechaDesdeVigencia());
      customList.add(centrEto);
    });
    return customList;
  }

  @Override
  public List<CustomMaestraCentroEto> listCustomSearch(String tipodeTarifa, String description, BigInteger centro,
      String descripcion) {

    TipoTarifaEntity entity = getTipoTarifaRepository().findByTipodeTarifaAndDescription(tipodeTarifa, description);
    List<CustomMaestraCentroEto> customList = new ArrayList();
    entity.getCentrotarifa().forEach(centroentity -> {
      CustomMaestraCentroEto centrEto = new CustomMaestraCentroEto();
      if ((centroentity.getCentros().getCentro()).equals(centro)
          && (centroentity.getCentros().getDescripcion()).equals(descripcion)) {
        centrEto.setCentro(centroentity.getCentros().getCentro());
        centrEto.setDescripcion(centroentity.getCentros().getDescripcion());
        centrEto.setFechaDesdeVigencia(centroentity.getFechaDesdeVigencia());
        customList.add(centrEto);
      }
    });
    return customList;
  }

  @Override
  public List<CustomParametroCentroEto> listCentroParam(String tipodeTarifa, String description,
      BigDecimal fechaDesdeVigencia) {

    TipoTarifaEntity entity = getTipoTarifaRepository().findByTipodeTarifaAndDescription(tipodeTarifa, description);
    List<CustomParametroCentroEto> customList = new ArrayList();
    entity.getCentrotarifa().forEach(centroentity -> {
      CustomParametroCentroEto eto = new CustomParametroCentroEto();
      if ((centroentity.getFechaDesdeVigencia()).equals(fechaDesdeVigencia)) {
        eto.setCentro(centroentity.getCentros().getCentro());
        eto.setDescripcion(centroentity.getCentros().getDescripcion());
        eto.setFechaDesdeVigencia(centroentity.getFechaDesdeVigencia());
        eto.setFechaFin(getTipoTarifaRepository().getFin(centroentity.getCentros().getCentro(), fechaDesdeVigencia));
        customList.add(eto);
      }
    });
    return customList;
  }

  @Override
  public List<CustomParametroCentroEto> searchCentroParam(String tipodeTarifa, String description,
      BigDecimal fechaDesdeVigencia, BigInteger centro, String descripcion) {

    TipoTarifaEntity entity = getTipoTarifaRepository().findByTipodeTarifaAndDescription(tipodeTarifa, description);
    List<CustomParametroCentroEto> customList = new ArrayList();
    entity.getCentrotarifa().forEach(centroentity -> {
      CustomParametroCentroEto eto = new CustomParametroCentroEto();
      if ((centroentity.getFechaDesdeVigencia()).equals(fechaDesdeVigencia)
          && (centroentity.getCentros().getCentro()).equals(centro)
          && (centroentity.getCentros().getDescripcion()).equals(descripcion)) {
        eto.setCentro(centroentity.getCentros().getCentro());
        eto.setDescripcion(centroentity.getCentros().getDescripcion());
        eto.setFechaDesdeVigencia(centroentity.getFechaDesdeVigencia());
        eto.setFechaFin(getTipoTarifaRepository().getFin(centroentity.getCentros().getCentro(), fechaDesdeVigencia));
        customList.add(eto);
      }
    });
    return customList;
  }

}

package com.devonfw.application.tptf.general.logic.api.to;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * TODO pragat This type ...
 *
 * @since v1
 */
public class CustomParametroCentroEto {

  private static final long serialVersionUID = 1L;

  private BigInteger centro;

  private String descripcion;

  private BigDecimal fechaDesdeVigencia;

  // @Formula("(SELECT COALESCE(MIN(c.fechaDesdeVigencia),0) FROM MUCETAT c WHERE c.codcen = codcen AND c.fdesvi >
  // fdesvi)")
  private BigDecimal fechaFin;

  /**
   * @return centro
   */
  public BigInteger getCentro() {

    return this.centro;
  }

  /**
   * @param centro new value of {@link #getcentro}.
   */
  public void setCentro(BigInteger centro) {

    this.centro = centro;
  }

  /**
   * @return descripcion
   */
  public String getDescripcion() {

    return this.descripcion;
  }

  /**
   * @param descripcion new value of {@link #getdescripcion}.
   */
  public void setDescripcion(String descripcion) {

    this.descripcion = descripcion;
  }

  /**
   * @return fechaDesdeVigencia
   */
  public BigDecimal getFechaDesdeVigencia() {

    return this.fechaDesdeVigencia;
  }

  /**
   * @param fechaDesdeVigencia new value of {@link #getfechaDesdeVigencia}.
   */
  public void setFechaDesdeVigencia(BigDecimal fechaDesdeVigencia) {

    this.fechaDesdeVigencia = fechaDesdeVigencia;
  }

  /**
   * @return fechaFin
   */
  public BigDecimal getFechaFin() {

    return this.fechaFin;
  }

  /**
   * @param fechaFin new value of {@link #getfechaFin}.
   */
  public void setFechaFin(BigDecimal fechaFin) {

    this.fechaFin = fechaFin;
  }

}

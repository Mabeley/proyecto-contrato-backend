package com.upc.contract.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_TIPO_CONTRACTO")
public class TipoContrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_TIPO")
	private Long idTipo;
	@Column(name = "CNOMBRE")
	private String nombre;
	@Column(name = "CDETALLE")
	private String detalle;

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}

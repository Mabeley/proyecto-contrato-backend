package com.upc.contract.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ESTADO")
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_ESTADO")
	private Long idEstado;
	@Column(name = "CNOMBRE")
	private String nombre;
	@Column(name = "CDETALLE")
	private String detalle;
	@Column(name = "CTIPO")
	private String tipo;

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

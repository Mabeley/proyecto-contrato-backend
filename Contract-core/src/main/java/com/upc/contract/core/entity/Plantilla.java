package com.upc.contract.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PLANTILLA")
public class Plantilla {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_PLANTILLA")
	private Long idPlantilla;
	@Column(name = "CNOMBRE")
	private String nombre;
	@Column(name = "BPLANTILLA")
	private byte[] plantilla;
	@Column(name = "NID_TIPO")
	private Long idTipo;

	public Long getIdPlantilla() {
		return idPlantilla;
	}

	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(byte[] plantilla) {
		this.plantilla = plantilla;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

}

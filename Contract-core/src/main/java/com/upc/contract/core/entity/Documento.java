package com.upc.contract.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_DOCUMENTO")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_DOCUMENTO")
	private Long idDocumento;
	@Column(name = "CDNI")
	private String dni;
	@Column(name = "CCARGO")
	private String cargo;
	@Column(name = "CJEFE")
	private String jefe;
	@Column(name = "CAREA")
	private String area;
	@Column(name = "NSUELDO")
	private double sueldo;
	@Column(name = "NID_ESTADO")
	private Long idEstado;
	@Column(name = "NID_ENVIO")
	private Long idEnvio;

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(Long idEnvio) {
		this.idEnvio = idEnvio;
	}

}

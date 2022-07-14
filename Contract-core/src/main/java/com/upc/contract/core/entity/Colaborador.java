package com.upc.contract.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COLABORADOR")
public class Colaborador {
	@Id
	@Column(name = "CDNI")
	private String dni;
	@Column(name = "CNOMBRE_APELLIDO")
	private String nombreApellido;
	@Column(name = "CCORREO")
	private String correo;
	@Column(name = "CCELULAR")
	private String celular;
	@Column(name = "CCLAVE")
	private String clave;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}

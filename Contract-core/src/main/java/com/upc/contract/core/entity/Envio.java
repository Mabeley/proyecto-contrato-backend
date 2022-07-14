package com.upc.contract.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_ENVIO")
public class Envio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_ENVIO")
	private Long idEnvio;
	@Column(name = "BDATOS",columnDefinition = "BLOB" )
	@Lob
	private byte[] datos;
	@Column(name = "DFECHA")
	private Date fechaRegistro;
	@Column(name = "NCANTIDAD")
	private int cantidad;
	@Column(name = "NID_TIPO")
	private Long idTipo;
	@Column(name = "NID_USUARIO")
	private Long idUsuario;
	@Column(name = "NID_ESTADO")
	private Long idEstado;

	public Long getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(Long idEnvio) {
		this.idEnvio = idEnvio;
	}

	public byte[] getDatos() {
		return datos;
	}

	public void setDatos(byte[] datos) {
		this.datos = datos;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

}

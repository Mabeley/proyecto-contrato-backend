package com.upc.contract.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CONTROL")
public class Control {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NID_CONTROL")
	private Long idControl;
	@Column(name = "CDISPOSITIVO")
	private String dispositivo;
	@Column(name = "DFECHA_REVISION")
	private Date fechaRevision;
	@Column(name = "NID_DOCUMENTO")
	private Long idDocumento;

	public Long getIdControl() {
		return idControl;
	}

	public void setIdControl(Long idControl) {
		this.idControl = idControl;
	}

	public String getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	public Date getFechaRevision() {
		return fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

}

package com.upc.util.service;

import java.util.Map;

public interface UtilidadService {
	public String enviarCorreo(Map<String, Object> datos) throws Exception;
	public String enviarCorreoAdjunto(Map<String, Object> datos,byte[] adjunto) throws Exception;
	public byte[] firmarPdf(byte[] data) throws Exception;
}

package com.upc.util.jms;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.upc.util.service.UtilidadService;

@Component
public class JmsNotificacion {
	
	@Autowired
	private UtilidadService utilService;
	
	@JmsListener(destination = "${jms.cola.notificacion}")
	public void notificar(String mensaje) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			System.out.println(mensaje);
			@SuppressWarnings("unchecked")
			Map<String, Object> map = mapper.readValue(mensaje, Map.class);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            Phrase header = new Phrase("CONTRATO DIGITAL POR TIEMPO DE SERVICIO\n\n");
            document.add(header);
            
            Phrase body = new Phrase("El empleador UPC con ruc 87874574812 ubicado en Monterrico y el contratista "+map.get("nombre")+" con DNI "+ map.get("dni") +", "
            		+ "aceptan con mutuo acuerdo mantener una relacion laboral por 1 año de servicio con un sueldo de S/.8000\n\n");
            document.add(body);
            
            Phrase footer = new Phrase("***Firmado Digitalmente***");
            document.add(footer);
            
            document.close();
            System.out.println("PDF creado");
			
			utilService.enviarCorreo(map);
			System.out.println("mensaje enviado");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

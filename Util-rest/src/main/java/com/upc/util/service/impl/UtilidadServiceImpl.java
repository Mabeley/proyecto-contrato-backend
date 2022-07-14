package com.upc.util.service.impl;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.upc.util.service.UtilidadService;

@Service
public class UtilidadServiceImpl implements UtilidadService {

	@Override
	public String enviarCorreo(Map<String, Object> datos) throws Exception {
		
		Session session = obtenerSesion();
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String)datos.get("de")));
		String[] correos = datos.get("para").toString().split(",");
		for (String correo : correos) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
		}
		message.setSubject((String)datos.get("asunto"));
		message.setContent((String)datos.get("contenido"), "text/html");
		Transport.send(message);
		
		return "Correo enviado";
	}

	private Session obtenerSesion() {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		final String username = "...";
		final String password = "...";

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return session;

	}

}

package com.upc.util.service.impl;

import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;

import com.upc.util.service.UtilidadService;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class UtilidadServiceImpl implements UtilidadService {

	private static String CERTIFICADO = "D:\\opt\\Certificado.pfx";
	private static String CLAVE = "12345678";

	@Override
	public String enviarCorreoAdjunto(Map<String, Object> datos, byte[] adjunto) throws Exception {

		Session session = obtenerSesion();
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String) datos.get("de")));
		String[] correos = datos.get("para").toString().split(",");
		for (String correo : correos) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
		}
		message.setSubject((String) datos.get("asunto"));

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent((String) datos.get("contenido"), "text/html;charset=utf-8");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		MimetypesFileTypeMap tiposMime = new MimetypesFileTypeMap();
		messageBodyPart = new MimeBodyPart();
		DataSource dataSource = new ByteArrayDataSource(adjunto, tiposMime.getContentType("application/pdf"));
		messageBodyPart.setDataHandler(new DataHandler(dataSource));
		messageBodyPart.setFileName((String) datos.get("dni")+".pdf");
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart);
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

		final String username = "sistemas.testing.ti@gmail.com";
		final String password = "twctwbkltlupnlty";

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		return session;

	}

	@Override
	public byte[] firmarPdf(byte[] data) throws Exception {
		try {
			PdfReader reader = null;
			reader = new PdfReader(data);

			KeyStore keystore = KeyStore.getInstance("PKCS12");
			InputStream in = new FileInputStream(CERTIFICADO);
			keystore.load(in, CLAVE.toCharArray());
			in.close();

			String alias = (String) keystore.aliases().nextElement();
			PrivateKey pk = (PrivateKey) keystore.getKey(alias, CLAVE.toCharArray());
			java.security.cert.Certificate[] chain = keystore.getCertificateChain(alias);

			ByteArrayOutputStream nuevoDocumento = new ByteArrayOutputStream();
			PdfStamper stp = PdfStamper.createSignature(reader, nuevoDocumento, '\000', null, true);
			PdfSignatureAppearance sap = stp.getSignatureAppearance();
			sap.setCrypto(pk, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
			sap.setReason("Firma de Contrato");
			sap.setLocation("Lima");
			stp.close();
			return nuevoDocumento.toByteArray();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String enviarCorreo(Map<String, Object> datos) throws Exception {
		Session session = obtenerSesion();
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress((String) datos.get("de")));
		String[] correos = datos.get("para").toString().split(",");
		for (String correo : correos) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
		}
		message.setSubject((String) datos.get("asunto"));
		message.setContent((String) datos.get("contenido"), "text/html;charset=utf-8");
		Transport.send(message);

		return "Correo enviado";
	}

}

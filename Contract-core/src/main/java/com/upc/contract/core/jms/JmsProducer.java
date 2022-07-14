package com.upc.contract.core.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Value("${jms.cola.notificacion}")
	String nombreCola;
	
	public void send(String mensaje) {
		jmsTemplate.setDefaultDestinationName(nombreCola);
		jmsTemplate.convertAndSend(mensaje);
	}
}

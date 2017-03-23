package com.teci.gereteci;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.teci.gereteci.model.Servico.Servico;
import com.teci.gereteci.model.Usuario.Usuario;

@Component
public class Mailer {
	
	@Autowired
	private JavaMailSender mailSender;
	private static Logger logger = LoggerFactory.getLogger(Mailer.class);
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	
	@Async
	public void enviar_servico(Usuario solicitante, Usuario atendente, String sigla_setor,Servico s, String Email)
	{
		
		
		Context context = new Context();
		context.setVariable("solicitante", solicitante);
		context.setVariable("atendente", atendente);
		context.setVariable("setor", sigla_setor);
		context.setVariable("servico", s);
		String email = thymeleaf.process("mail/ServicoCadastrado", context);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("suporte.ctb1210@ctb.ba.gov.br");
			helper.setTo("jfcarvalho@ctb.ba.gov.br");
			helper.setSubject("GERETECI - Abertura de Serviço");
			helper.setText(email, true);
			mailSender.send(mimeMessage);
		} catch(MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		thymeleaf.process("mail/ServicoCadastrado", context);
	}
}
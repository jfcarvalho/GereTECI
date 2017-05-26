package com.teci.gereteci;

import java.util.Date;

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

import com.teci.gereteci.model.Requisicao.Requisicao;
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
			helper.setTo("romeuoj@ctb.ba.gov.br");
			helper.setSubject("GERETECI - Abertura de Serviço");
			helper.setText(email, true);
			mailSender.send(mimeMessage);
		} catch(MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		thymeleaf.process("mail/ServicoCadastrado", context);
	}

	@Async
	public void enviar_requisicao(String solicitante, String protocolo, String setor, Date data, Date data_abertura,String Categoria, String descricao, String emailu)
	{
		
		
		Context context = new Context();
		context.setVariable("solicitante", solicitante);
		context.setVariable("protocolo", protocolo);
		context.setVariable("setor", setor);
		context.setVariable("data_abertura", data);
		context.setVariable("data_ocorrencia", data_abertura);
		context.setVariable("categoria", Categoria);
		context.setVariable("descricao", descricao);
		String email = thymeleaf.process("mail/RequisicaoCadastrada", context);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("suporte.ctb1210@ctb.ba.gov.br");
			helper.setTo(emailu);
			helper.setCc("jfcarvalho@ctb.ba.gov.br");
			helper.setSubject("GERETECI - Cadastro de Requisição");
			helper.setText(email, true);
			mailSender.send(mimeMessage);
		} catch(MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		thymeleaf.process("mail/ServicoCadastrado", context);
	}
	
	@Async
	public void requisicao_atendida(String nomea, String matriculaa, String protocolo, Date data, String Categoria, String emailu, String emaila)
	{
		Context context = new Context();
		context.setVariable("nome", nomea);
		context.setVariable("matricula", matriculaa);
		context.setVariable("protocolo", protocolo);
		context.setVariable("data", data);
		context.setVariable("categoria", Categoria);
		String email = thymeleaf.process("mail/RequisicaoAtendida", context);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("suporte.ctb1210@ctb.ba.gov.br");
			helper.setTo(emailu);
			helper.setCc(emaila);
			helper.setSubject("GERETECI - Atendimento de Requisição");
			helper.setText(email, true);
			mailSender.send(mimeMessage);
		} catch(MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		thymeleaf.process("mail/RequisicaoAtendida", context);
		
	}
	
	public void mensagem_nova_requisicao(String requisicao, String nomea, String matriculaa, String protocolo, Date data, String Categoria, String emailu, String emaila)
	{
		Context context = new Context();
		context.setVariable("nome", nomea);
		context.setVariable("matricula", matriculaa);
		context.setVariable("protocolo", protocolo);
		context.setVariable("data", data);
		context.setVariable("link", "localhost:8080/gereteci/requisicoes/" + requisicao + "/mensagem/novo");
		
		String email = thymeleaf.process("mail/RequisicaoMensagem", context);
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("suporte.ctb1210@ctb.ba.gov.br");
			helper.setTo(emailu);
			helper.setCc(emaila);
			helper.setSubject("GERETECI - Aviso de mensagem nova em requisição");
			helper.setText(email, true);
			mailSender.send(mimeMessage);
		} catch(MessagingException e) {
			logger.error("Erro enviando e-mail", e);
		}
		thymeleaf.process("mail/RequisicaoMensagem", context);
		
	}
	
}
package br.com.fabriciocs.infra.email.service;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class EmailService {
	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Autowired
	private JavaMailSenderImpl mailSender;

	void preparar(Map<String, String> config) {
		mailSender.setDefaultEncoding(config.get("servidor.email.encoding"));
		mailSender.setHost(config.get("servidor.email.host"));
		mailSender.setPassword(config.get("servidor.email.senha"));
		mailSender.setUsername(config.get("servidor.email.usuario"));
		mailSender.setPort(Integer.parseInt(config
				.get("servidor.email.host.port")));
		mailSender.setProtocol(config.get("servidor.email.protocol"));
		Properties prop = new Properties();
		prop.put("mail.smtp.auth",
				Boolean.parseBoolean(config.get("servidor.email.smtp.auth")));
		prop.put("mail.smtp.starttls.enable", Boolean.parseBoolean(config
				.get("servidor.email.smtp.starttls.enable")));
		mailSender.setJavaMailProperties(prop);
	}

	public InternetAddress[] getAddresses(String[] to) throws AddressException {
		InternetAddress addresses[] = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			addresses[i] = new InternetAddress(to[i]);
		}
		return addresses;
	}

	public void sendEmail(final String assunto, final String msg,
			final File attach, final String from, final String[] to) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(getAddresses(to));
			helper.setFrom(new InternetAddress(from));
			helper.setSubject(assunto);
			helper.setText(msg, true);
			helper.addAttachment(attach.getName(), attach);
		} catch (AddressException e) {
			throw new RuntimeException(e);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		mailSender.send(message);
	}
}

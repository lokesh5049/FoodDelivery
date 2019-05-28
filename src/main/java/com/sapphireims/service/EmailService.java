package com.sapphireims.service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 
 * Email Service server managed
 * 
 * @author lokesh.yadav
 * @since 2019-01-14
 *
 */
@Service("email")
public class EmailService {

	@Resource(mappedName = "java:jboss/mail/Mail")
	private Session session;

	private static final Logger LOGGER = Logger.getLogger(EmailService.class);

	// send method Single parameter
	public void send(final String addresses) {
		try {
			final Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			message.setSubject("SapphireIms Registeration Complete ");
			message.setText("Enjoy food Delivery System!!!!");
			Transport.send(message);
		} catch (MessagingException m) {
			LOGGER.error(m.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}

	}

	// overloaded method for subject and message body
	public void send(final String addresses, final String subject, final String body) {
		try {
			final Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
		} catch (MessagingException m) {
			LOGGER.error(m.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}

	}

}

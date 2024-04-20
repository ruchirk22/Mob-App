package com.mobicom.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String to, String subject, String message) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(to);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message);
    simpleMailMessage.setFrom("mobicom.prepaid@gmail.com");
    mailSender.send(simpleMailMessage);
  }

  public void sendEmailWithHtml(String to, String subject, String htmlContent) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(
        mimeMessage,
        true,
        "UTF-8"
      );
      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true);
      helper.setFrom("mobicom.prepaid@gmail.com");
      mailSender.send(mimeMessage);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}

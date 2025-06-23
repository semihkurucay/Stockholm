/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import stockholm.user_interface.cl_MailSettings;
import stockholm.data_access.sl_MailSetting;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author semih
 */
public class mailSend {

    cl_MailSettings mailSetting = new sl_MailSetting().getInformation();

    public void sendMail(String toMail, String cc, String subject, String text) {
        Properties prep = new Properties();
        prep.put("mail.smtp.auth", mailSetting.getAuth());
        prep.put("mail.smtp.starttls.enable", mailSetting.getStarttls());
        prep.put("mail.smtp.host", mailSetting.getHost());
        prep.put("mail.smtp.port", mailSetting.getPort());

        Session session = Session.getInstance(prep, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSetting.getMail(), mailSetting.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailSetting.getMail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Mail başarıyla iletildi.", "Mail Gönderildi", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Mail gönderilemedi, gönderim sırasında hata ile karşılaşıldı", "Mail Gönderilemedi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void sendTestMail() {
        Properties prep = new Properties();
        prep.put("mail.smtp.auth", mailSetting.getAuth());
        prep.put("mail.smtp.starttls.enable", mailSetting.getStarttls());
        prep.put("mail.smtp.host", mailSetting.getHost());
        prep.put("mail.smtp.port", mailSetting.getPort());

        Session session = Session.getInstance(prep, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSetting.getMail(), mailSetting.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailSetting.getMail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSetting.getMail()));
            message.setSubject("Test Maili");
            message.setText("Test maili olarak gönderildi, mail başarıyla iletidi.");

            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Mail başarıyla iletildi.", "Mail Gönderildi", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null, "Mail gönderilemedi, gönderim sırasında hata ile karşılaşıldı", "Mail Gönderilemedi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

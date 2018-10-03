package by.itechart.contacts.model.util;

import by.itechart.contacts.dao.AbstractDao;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class EmailUtil {

    private static String login;
    private static String password;



    static {
        Map<String, String> privateData = AbstractDao.getPrivateData();
        login = privateData.get("login");
        password = privateData.get("password");
    }

    public static void sendMail(String toMail, String subject, String messageText) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(login, "Contacts"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
        msg.setSubject(subject);
        msg.setContent(messageText, "text/plain; charset=UTF-8");
        msg.setHeader("X-SES-CONFIGURATION-SET", "ConfigSet");
        Transport transport = session.getTransport();
        try {
            transport.connect("smtp.mail.ru", login, password);
            transport.sendMessage(msg, msg.getAllRecipients());
        } finally {
            transport.close();
        }
    }

    public static String getMessage(String message, String party, String newYear, String birthday, String customText){
        switch (message){
            case "message.birthday": return birthday;
            case "message.newYear": return newYear;
            case "message.party": return party;
            default: return customText;
        }
    }


}

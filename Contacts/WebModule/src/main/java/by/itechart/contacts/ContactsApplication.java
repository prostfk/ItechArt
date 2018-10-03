package by.itechart.contacts;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.util.EmailUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Configuration
public class ContactsApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        sending();
        SpringApplication.run(ContactsApplication.class, args);
    }

    public synchronized static void sending(){
        new Thread(() -> {
            while (true){
                ContactDao contactDao = new ContactDao();
                List<Contact> all = contactDao.findAll();
                Date date = new Date();
                all.forEach(contact -> {
                    if (isBirthday(date,contact.getDate())){
                        try {
                            EmailUtil.sendMail(contact.getEmail(), String.format("Happy birthday, %s", contact.getName()), String.format("Birthdays are a new start, a fresh beginning and a time to pursue new endeavors with new goals. Move forward with confidence and courage. You are a very special person, %s. May today and all of your days be amazing!",contact.getName()));
                            System.out.println(String.format("%s has a birthday today! We sent him best wishes", contact.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    Thread.sleep(86_400_000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }).start();
    }

    private static boolean isBirthday(Date now, String contactDate){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String current = sdf.format(now);
        String substring = contactDate.substring(5);
        return current.equals(substring);

    }

}
package by.itechart.contacts.model.entity;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Contact {

    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date date;
    private Gender gender;
    private String citizenship;
    private FamilyStatus familyStatus;
    private String site;
    private String email;
    private String job;
    private Address address;

    public Contact() {
    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job, Address address) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        setDate(date);
        this.gender = gender;
        this.citizenship = citizenship;
        this.familyStatus = familyStatus;
        this.site = site;
        this.email = email;
        this.job = job;
        this.address = address;
    }

    public Contact(Long id,String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job, Address address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        setDate(date);
        this.gender = gender;
        this.citizenship = citizenship;
        this.familyStatus = familyStatus;
        this.site = site;
        this.email = email;
        this.job = job;
        this.address = address;
    }

    public Contact(Long id, String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        setDate(date);
        this.gender = gender;
        this.citizenship = citizenship;
        this.familyStatus = familyStatus;
        this.site = site;
        this.email = email;
        this.job = job;
    }

    public String getDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public void setDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }




}

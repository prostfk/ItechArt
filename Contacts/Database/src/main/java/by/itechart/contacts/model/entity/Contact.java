package by.itechart.contacts.model.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Contact {

    private Long id;
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
    @NotNull
    @Size(min = 3, max = 40)
    private String surname;
    private String patronymic;
    private Date date;
    private Gender gender;
    private String citizenship;
    private FamilyStatus familyStatus;
    private String site;
    private String email;
    private String job;
    private Long addressId;
    private boolean status;

    public Contact() {
    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job, Long address) {
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
        this.addressId = address;
    }

    public Contact(Long id,String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job, Long address) {
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
        this.addressId = address;
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

    public Contact(Long id, String name, String surname, String patronymic, String date, Gender gender, String citizenship, FamilyStatus familyStatus, String site, String email, String job, Long addressId, boolean status) {
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
        this.addressId = addressId;
        this.status = status;
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

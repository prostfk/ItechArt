package by.itechart.contacts.dao;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
import by.itechart.contacts.model.entity.FamilyStatus;
import by.itechart.contacts.model.entity.Gender;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ContactDao extends Dao<Contact> {


    @Override
    public void save(Contact contact) {
        //language=SQL
        execute(String.format(
                "INSERT INTO contact(name, surname, patronymic, date_of_birth, gender, citizenship, family_status, site, email, job) VALUES " +
                        "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                contact.getName(),contact.getSurname(),contact.getPatronymic(), contact.getDate(),
                contact.getGender(),contact.getCitizenship(),contact.getFamilyStatus(),
                contact.getSite(),contact.getEmail(),contact.getJob()
        ));
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new LinkedList<>();
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM contact");
        try {
            while (resultSet.next()){
                contacts.add(new Contact(
                        resultSet.getLong("id"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("patronymic"),resultSet.getString("date_of_birth"),
                        Gender.valueOf(resultSet.getString("gender")), resultSet.getString("citizenship"),
                        FamilyStatus.valueOf(resultSet.getString("family_status")),resultSet.getString("site"),
                        resultSet.getString("email"),resultSet.getString("job")
                ));
            }

        }catch (Exception e){
            System.err.println(e);
        }
        return contacts;
    }

    @Override
    public void delete(Long id) {
        //language=SQL
        execute(String.format("DELETE * FROM contact WHERE id='%d'", id));
    }

    @Override
    public Contact update(Long id, Contact contact) {
        //language=SQL
        execute(String.format("UPDATE contact SET name='%s', surname='%s', patronymic='%s', date_of_birth='%s', gender='%s', citizenship='%s', family_status='%s', site='%s',email='%s',job='%s' WHERE id='%d'",
                contact.getName(), contact.getSurname(), contact.getPatronymic(),contact.getDate(), contact.getGender(),
                contact.getCitizenship(), contact.getFamilyStatus(), contact.getSite(), contact.getEmail(),
                contact.getJob(), id));
        return contact;

    }

    public Contact findContactById(Long id){
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM contact WHERE id='%d'", id));
        try{
            if (resultSet.next()){
                return new Contact(
                        resultSet.getLong("id"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("patronymic"),resultSet.getString("date_of_birth"),Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("citizenship"), FamilyStatus.valueOf(resultSet.getString("family_status")),resultSet.getString("site"),
                        resultSet.getString("email"),resultSet.getString("job"),null
                );
            }
        }catch (Exception e){
            System.err.println(e);
        }
        return null;
    }

    public List<Contact> findContactsByFiled(ContactField field, String value){
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM contact WHERE %s LIKE '%%%s%%'", field.name(), value));
        List<Contact> contacts = new LinkedList<>();
        try{
            while (resultSet.next()){
                contacts.add(new Contact(
                        resultSet.getLong("id"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("patronymic"),resultSet.getString("date_of_birth"),Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("citizenship"), FamilyStatus.valueOf(resultSet.getString("family_status")),resultSet.getString("site"),
                        resultSet.getString("email"),resultSet.getString("job"),null
                ));
            }
        }catch (Exception e){
            System.err.println(e);
        }
        return contacts;
    }


}

package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
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
                "INSERT INTO contact(name, surname, patronymic, date_of_birth, gender, citizenship, family_status, site, email, job, address_id) VALUES " +
                        "('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d')",
                contact.getName(),contact.getSurname(),contact.getPatronymic(), contact.getDate(),
                contact.getGender(),contact.getCitizenship(),contact.getFamilyStatus(),
                contact.getSite(),contact.getEmail(),contact.getJob(),contact.getAddress() != null ? contact.getAddress().getId() : null
        ));
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new LinkedList<>();
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM contact JOIN address a on contact.address_id = a.id");
        try {
            while (resultSet.next()){
                contacts.add(new Contact(
                        resultSet.getLong("id"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("patronymic"),resultSet.getString("date_of_birth"),
                        Gender.valueOf(resultSet.getString("gender")), resultSet.getString("citizenship"),
                        FamilyStatus.valueOf(resultSet.getString("family_status")),resultSet.getString("site"),
                        resultSet.getString("email"),resultSet.getString("job"),
                        new Address(
                                resultSet.getString("country"),resultSet.getString("city"),
                                resultSet.getString("street"),resultSet.getInt("house"),
                                resultSet.getInt("flat"), resultSet.getInt("post_index")
                        )
                ));
            }

        }catch (Exception e){
            System.err.println(e);
        }
        return contacts;
    }


}

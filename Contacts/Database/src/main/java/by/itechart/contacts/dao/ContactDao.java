package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.*;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContactDao extends AbstractDao<Contact> {

    private static final Logger LOGGER = Logger.getLogger(ContactDao.class);


    @Override
    public Contact findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createEntity(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Contact> findAll() {
        //language=SQL
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM contact")) {
            return createList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Contact save(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact(name, surname, patronymic, date_of_birth, gender, citizenship, family_status, site, email, job, address_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender(), contact.getCitizenship(), contact.getFamilyStatus(), contact.getSite(), contact.getEmail(), contact.getJob());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }


    @Override
    public Contact update(Long id, Contact contact) {
        //language=SQL
        try {
            if (contact.getAddress() != null) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET name=?, surname=?, patronymic=?, date_of_birth=?, gender=?, citizenship=?, family_status=?, site=?,email=?,job=?,address_id=? WHERE id=?");
                execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender(),
                        contact.getCitizenship(), contact.getFamilyStatus(), contact.getSite(), contact.getEmail(),
                        contact.getJob(), contact.getAddress(), id);
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET name=?, surname=?, patronymic=?, date_of_birth=?, gender=?, citizenship=?, family_status=?, site=?,email=?,job=? WHERE id=?");
                execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender(),
                        contact.getCitizenship(), contact.getFamilyStatus(), contact.getSite(), contact.getEmail(),
                        contact.getJob(), id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return contact;

    }

    @Override
    public Contact createEntity(ResultSet resultSet) {
        try {
            return new Contact(
                    resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("surname"),
                    resultSet.getString("patronymic"), resultSet.getString("date_of_birth"), Gender.valueOf(resultSet.getString("gender")),
                    resultSet.getString("citizenship"), FamilyStatus.valueOf(resultSet.getString("family_status")), resultSet.getString("site"),
                    resultSet.getString("email"), resultSet.getString("job"), resultSet.getLong("address_id")
            );
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Contact> findContactsFromIdAndWithLimit(Long firstId, Long limit) {
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM contact WHERE id >= '%d' LIMIT %d ", firstId, limit));
        return createList(resultSet);
    }

    public Contact findContactById(Long id) {
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM contact WHERE id='%d'", id));
        try {
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Contact> findContactsByFiled(ContactField field, String value) {
        //language=SQL
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE ? LIKE ?");
            ResultSet resultSet = executeQuery(preparedStatement, field, "%" + value + "%");
            return createList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addAddressToContact(Long contactId, Long addressId) {
        //language=SQL
        execute(String.format("UPDATE contact SET address_id='%d' WHERE id='%d'", addressId, contactId));
    }


    public Long findLastId() {
        ResultSet resultSet = executeQuery("SELECT MAX(id) from contact");
        try {
            if (resultSet.next()) {
                return resultSet.getLong("MAX(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return 0L;
    }

}

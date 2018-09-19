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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE id=? AND status!=1");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    public Contact delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET status=1 WHERE id=?")) {
            execute(preparedStatement, id);
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return findContactById(id);
    }

    @Override
    public List<Contact> findAll() {
        //language=SQL
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM contact WHERE status!=1")) {
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
            return null;
        }
    }

    @Override
    public Contact save(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contact(name, surname, patronymic, date_of_birth, gender, citizenship, family_status, site, email, job, address_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender() + "", contact.getCitizenship(), contact.getFamilyStatus() + "", contact.getSite(), contact.getEmail(), contact.getJob(),contact.getAddressId());
        } catch (SQLException e) {
            log(e, LOGGER);
        }
        return contact;
    }


    @Override
    public Contact update(Long id, Contact contact) {
        //language=SQL
        try {
            if (contact.getAddressId() != null) {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET name=?, surname=?, patronymic=?, date_of_birth=?, gender=?, citizenship=?, family_status=?, site=?,email=?,job=?,address_id=? WHERE id=?");
                execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender() + "",
                        contact.getCitizenship(), contact.getFamilyStatus() + "", contact.getSite(), contact.getEmail(),
                        contact.getJob(), contact.getAddressId(), id);
            } else {
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contact SET name=?, surname=?, patronymic=?, date_of_birth=?, gender=?, citizenship=?, family_status=?, site=?,email=?,job=? WHERE id=?");
                execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender() + "",
                        contact.getCitizenship(), contact.getFamilyStatus() + "", contact.getSite(), contact.getEmail(),
                        contact.getJob(), id);
            }
        } catch (Exception e) {
            log(e, LOGGER);
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
            log(e, LOGGER);
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
            if (resultSet.next()) {
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    public List<Contact> search(String param, String value) {
        List<Contact> list = new LinkedList<>();
        //language=SQL
        value = "%" + value + "%";
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM contact WHERE %s LIKE ?", param))) {
            ResultSet resultSet = executeQuery(preparedStatement, value);
            System.out.println(preparedStatement);
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return list;


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
            log(e, LOGGER);
        }
        return 0L;
    }

}

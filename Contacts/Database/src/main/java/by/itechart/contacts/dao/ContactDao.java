package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.*;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ContactDao extends AbstractDao<Contact> {

    private static final Logger LOGGER = Logger.getLogger(ContactDao.class);


    @Override
    public Contact findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE id=? AND status!=1");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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
            execute(preparedStatement, contact.getName(), contact.getSurname(), contact.getPatronymic(), contact.getDate(), contact.getGender() + "", contact.getCitizenship(), contact.getFamilyStatus() + "", contact.getSite(), contact.getEmail(), contact.getJob(), contact.getAddressId());
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
            return contact;
        } catch (Exception e) {
            log(e, LOGGER);
            return null;
        }
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE status!=1 LIMIT ?,?")) {
            ResultSet resultSet = executeQuery(preparedStatement, firstId, limit);
            System.out.println(preparedStatement);
            List<Contact> list = createList(resultSet);
            return list;
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return Collections.emptyList();
    }

    public Contact findContactByNameAndSurname(String name, String surname) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE name=? AND surname=?")) {
            ResultSet resultSet = executeQuery(preparedStatement, name, surname);
            if (resultSet.next()) {
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    //    TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST
    public List<Contact> findContactsByIdList(String... ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length - 1; i++) {
            sb.append("?,");
        }
        sb.append("?");
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM contact WHERE id IN(%s)", sb.toString()))) {
            ResultSet resultSet = executeQuery(preparedStatement, ids);
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    public List<Contact> findContactsByFieldsAndValues(String[] params, String[] values) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.length; i++) {
            if (i != params.length - 1) {
                sb.append(String.format("%s LIKE '%%%s%%' AND ", params[i], values[i]));
            } else {
                sb.append(String.format("%s LIKE '%%%s%%'", params[i], values[i]));
            }
        }
        System.out.println(sb);
        try (ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM contact WHERE %s", sb.toString()))) {
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    public Map<String, String> findContactWithAddressById(Long id) {
        HashMap<String, String> json = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT contact.id,name,surname,patronymic,date_of_birth,gender,citizenship,family_status,site,email,job,address_id,country,city,street,house,flat,post_index FROM contact LEFT JOIN address a on contact.address_id = a.id WHERE contact.id = ? AND status!=1;")) {
            ResultSet resultSet = executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                json.put("id", id.toString());
                json.put("name", resultSet.getString("name"));
                json.put("surname", resultSet.getString("surname"));
                json.put("patronymic", resultSet.getString("patronymic"));
                json.put("date", resultSet.getString("date_of_birth"));
                json.put("gender", resultSet.getString("gender"));
                json.put("citizenship", resultSet.getString("citizenship"));
                json.put("familyStatus", resultSet.getString("family_status"));
                json.put("site", resultSet.getString("site"));
                json.put("email", resultSet.getString("email"));
                json.put("job", resultSet.getString("job"));
                json.put("addressId", resultSet.getString("address_id"));
                json.put("country", resultSet.getString("country"));
                json.put("city", resultSet.getString("city"));
                json.put("street", resultSet.getString("street"));
                json.put("house", resultSet.getString("house"));
                json.put("flat", resultSet.getString("flat"));
                json.put("postIndex", resultSet.getString("post_index"));
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return json;
    }

    public Contact findContactByAddressId(Long addressId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE address_id=?")) {
            ResultSet resultSet = executeQuery(preparedStatement, addressId);
            if (resultSet.next()) {
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format("SELECT * FROM contact WHERE %s LIKE ? AND status!=1", param))) {
            ResultSet resultSet = executeQuery(preparedStatement, value);
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return list;


    }

    public Contact findContactByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact WHERE email=?")) {
            ResultSet resultSet = executeQuery(preparedStatement, email);
            if (resultSet.next()) {
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            log(e, LOGGER);
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
            log(e, LOGGER);
        }
        return 0L;
    }

}

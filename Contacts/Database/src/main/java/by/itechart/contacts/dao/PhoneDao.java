package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Phone;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class PhoneDao extends AbstractDao<Phone> {

    private static final Logger LOGGER = Logger.getLogger(PhoneDao.class);

    @Override
    public Phone save(Phone phone) {
        //language=SQL
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO phone(contact_id, country_code, number, type, comment) VALUES (?,?,?,?,?)")) {
            execute(preparedStatement, phone.getContactId(), phone.getCountryCode(), phone.getNumber(), phone.getType(), phone.getComment());
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Phone findById(Long id) {
        try (ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM phone WHERE id='%s'", id))) {
            if(resultSet.next()){
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Phone> findAll() {
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM phone")) {
            return createList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public Phone update(Long id, Phone phone) {
        //language=SQL
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE phone SET country_code=?, number=?,type=?,comment=? WHERE contact_id=?")) {
            execute(preparedStatement, phone.getCountryCode(), phone.getNumber(), phone.getType(), phone.getComment(), id);
            return phone;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Phone createEntity(ResultSet resultSet) {
        try {
            return new Phone(
                    resultSet.getLong("id"), resultSet.getLong("contact_id"), resultSet.getString("country_code"),
                    resultSet.getString("number"), resultSet.getString("type"), resultSet.getString("comment")
            );
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Phone> findPhonesByParameter(String parameter, String value) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phone WHERE ? LIKE ?")) {
            value = String.format("%%%s%%", value);
            ResultSet resultSet = executeQuery(preparedStatement, parameter, value);
            System.out.println(preparedStatement.toString());
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
            return Collections.emptyList();
        }
    }

    public Phone findPhoneByContactId(Long id) {
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM phone WHERE contact_id='%d'", id));
        try {
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }


}

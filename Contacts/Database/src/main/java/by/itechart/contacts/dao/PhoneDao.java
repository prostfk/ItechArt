package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Phone;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class PhoneDao extends AbstractDao<Phone> {

    private static final Logger LOGGER = Logger.getLogger(PhoneDao.class);

    public Phone delete(Long id){
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE phone SET status=1 WHERE id=?")) {
            execute(preparedStatement,id);
            ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM phone WHERE id=%d", id));
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }

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
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM phone WHERE status!=1")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE phone SET country_code=?, number=?,type=?,comment=? WHERE id=? AND status!=1")) {
            execute(preparedStatement, phone.getCountryCode(), phone.getNumber(), phone.getType(), phone.getComment(), id);
            System.out.println(preparedStatement);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phone WHERE ? LIKE ? AND status!=1")) {
            value = String.format("%%%s%%", value);
            ResultSet resultSet = executeQuery(preparedStatement, parameter, value);
            System.out.println(preparedStatement.toString());
            return createList(resultSet);
        } catch (Exception e) {
            log(e, LOGGER);
            return Collections.emptyList();
        }
    }

    public List<Phone> findPhonesByContactId(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phone WHERE contact_id=? AND status!=1")){
            ResultSet resultSet = executeQuery(preparedStatement, id);
            return createList(resultSet);
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }

    public Phone findPhonesByNumberAndCountryCodeAndContactId(String countryCode, String number, Long contactId){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM phone WHERE country_code=? AND number=? AND contact_id=? AND status!=1")) {
            ResultSet resultSet = executeQuery(preparedStatement, countryCode, number, contactId);
            if (resultSet.next()){
                createEntity(resultSet);
            }
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }


}

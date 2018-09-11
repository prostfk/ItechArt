package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Phone;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class PhoneDao extends Dao<Phone> {

    private static final Logger LOGGER = Logger.getLogger(PhoneDao.class);

    @Override
    public void save(Phone phone) {
        //language=SQL
        execute(String.format("INSERT INTO phone(contact_id, country_code, number, type, comment) VALUES ('%d','%s','%s','%s','%s')",phone.getContactId(), phone.getCountryCode(), phone.getNumber(), phone.getType(), phone.getComment()));
    }

    @Override
    public List<Phone> findAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        execute(String.format("DELETE FROM phone WHERE id='%d'", id));
    }

    @Override
    public Phone update(Long id, Phone phone) {
        //language=SQL
        execute(String.format("UPDATE phone SET country_code='%s', number='%s',type='%s',comment='%s'", phone.getCountryCode(),phone.getNumber(),phone.getType(),phone.getComment()));
        return phone;
    }

    public Phone findPhoneById(Long id){
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM phone WHERE id='%d'", id));
        return phoneFromResultSet(resultSet);
    }

    public Phone findPhoneByContactId(Long id){
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM phone WHERE contact_id='%d'", id));
        return phoneFromResultSet(resultSet);
    }

    private Phone phoneFromResultSet(ResultSet resultSet){
        try {
            if (resultSet.next()){
                return new Phone(
                        resultSet.getLong("id"),resultSet.getLong("contact_id"),resultSet.getString("country_code"),
                        resultSet.getString("number"),resultSet.getString("type"),resultSet.getString("comment")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}

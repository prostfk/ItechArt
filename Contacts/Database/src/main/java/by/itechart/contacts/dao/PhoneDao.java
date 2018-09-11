package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Phone;

import java.util.List;

public class PhoneDao extends Dao<Phone> {
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
        return null;
    }
}

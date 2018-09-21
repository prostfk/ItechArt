package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Address;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AddressDao extends AbstractDao<Address> {

    private static final Logger LOGGER = Logger.getLogger(AddressDao.class);


    public void delete(Long id) {
        //language=SQL
//        execute(String.format("DELETE FROM address WHERE id='%d'", id));
    }

    public Address findAddressByUserId(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM contact JOIN address a on contact.address_id = a.id WHERE contact.id = ?")) {
            ResultSet resultSet = executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                return new Address(
                        resultSet.getLong("address_id"), resultSet.getString("country"), resultSet.getString("city"),
                        resultSet.getString("street"), resultSet.getString("house"), resultSet.getString("flat"),
                        resultSet.getString("post_index"));
            }
        } catch (Exception e) {
            log(e, LOGGER);
        }
        return null;
    }

    @Override
    public Address findById(Long id) {
        //language=SQL
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM address WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM address");
            return createList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Address save(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO address(country, city, street, house, flat, post_index) VALUES (?,?,?,?,?,?)");
            execute(preparedStatement, address.getCountry(), address.getCity(), address.getStreet(), address.getHouse(), address.getFlat(), address.getPostIndex());
            return address;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Address update(Long id, Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE address SET country=?, city=?,street=?,house=?,flat=?,post_index=? WHERE id=?");
            execute(preparedStatement,
                    address.getCountry(), address.getCity(), address.getStreet(),
                    address.getHouse(), address.getFlat(), address.getPostIndex(), id
            );
            return address;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public Long findLastId() {
        ResultSet resultSet = executeQuery("SELECT MAX(id) FROM address");
        try {
            if (resultSet.next()) {
                return resultSet.getLong("MAX(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    @Override
    public Address createEntity(ResultSet resultSet) {
        try {
            return new Address(
                    resultSet.getLong("id"), resultSet.getString("country"), resultSet.getString("city"),
                    resultSet.getString("street"), resultSet.getString("house"), resultSet.getString("flat"),
                    resultSet.getString("post_index")
            );
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }


}
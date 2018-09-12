package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Address;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AddressDao extends Dao<Address> {

    private static final Logger LOGGER = Logger.getLogger(AddressDao.class);

    @Override
    public void save(Address address) {
        //language=SQL
        execute(String.format("INSERT INTO address(country,city,street,house,flat,post_index) VALUES('%s','%s','%s','%d','%d','%d')",address.getCountry(),address.getCity(), address.getStreet(), address.getHouse(),address.getFlat(), address.getPostIndex()));
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new LinkedList<>();
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM address");
        try {
            while (resultSet.next()){
                addresses.add(new Address(
                        resultSet.getLong("id"),resultSet.getString("country"),
                        resultSet.getString("city"), resultSet.getString("street"),
                        resultSet.getInt("house"),resultSet.getInt("flat"),resultSet.getInt("post_index")
                ));
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return addresses;
    }

    @Override
    public void delete(Long id) {
        //language=SQL
        execute(String.format("DELETE FROM address WHERE id='%d'", id));
    }

    @Override
    public Address update(Long id, Address address) {
        //language=SQL
        execute(String.format("UPDATE address SET country='%s', city='%s',street='%s',house='%d',flat='%d',post_index='%d' WHERE id='%d'",address.getCountry(),address.getCity(),address.getStreet(),address.getHouse(),address.getFlat(),address.getPostIndex(), id));
        return address;
    }

    public Address findAddressById(Long id){
        //language=SQL
        ResultSet resultSet = executeQuery(String.format("SELECT * FROM address WHERE id='%d'", id));
        try{
            if (resultSet.next()){
                return new Address(
                        resultSet.getLong("id"),resultSet.getString("country"),resultSet.getString("city"),
                        resultSet.getString("street"),resultSet.getInt("house"),resultSet.getInt("flat"),
                        resultSet.getInt("post_index")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Long findLastId(){
        ResultSet resultSet = executeQuery("SELECT MAX(id) FROM address");
        try {
            if (resultSet.next()){
                return resultSet.getLong("MAX(id)");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

}

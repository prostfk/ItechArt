package by.itechart.contacts.repository;

import by.itechart.contacts.model.entity.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findAll();
    Contact findContactById(Long id);

}

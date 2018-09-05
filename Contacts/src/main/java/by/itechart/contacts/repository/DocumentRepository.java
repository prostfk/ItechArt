package by.itechart.contacts.repository;

import by.itechart.contacts.model.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    List<Document> findAll();

}

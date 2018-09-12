package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Document;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DocumentDao extends Dao<Document> {

    private static final Logger LOGGER = Logger.getLogger(DocumentDao.class);

    @Override
    public void save(Document document) {
        //language=SQL
        execute(String.format("INSERT INTO document(path, contact_id, name) VALUES ('%s','%d','%s')", document.getPath(),document.getContactId(),document.getName()));
    }

    @Override
    public List<Document> findAll() {
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM document");
        List<Document> documents = new LinkedList<>();
        try {
            while (resultSet.next()){
                documents.add(new Document(
                        resultSet.getLong("id"),resultSet.getString("path"),
                        resultSet.getLong("contact_id"), resultSet.getString("name")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        System.out.println(documents);
        return documents;
    }

    @Override
    public void delete(Long id) {
        //language=SQL
        execute(String.format("DELETE FROM document WHERE id='%d'", id));
    }

    @Override
    public Document update(Long id, Document document) {
        //language=SQL
        execute(String.format("UPDATE document SET path='%s', contact_id='%d', name='%s' WHERE id='%d'",document.getPath(),document.getContactId(),document.getName(), id));
        return document;
    }
}

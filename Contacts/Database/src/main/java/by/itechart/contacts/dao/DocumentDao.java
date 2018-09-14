package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Document;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DocumentDao extends AbstractDao<Document> {

    private static final Logger LOGGER = Logger.getLogger(DocumentDao.class);

    @Override
    public Document save(Document document) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO document(path, contact_id, name) VALUES (?,?,?)")) {
            execute(preparedStatement,document.getPath(),document.getContactId(),document.getName());
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return document;
    }

    @Override
    public Document findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document WHERE id=?")) {
            return createEntity(executeQuery(preparedStatement,id));
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM document");
        List<Document> documents = new LinkedList<>();
        try {
            documents = createList(resultSet);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return documents;
    }


    @Override
    public Document update(Long id, Document document) {
        //language=SQL
        execute(String.format("UPDATE document SET path='%s', contact_id='%d', name='%s' WHERE id='%d'",document.getPath(),document.getContactId(),document.getName(), id));
        return document;
    }

    @Override
    public Document createEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()){
                new Document(
                        resultSet.getLong("id"),resultSet.getString("path"),
                        resultSet.getLong("contact_id"), resultSet.getString("name")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}

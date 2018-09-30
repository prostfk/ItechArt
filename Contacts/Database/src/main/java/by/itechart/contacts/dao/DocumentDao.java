package by.itechart.contacts.dao;

import by.itechart.contacts.model.entity.Document;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DocumentDao extends AbstractDao<Document> {

    private static final Logger LOGGER = Logger.getLogger(DocumentDao.class);

    @Override
    public Document save(Document document) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO document(path, contact_id, name) VALUES (?,?,?)")) {
            execute(preparedStatement, document.getPath(), document.getContactId(), document.getName());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return document;
    }

    @Override
    public Document findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document WHERE id=? AND status!=1")) {
            ResultSet resultSet = executeQuery(preparedStatement, id);
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Document> findAll() {
        //language=SQL
        ResultSet resultSet = executeQuery("SELECT * FROM document WHERE status!=1");
        List<Document> documents = new LinkedList<>();
        try {
            documents = createList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return documents;
    }

    public List<Document> findDocumentsByUserId(Long id){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document WHERE contact_id=? AND status!=1")) {
            ResultSet resultSet = executeQuery(preparedStatement, id);
            return createList(resultSet);
        }catch (Exception e){
            log(e,LOGGER);
        }
        return Collections.emptyList();

    }

    public Document findDocumentByNameAndContactId(String name, Long contactId){
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document WHERE contact_id=? AND name=? AND status!=1")) {
            ResultSet resultSet = executeQuery(preparedStatement, contactId, name);
            if (resultSet.next()){
                return createEntity(resultSet);
            }
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }

    @Override
    public Document update(Long id, Document document) {
        //language=SQL
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE document SET path=?, contact_id=?, name=? WHERE id=?")) {
            execute(preparedStatement, document.getPath(), document.getContactId(), document.getName(), id);
        }catch (Exception e){
            log(e,LOGGER);
        }
        return document;
    }

    public Document delete(Long id){
        Document byId = findById(id);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE document SET status=1 WHERE id=? AND status!=1")) {
            execute(preparedStatement,id);
            return byId;
        }catch (Exception e){
            log(e,LOGGER);
        }
        return null;
    }

    @Override
    public Document createEntity(ResultSet resultSet) {
        try {
            return new Document(
                    resultSet.getLong("id"), resultSet.getString("path"),
                    resultSet.getLong("contact_id"), resultSet.getString("name"),
                    resultSet.getString("upload_date")
            );
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}

package by.itechart.contacts.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


@Data
public class Document {

    private Long id;
    private String path;
    private Long contactId;
    private String name;
    private String date;

    public Document() {
    }

    public Document(String path, Long contactId, String name) {
        this.path = path;
        this.contactId = contactId;
        this.name = name;
    }

    public Document(Long id, String path, Long contactId, String name, String date) {
        this.id = id;
        this.path = path;
        this.contactId = contactId;
        this.name = name;
        this.date = date;
    }

    public Document(String path, Long contactId, String name, String date) {
        this.path = path;
        this.contactId = contactId;
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

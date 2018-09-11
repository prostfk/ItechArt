package by.itechart.contacts.model.entity;

public class Phone {

    private Long id;
    private Long contactId;
    private String countryCode;
    private String number;
    private String type;
    private String comment;

    public Phone() {
    }

    public Phone(Long id, Long contactId, String countryCode, String number, String type, String comment) {
        this.id = id;
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
    }

    public Phone(Long contactId, String countryCode, String number, String type, String comment) {
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.number = number;
        this.type = type;
        this.comment = comment;
    }

    public Phone(Long id, Long contactId, String countryCode, String number, String type) {
        this.id = id;
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.number = number;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package by.itechart.contacts.model.validator;

import by.itechart.contacts.model.entity.Contact;

public class ContactValidator implements Validator<Contact> {
    @Override
    public boolean validate(Contact contact) {
        return contact.getName().length() > 2 && contact.getName().length() < 30 &&
                contact.getSurname().length() > 2 && contact.getSurname().length() < 40 &&
                contact.getDate() != null && (contact.getGender().toString().equals("Male") || contact.getGender().toString().equals("Female"));

    }
}

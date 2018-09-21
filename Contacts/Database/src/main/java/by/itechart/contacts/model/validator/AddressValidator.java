package by.itechart.contacts.model.validator;

import by.itechart.contacts.model.entity.Address;

public class AddressValidator implements Validator<Address> {
    @Override
    public boolean validate(Address address) {
        return address.getCountry().length() > 3 && address.getCountry().length() < 50 &&
                address.getCity().length() > 3 && address.getCity().length() < 50 &&
                address.getStreet().length() > 3 && address.getStreet().length() < 50 &&
                address.getHouse().length() >= 1 && address.getHouse().length() < 11 &&
                address.getFlat().length() >= 1 && address.getHouse().length() < 11 &&
                address.getPostIndex().length() >= 1 && address.getPostIndex().length() < 11;
    }
}

package by.itechart.contacts.controller;

import by.itechart.contacts.dao.AddressDao;
import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {

    private AddressDao addressDao;
    private ContactDao contactDao;

    public AddressController() {
        this.addressDao = new AddressDao();
        this.contactDao = new ContactDao();
    }

    @GetMapping(value = "/addAddress/{id}")
    public ModelAndView addNewAddress(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("addAddress", "address", new Address());
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @PostMapping(value = "/addAddress/{id}")
    @ResponseBody
    public Address submit(@PathVariable Long id, Address address){
        addressDao.save(address);
        Long addressId = addressDao.findLastId();
        contactDao.addAddressToContact(id,addressId);
        return address;
    }

    @GetMapping(value = "/check")
    @ResponseBody
    public Long checkId(){
        return addressDao.findLastId();
    }


}

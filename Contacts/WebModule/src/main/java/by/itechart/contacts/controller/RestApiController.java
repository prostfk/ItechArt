package by.itechart.contacts.controller;

import by.itechart.contacts.dao.AddressDao;
import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.DocumentDao;
import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
import by.itechart.contacts.model.util.EmailUtil;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = "/rest")
@RestController
public class RestApiController {

    private ContactDao contactDao;
    private DocumentDao documentDao;
    private AddressDao addressDao;

    public RestApiController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
        addressDao = new AddressDao();
    }

    @GetMapping(value = "/deleteContact/{id}")
    public Contact deleteContact(@PathVariable Long id){
        return contactDao.delete(id);
    }

    @GetMapping(value = "/contact/{id}")
    public Contact findContact(@PathVariable Long id){
        return contactDao.findContactById(id);
    }

    @GetMapping(value = "/searchContact")
    public List<Contact> searchContacts(@RequestParam("type")String type, @RequestParam("value") String value){
        return contactDao.search(type,value);
    }

    @GetMapping(value = "/allContacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List findAll(){
        return contactDao.findAll();
    }

    @GetMapping(value = "/documents")
    public List findDocuments(){
        return documentDao.findAll();
    }

    @PostMapping(value = "/addAddress/{id}")
    public Address submit(@PathVariable Long id, Address address){
        addressDao.save(address);
        Long addressId = addressDao.findLastId();
        contactDao.addAddressToContact(id,addressId);
        return address;
    }

    @GetMapping(value = "/address/{id}")
    public Address findAddress(@PathVariable Long id){
        return addressDao.findById(id);
    }

    @GetMapping(value = "/check")
    public Long checkId(){
        return addressDao.findLastId();
    }

    @PostMapping(value = "/sendEmail")
    public String sendEmail(@RequestParam String subject, @RequestParam String message, @RequestParam String email) {
        EmailUtil.sendMail(email, subject, message);
        return String.format("%s/%s/%s", email, subject, message);
    }

    @GetMapping(value = "/getNumberOfPage")
    public String number(HttpServletRequest request) {
        char[] chars = request.getRequestURL().toString().toCharArray();
        return chars[chars.length - 1] + "";
    }

    @PutMapping(value = "/editContact/{id}")
    public Contact editContact(@PathVariable Long id, Contact contact) {
        return contactDao.update(id, contact);
    }




}

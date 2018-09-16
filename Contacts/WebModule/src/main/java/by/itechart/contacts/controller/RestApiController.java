package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.DocumentDao;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
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

    public RestApiController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
    }

//    @DeleteMapping(value = "/deleteContact/{id}")
//    public String deleteContact(@PathVariable Long id){
//        contactDao.delete(id);
//        return JSONObject.quote("Status: ok");
//    }

    @GetMapping(value = "/contact/{id}")
    public Contact findContact(@PathVariable Long id){
        return contactDao.findContactById(id);
    }

    @GetMapping(value = "/searchContact")
    public List<Contact> searchContacts(@RequestParam("type")String type, @RequestParam("value") String value){
//        return contactDao.findContactsByFiled(ContactField.valueOf(type),value);
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





}

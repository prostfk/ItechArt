package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private ContactDao contactDao;

    public ContactController() {
        contactDao = new ContactDao();
    }

    @GetMapping(value = "/registration")
    public ModelAndView getRegistration(){
        return new ModelAndView("registration", "contact", new Contact());
    }

    @PostMapping(value = "/registration")
    public String processRegistration(Contact contact){
        contactDao.save(contact);//todo create validator
        return "redirect:/all";
    }

    @GetMapping(value = "/editContact/{id}")
    public ModelAndView edit(@PathVariable Long id){
        return new ModelAndView("editContact", "contact", contactDao.findContactById(id));
    }

    @PostMapping(value = "/editContact/{id}")
    @ResponseBody
    public Contact processEditing(@PathVariable Long id, Contact contact){
        return contactDao.update(id, contact);
    }

    @GetMapping(value = "/allContacts")
    public ModelAndView findContacts(){
        return new ModelAndView("contactViewer", "contacts", contactDao.findAll());
    }

    @GetMapping(value = "/allContacts/{id}")
    public ModelAndView managePages(@PathVariable Long id){
        return new ModelAndView("contactViewer" , "contacts", contactDao.findContactsFromIdAndWithLimit(id * 2, 2L));
    }



}

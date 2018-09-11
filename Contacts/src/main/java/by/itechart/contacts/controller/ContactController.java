package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.PhoneDao;
import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
import by.itechart.contacts.model.entity.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private ContactDao contactDao;
    private PhoneDao phoneDao;

    public ContactController() {
        phoneDao = new PhoneDao();
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

    @GetMapping(value = "/addContact")
    public ModelAndView addNewContact(){
        ModelAndView modelAndView = new ModelAndView("addContact", "contact", new Contact());
        modelAndView.addObject("phone", new Phone());
        return modelAndView;
    }

    @PostMapping(value = "/addContact")
    public String processAdding(Contact contact, Phone phone){
        contactDao.save(contact);
        Long lastId = contactDao.findLastId();
        phone.setContactId(lastId);
        phoneDao.save(phone);
        return "redirect:/rest/allContacts/1";
    }

    @GetMapping(value = "/editContact/{id}")
    public ModelAndView edit(@PathVariable Long id){
        Contact contactById = contactDao.findContactById(id);
        ModelAndView modelAndView = new ModelAndView("editContact", "contact", contactDao.findContactById(id));
        Phone phoneByContactId = phoneDao.findPhoneByContactId(contactById.getId());
        if (phoneByContactId == null){
            phoneByContactId = new Phone();
        }
        System.out.println(phoneByContactId.getId());
        modelAndView.addObject("phone", phoneByContactId);
        return modelAndView;
    }

    @PostMapping(value = "/editContact/{contactId}")
    @ResponseBody
    public Contact processEditing(@PathVariable Long contactId, Contact contact, Phone phone){
        if (phone.getId()!=null){
            phoneDao.update(phone.getId(),phone);
        }else{
            phoneDao.save(phone);
        }
        return contactDao.update(contactId, contact);
    }

    @GetMapping(value = "/allContacts/")
    public String checkAll(){
        return "redirect:/allContacts/1";
    }

    @GetMapping(value = "/allContacts/{id}")
    public String managePages(@PathVariable Long id){
        return "restContactViewer";
//        return new ModelAndView("contactViewer" , "contacts", contactDao.findContactsFromIdAndWithLimit(id * 2, 2L));
    }



}

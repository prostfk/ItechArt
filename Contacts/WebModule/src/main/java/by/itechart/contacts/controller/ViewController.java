package by.itechart.contacts.controller;

import by.itechart.contacts.dao.AddressDao;
import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.DocumentDao;
import by.itechart.contacts.dao.PhoneDao;
import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.Document;
import by.itechart.contacts.model.entity.Phone;
import by.itechart.contacts.model.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ViewController {

    private ContactDao contactDao;
    private DocumentDao documentDao;
    private AddressDao addressDao;
    private PhoneDao phoneDao;

    public ViewController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
        addressDao = new AddressDao();
        phoneDao = new PhoneDao();
    }

    @GetMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping(value = "/contact/{id}/uploadDocument")
    public ModelAndView uploadDocument(@PathVariable Long id) {
        if (contactDao.findContactById(id) != null) {
            return new ModelAndView("uploadDocument", "id", id);
        } else {
            return new ModelAndView("/error", "message", "No such user! Check the link");
        }
    }

    @GetMapping(value = "/contact/sendEmail")
    public String sendEmailPage() {
        return "sendEmail";
    }

    @GetMapping(value = "/contact/search")
    public String getSearchPage() {
        return "searchForm";
    }

    @GetMapping(value = "/documents")
    public String getDocuments() {
        return "documents";
    }

    @GetMapping(value = "/contact/addContact")
    public ModelAndView addNewContact() {
        ModelAndView modelAndView = new ModelAndView("addContact", "contact", new Contact());
        modelAndView.addObject("phone", new Phone());
        return modelAndView;
    }


    @GetMapping(value = "/contact/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        Contact contactById = contactDao.findContactById(id);
        ModelAndView modelAndView = new ModelAndView("editContact", "contact", contactDao.findContactById(id));
        Phone phoneByContactId = phoneDao.findPhoneByContactId(contactById.getId());
        if (phoneByContactId == null) {
            phoneByContactId = new Phone();
        }
        System.out.println(phoneByContactId.getId());
        modelAndView.addObject("phone", phoneByContactId);
        return modelAndView;
    }

    @GetMapping(value = "/contact/{id}/addAddress")
    public ModelAndView addNewAddress(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("addAddress", "address", new Address());
        modelAndView.addObject("id", id);
        return modelAndView;
    }



}

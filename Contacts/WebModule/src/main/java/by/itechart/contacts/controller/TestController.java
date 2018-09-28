package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.PhoneDao;
import by.itechart.contacts.model.entity.Address;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.ContactField;
import by.itechart.contacts.model.entity.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    private ContactDao contactDao;
    private PhoneDao phoneDao;

    public TestController() {
        phoneDao = new PhoneDao();
        contactDao = new ContactDao();
    }


    @GetMapping(value = "/test")
    public String testPage(){
        return "test";
    }

}

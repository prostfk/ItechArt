package by.itechart.contacts.controller;

import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private ContactRepository contactRepository;

    



}

package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id){
        return new ModelAndView("editContact", "contact", contactDao.findContactById(id));
    }

    @PostMapping(value = "/edit/{id}")
    @ResponseBody
    public Contact processEditing(@PathVariable Long id, Contact contact){
        return contactDao.update(id, contact);
    }

}

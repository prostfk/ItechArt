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
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
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

    @GetMapping(value = "/contact/{id}/editAddress")
    public ModelAndView editAddress(@PathVariable Long id){
        Contact byId = contactDao.findById(id);
        if (byId!=null | byId.getAddressId()!=0){
            return new ModelAndView("editAddress", "id",byId.getAddressId());
        }
        System.out.println(byId);
        return new ModelAndView("error");
    }

    @GetMapping(value = "/editAddress/{id}")
    public ModelAndView redirectToEditAddressPage(@PathVariable Long id){
        Contact contactByAddressId = contactDao.findContactByAddressId(id);
        if (contactByAddressId!=null){
            return new ModelAndView(String.format("redirect:/contact/%d/editAddress", contactByAddressId.getId()));
        }
        return new ModelAndView("error", "message", "check url");
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
        Contact byId = contactDao.findById(id);
        if (byId.getAddressId()!=0){
            ModelAndView modelAndView = new ModelAndView("viewAddress", "id", byId.getAddressId());
            modelAndView.addObject("contactId",byId.getId());
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("addAddress", "id", id);
            modelAndView.addObject("address", new Address());
            return modelAndView;
        }
    }

    @GetMapping(value = "/{view}.html")
    public String returnView(@PathVariable String view){
        return view;
    }

    @GetMapping(value = "/error")
    public ModelAndView sendError(@RequestParam String message){
        return new ModelAndView("error", "message", message);
    }

    @GetMapping(value = "/{name}.{format}")
    public ResponseEntity<Void> getCss(HttpServletResponse response, @PathVariable String name, @PathVariable String format){
        response.setContentType(String.format("text/%s", format));
        File path = new File(String.format("WebModule/src/main/resources/static/%s.%s", name, format));
        try {
            InputStream inputStream = new FileInputStream(path);
            IOUtils.copy(inputStream, response.getOutputStream());
            IOUtils.closeQuietly(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}

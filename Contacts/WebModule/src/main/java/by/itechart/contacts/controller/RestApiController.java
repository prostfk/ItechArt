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
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "/rest")
@RestController
public class RestApiController {

    private ContactDao contactDao;
    private DocumentDao documentDao;
    private AddressDao addressDao;
    private PhoneDao phoneDao;

    public RestApiController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
        addressDao = new AddressDao();
        phoneDao = new PhoneDao();
    }

    @GetMapping(value = "/contact/{id}/delete")
    public Contact deleteContact(@PathVariable Long id) {
        return contactDao.delete(id);
    }

    @GetMapping(value = "/contact/{id}")
    public Contact findContact(@PathVariable Long id) {
        return contactDao.findContactById(id);
    }

    @GetMapping(value = "/searchContact")
    public List<Contact> searchContacts(@RequestParam("type") String type, @RequestParam("value") String value) {
        return contactDao.search(type, value);
    }

    @GetMapping(value = "/allContacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List findAll() {
        return contactDao.findAll();
    }

    @GetMapping(value = "/documents")
    public List findDocuments() {
        return documentDao.findAll();
    }

    @PostMapping(value = "/contact/{id}/addAddress")
    public Address submit(@PathVariable Long id, Address address) {
        addressDao.save(address);
        Long addressId = addressDao.findLastId();
        contactDao.addAddressToContact(id, addressId);
        return address;
    }

    @GetMapping(value = "/address/{id}")
    public Address findAddress(@PathVariable Long id) {
        return addressDao.findById(id);
    }

    @GetMapping(value = "/check")
    public Long checkId() {
        return addressDao.findLastId();
    }

    @PostMapping(value = "/contact/sendEmail")
    public String sendEmail(@RequestParam String subject, @RequestParam String message, @RequestParam String email) {
        EmailUtil.sendMail(email, subject, message);
        return String.format("%s/%s/%s", email, subject, message);
    }

    @GetMapping(value = "/getNumberOfPage")
    public String number(HttpServletRequest request) {
        char[] chars = request.getRequestURL().toString().toCharArray();
        return chars[chars.length - 1] + "";
    }

    @PostMapping(value = "/contact/{contactId}/edit")
    public Contact processEditing(@PathVariable Long contactId, Contact contact, Phone phone) {
        if (phone.getId() != null) {
            phoneDao.update(phone.getId(), phone);
        } else {
            phoneDao.save(phone);
        }
        return contactDao.update(contactId, contact);
    }

    @PostMapping(value = "/addContact")
    public Contact processAdding(@Valid Contact contact, Phone phone, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("ERROR");
            return null;
        }
        contactDao.save(contact);
        Long lastId = contactDao.findLastId();
        phone.setContactId(lastId);
        phoneDao.save(phone);
        return contact;
    }

    @PostMapping(value = "/editAddress")
    public Address editAddress(@ModelAttribute Address address) {
        System.out.println(address);
        try {
            addressDao.update(address.getId(), address);
        } catch (Exception e) {
            addressDao.save(address);
        }
        return address;
    }

    @PostMapping(value = "/contact/{id}/document/upload")
    public Document uploadPost(@PathVariable Long id, MultipartFile file) {
        String filePath = String.format("src/main/webapp/resources/pics/%d/", id);
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File fileObj = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
//            fileObj = new File(fileObj.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileObj));
            stream.write(bytes);
            stream.flush();
            stream.close();
            Document document = new Document("/resources/pics/" + file.getOriginalFilename(), id, file.getOriginalFilename());
            documentDao.save(document);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/searchPhone")
    public List findPhones(@RequestParam("type") String type, @RequestParam("value") String value) {
        List<Phone> phonesByParameter = phoneDao.findPhonesByParameter(type, value);
        System.out.println("phonesByParameter = " + phonesByParameter);
        return phonesByParameter;
    }

    @GetMapping(value = "/contact/{id}/phone")
    public Phone findPhoneByContact(@PathVariable Long id) {
        return phoneDao.findPhoneByContactId(id);
    }

}

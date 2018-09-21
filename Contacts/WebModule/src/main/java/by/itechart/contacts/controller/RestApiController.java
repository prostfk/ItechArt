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
import org.apache.log4j.Logger;
import org.json.JSONObject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/rest")
@RestController
public class RestApiController {

    private ContactDao contactDao;
    private DocumentDao documentDao;
    private AddressDao addressDao;
    private PhoneDao phoneDao;
    private static final Logger LOGGER = Logger.getLogger(RestApiController.class);

    public RestApiController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
        addressDao = new AddressDao();
        phoneDao = new PhoneDao();
    }

    @GetMapping(value = "/contact/{id}/delete")
    public Object deleteContact(@PathVariable Long id) {
        return validate(contactDao.delete(id), "no such contact");
    }

    @GetMapping(value = "/contact/{id}")
    public Object findContact(@PathVariable Long id) {
        return validate(contactDao.findById(id), "no such contact");
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

    @GetMapping(value = "/contact/{id}/full")
    public Object fullInfo(@PathVariable Long id){
        return validate(contactDao.findContactWithAddressById(id), "no such data");
    }

    @PostMapping(value = "/contact/{id}/addAddress")
    public Address submit(@PathVariable Long id, Address address) {
        addressDao.save(address);
        Long addressId = addressDao.findLastId();
        contactDao.addAddressToContact(id, addressId);
        return address;
    }

    @GetMapping(value = "/contact/{id}/address")
    public Object findAddressByUser(@PathVariable Long id) {
        return validate(addressDao.findAddressByUserId(id), "no data. Check url");
    }

    @GetMapping(value = "/address/{id}")
    public Object findAddress(@PathVariable Long id) {
        return validate(addressDao.findById(id), "no such address");
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
    public Object processEditing(@PathVariable Long contactId, Contact contact, Phone phone) {
        phone.setContactId(contactId);
        if (phone.getId() != null) {
            phoneDao.update(phone.getId(), phone);
        } else {
            phoneDao.save(phone);
        }
        return validate(contactDao.update(contactId, contact), "check your data");
    }

    @PostMapping(value = "/addContact")
    public Object processAddingContact(@Valid Contact contact, Phone phone, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("ERROR");
            return validate(null, "Your data did't pass the validator. Check your data!");
        }
        contactDao.save(contact);
        Contact baseContact = contactDao.findContactByNameAndSurname(contact.getName(), contact.getSurname());
        phone.setContactId(baseContact.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("contact", contact);
        data.put("phone", phone);
        return data;
    }

    @PostMapping(value = "/editAddress")
    public Object editAddress(@ModelAttribute Address address) {
        Address update = addressDao.update(address.getId(), address);
        if (update != null) {
            return update;
        } else {
            return validate(addressDao.save(address), "server error");
        }
    }

    @PostMapping(value = "/contact/{id}/document/upload")
    public Document uploadPost(@PathVariable Long id, MultipartFile file) {
        String filePath = String.format("WebModule/src/main/resources/static/userFiles/%d/", id);
        File dir = new File(filePath);
        if (!dir.exists()) {
            boolean mkdir = dir.mkdir();
        }
        File fileObj = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
//            fileObj = new File(fileObj.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileObj));
            stream.write(bytes);
            stream.flush();
            stream.close();
            Document document = new Document(String.format("/userFiles/%d/%s",id,file.getOriginalFilename()), id, file.getOriginalFilename());
            documentDao.save(document);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
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
    public Object findPhoneByContact(@PathVariable Long id) {
        return validate(phoneDao.findPhoneByContactId(id), "no such data");
    }

    private Object validate(Object object, String message) {
        if (object != null) {
            return object;
        }
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("error", message);
        return stringStringHashMap;
    }

}

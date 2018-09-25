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
import by.itechart.contacts.model.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        return notNullValidation(contactDao.delete(id), "no such contact");
    }

    @GetMapping(value = "/contact/{id}")
    public Object findContact(@PathVariable Long id) {
        return notNullValidation(contactDao.findById(id), "no such contact");
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
    public Object fullInfo(@PathVariable Long id) {
        return notNullValidation(contactDao.findContactWithAddressById(id), "no such data");
    }

    @PostMapping(value = "/contact/{id}/addAddress")
    public Object submit(@PathVariable Long id, Address address) {
        if (!address.getHouse().equals("") && !address.getCountry().equals("") && !address.getCity().equals("")) {
            addressDao.save(address);
            Long addressId = addressDao.findLastId();
            contactDao.addAddressToContact(id, addressId);
            return address;
        }
        return notNullValidation(null, "Check your data!");
    }

    @GetMapping(value = "/contact/{id}/address")
    public Object findAddressByUser(@PathVariable Long id) {
        return notNullValidation(addressDao.findAddressByUserId(id), "no data. Check url");
    }

    @GetMapping(value = "/address/{id}")
    public Object findAddress(@PathVariable Long id) {
        return notNullValidation(addressDao.findById(id), "no such address");
    }

    @GetMapping(value = "/check")
    public Long checkId() {
        return addressDao.findLastId();
    }

    @PostMapping(value = "/contact/sendEmail")
    public Object sendEmail(@RequestParam String subject, @RequestParam String message, @RequestParam String email) {
        try {
            EmailUtil.sendMail(email, subject, message);
            return JsonUtil.getJson(new String[]{"Status"}, new Object[]{String.format("Email '%s' was successfully sent on address '%s'", message, email)});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return notNullValidation(null, "Invalid email");
        }
    }

    @GetMapping(value = "/getNumberOfPage")
    public String number(HttpServletRequest request) {
        char[] chars = request.getRequestURL().toString().toCharArray();
        return chars[chars.length - 1] + "";
    }

    @PostMapping(value = "/contact/{contactId}/edit")
    public Object processEditing(@PathVariable Long contactId, Contact contact, Phone phone) {
        phone.setContactId(contactId);
        Phone phoneByContactId = phoneDao.findPhoneByContactId(contactId);
        if (phoneByContactId != null && !phone.getNumber().equals("")&&!phone.getCountryCode().equals("")) {
            phoneDao.update(phoneByContactId.getContactId(), phone);
        } else {
            phoneDao.save(phone);
        }
        return notNullValidation(contactDao.update(contactId, contact), "check your data");
    }

    @PostMapping(value = "/addContact")
    public Object postAddContact(Contact contact, Phone phone) {
        if (contact != null & contact.getName() != null & contact.getSurname() != null & !contact.getSurname().equals("") & !contact.getName().equals("")) {
            contactDao.save(contact);
            Contact base = contactDao.findContactByNameAndSurname(contact.getName(), contact.getSurname());
            if (phone != null & !phone.getCountryCode().equals("") & !phone.getNumber().equals("")){
                phone.setContactId(base.getId());
                phoneDao.save(phone);
            }
            return base;
        }
        return notNullValidation(null, "You did't pass the validator");
    }

    @PostMapping(value = "/editAddress")
    public Object editAddress(@ModelAttribute Address address) {
        Address update = addressDao.update(address.getId(), address);
        if (update != null) {
            return update;
        } else {
            return notNullValidation(addressDao.save(address), "server error");
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
            Document document = new Document(String.format("/userFiles/%d/%s", id, file.getOriginalFilename()), id, file.getOriginalFilename());
            documentDao.save(document);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @GetMapping(value = "/contact/findContactByEmail")
    public Object findContactByEmail(@RequestParam String email) {
        return notNullValidation(contactDao.findContactByEmail(email), "No such contact");
    }

    @GetMapping(value = "/searchPhone")
    public List findPhones(@RequestParam("type") String type, @RequestParam("value") String value) {
        List<Phone> phonesByParameter = phoneDao.findPhonesByParameter(type, value);
        System.out.println("phonesByParameter = " + phonesByParameter);
        return phonesByParameter;
    }

    @GetMapping(value = "/contact/{id}/phone")
    public Object findPhoneByContact(@PathVariable Long id) {
        return notNullValidation(phoneDao.findPhoneByContactId(id), "no such data");
    }

    @GetMapping(value = "/contacts")
    public List<Contact> getContacts(@RequestParam Long current, @RequestParam Long count) {
        return contactDao.findContactsFromIdAndWithLimit(current * count - count, count);
    }

    private Object notNullValidation(Object object, String message) {
        if (object != null) {
            return object;
        }
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("error", message);
        return stringStringHashMap;
    }

}

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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/rest")
@RestController
public class RestApiController {

    private ContactDao contactDao;
    private DocumentDao documentDao;
    private AddressDao addressDao;
    private PhoneDao phoneDao;
    @Value("${user.files.path}")
    private String staticPath;
    @Value("${message.birthday}")
    String birthday;
    @Value("${message.newYear}")
    String newYear;
    @Value("${message.party}")
    String party;
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
    public Object processEditing(@PathVariable Long contactId, Contact contact) {
        return notNullValidation(contactDao.update(contactId, contact), "check your data");
    }

    @PostMapping(value = "/addContact")
    public Object postAddContact(Contact contact) {
        System.out.println(contact);
        if (contact != null & contact.getName() != null & contact.getSurname() != null & !contact.getSurname().equals("") & !contact.getName().equals("")) {
            contactDao.save(contact);
            Contact base = contactDao.findContactByNameAndSurname(contact.getName(), contact.getSurname());
            return base;
        }
        return notNullValidation(null, "You did't pass the validator");
    }

    @PostMapping(value = "/contact/{id}/editAddress")
    public Object editAddress(@PathVariable Long id, Address address) {
        Address baseAddress = addressDao.findAddressByUserId(id);
        if (baseAddress != null) {
            return addressDao.update(baseAddress.getId(), address);
        } else {
            return notNullValidation(null, "No such address");
        }
    }

    @PostMapping(value = "/contact/{id}/document/upload")
    public Object uploadPost(@PathVariable Long id, MultipartFile file, @Value("${user.files.path}") String startPath) {
        String filePath = String.format("%s/userFiles/%d/", startPath, id);
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
            return documentDao.findDocumentByNameAndContactId(file.getOriginalFilename(), id);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return notNullValidation(null, "Something went wrong");
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

    @GetMapping(value = "/contact/{id}/phones")
    public Object findPhoneByContact(@PathVariable Long id) {
        return notNullValidation(phoneDao.findPhonesByContactId(id), "no such data");
    }

    @GetMapping(value = "/contacts")
    public List<Contact> getContacts(@RequestParam Long current, @RequestParam Long count) {
        return contactDao.findContactsFromIdAndWithLimit(current, count);
    }

    @GetMapping(value = "/contact/{id}/documents")
    public Object findDocumentsByUserId(@PathVariable Long id) {
        return notNullValidation(documentDao.findDocumentsByUserId(id), "NO DATA");
    }

    @GetMapping(value = "/sendEmails")
    public Object sendEmailsTo(@RequestParam("To") String to, @RequestParam String message) {
        String[] split = to.split(",");
        List<Contact> contactsByIdList = contactDao.findContactsByIdList(split);
        int successMails = 0;
        message = EmailUtil.getMessage(message, party, newYear, birthday);
        JSONObject json = new JSONObject();
        for (int i = 0; i < split.length; i++) {
            try {
                message = message.replace("{username}", contactsByIdList.get(i).getName());
                EmailUtil.sendMail(contactsByIdList.get(i).getEmail(), String.format("Hey, %s, we have something for you! ", contactsByIdList.get(i).getName()), message);
                successMails++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (successMails == split.length) {
            json.put("status", "ok");
        } else {
            json.put("status", "some email addresses may be invalid");
        }
        return json.toString();
    }

    @PostMapping(value = "/contact/{id}/addPhone")
    public Object addNewPhone(@PathVariable Long id, Phone phone) {
        Contact contact = contactDao.findContactById(id);
        if (contact != null) {
            if (phone.getCountryCode().length() > 0 && phone.getCountryCode().length() < 5 && phone.getNumber().length() > 5 && phone.getNumber().length() < 15) {
                phone.setContactId(id);
                phoneDao.save(phone);
                return phone;
            }
        }
        return notNullValidation(null, "Check your data");

    }

    @GetMapping(value = "/updatedSearch")
    public Object updatedSearch(@RequestParam String param, @RequestParam String value) {
        System.out.println(param);
        System.out.println(value);
        String[] params = param.split(",");
        String[] values = value.split(",");
        return notNullValidation(contactDao.findContactsByFieldsAndValues(params, values), "Something went wrong");
    }

    @GetMapping(value = "/contact/{id}/documents/rename")
    public Object renameDocument(@PathVariable Long id, @RequestParam String name, @RequestParam String newFileName) {
        Document doc = documentDao.findDocumentByNameAndContactId(name, id);
        File file = new File(staticPath + doc.getPath());
        String[] split = doc.getPath().split("/");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            stringBuilder.append("/").append(split[i]);
        }
        stringBuilder.append("/").append(newFileName);
        File newFile = new File(staticPath + stringBuilder.toString());
        JSONObject json = new JSONObject();
        if (!newFile.exists() && file.renameTo(newFile)) {
            doc.setPath(stringBuilder.toString());
            doc.setName(newFileName);
            documentDao.update(doc.getId(), doc);
            json.put("status", "success");
        } else {
            json.put("status", "error, such file already exists");
        }
        return json.toString();

    }

    @GetMapping(value = "/download")
    public void download(@RequestParam String path, HttpServletResponse response) {
        String dataDirectory = String.format("%s/%s", staticPath, path);
        String filename = path.split("/")[path.split("/").length - 1];
        Path file = Paths.get(dataDirectory);
        if (Files.exists(file)) {
            response.setContentType(URLConnection.guessContentTypeFromName(filename));
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @GetMapping(value = "/email/preview")
    public Object getText(@RequestParam String text) {
        JSONObject json = new JSONObject();
        switch (text) {
            case "message.birthday":
                json.put("message", birthday);
                break;
            case "message.party":
                json.put("message", party);
                break;
            case "message.newYear":
                json.put("message", newYear);
                break;
            default:
                json.put("error", "No such data");
                break;
        }
        return json.toString();
    }

    @PostMapping(value = "/contact/removePhone/{id}")
    public Object removePhone(@PathVariable Long id){
        Phone byId = phoneDao.findById(id);
        if (byId!=null){
            phoneDao.delete(id);
        }
        return notNullValidation(byId, "No such data");
    }

    @GetMapping(value = "/contact/{contactId}/phone/{id}")
    public Object findPhoneById(@PathVariable Long id){
        Phone byId = phoneDao.findById(id);
        return notNullValidation(byId,"No such data");
    }

    @GetMapping(value = "/contact/{id}/file/{fileId}/remove")
    public Object removeFile(@PathVariable Long id, @PathVariable Long fileId){
        return notNullValidation(documentDao.delete(fileId), "No such data");
    }

    @PostMapping(value = "/contact/{id}/editPhone/{phoneId}")
    public Object editPhone(@PathVariable Long id, @PathVariable Long phoneId, Phone phone){
        if (phone.getContactId() == id){
            phoneDao.update(phoneId,phone);
            return phone;
        }
        return notNullValidation(null, "Something went wrong");
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

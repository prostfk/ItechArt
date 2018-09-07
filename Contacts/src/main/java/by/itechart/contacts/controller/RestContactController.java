package by.itechart.contacts.controller;

import dao.ContactDao;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestContactController {

    private ContactDao contactDao;

    public RestContactController() {
        contactDao = new ContactDao();
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public String deleteContact(@PathVariable Long id){
        contactDao.delete(id);
        return JSONObject.quote("Status: ok");
    }

}

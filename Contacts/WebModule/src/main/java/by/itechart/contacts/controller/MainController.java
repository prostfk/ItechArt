package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.dao.DocumentDao;
import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.Document;
import by.itechart.contacts.model.util.EmailUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private ContactDao contactDao;
    private DocumentDao documentDao;

    public MainController() {
        contactDao = new ContactDao();
        documentDao = new DocumentDao();
    }

    @GetMapping(value = "/")
    public String getIndexPage(){return "index.html";}

    @PutMapping(value = "/editContact/{id}")
    @ResponseBody
    public Contact editContact(@PathVariable Long id, Contact contact){
        return contactDao.update(id, contact);
    }

    @GetMapping(value = "/upload/{id}")
    public ModelAndView uploadDocument(@PathVariable Long id){
        if (contactDao.findContactById(id)!=null){
            return new ModelAndView("uploadDocument", "id", id);
        }else{
            return new ModelAndView("/error", "message", "No such user! Check the link");
        }
    }

    @PostMapping(value = "/upload/{id}")
    public String uploadPost(@PathVariable Long id, MultipartFile file){
        String filePath = String.format("src/main/webapp/resources/pics/%d/", id);
        File dir = new File(filePath);
        if (!dir.exists()){
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
            documentDao.save(new Document(
                    "/resources/pics/" + file.getOriginalFilename(),
                    id, file.getOriginalFilename()
            ));
            return "redirect:/docs";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/all";
    }

    @GetMapping(value = "/sendEmail")
    public String sendEmailPage(){
        return "sendEmail";
    }

    @PostMapping(value = "/sendEmail")
    @ResponseBody
    public String sendEmail(@RequestParam String subject, @RequestParam String message, @RequestParam String email){
        EmailUtil.sendMail(email, subject, message);
        return String.format("%s/%s/%s",email,subject,message);
    }

    @GetMapping(value = "/getNumberOfPage")
    @ResponseBody
    public String number(HttpServletRequest request){
        char[] chars = request.getRequestURL().toString().toCharArray();
        return chars[chars.length-1] + "";
    }

    @GetMapping(value = "/searchContact")
    public String getSearchPage(){
        return "searchForm";
    }

    @GetMapping(value = "/documents")
    public String getDocuments(){
        return "documents";
    }


}

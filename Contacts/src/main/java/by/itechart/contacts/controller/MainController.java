package by.itechart.contacts.controller;

import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.Document;
import by.itechart.contacts.repository.ContactRepository;
import by.itechart.contacts.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping(value = "/all")
    @ResponseBody
    public List allContacts(){
        return contactRepository.findAll();
    }

    @GetMapping(value = "/add")
    public ModelAndView addContact(){
        return new ModelAndView("addContact", "contact",new Contact());
    }

    @PostMapping(value = "/add")
    public String process(Contact contact){
        contactRepository.save(contact);
        return "redirect:/all";
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editContact(@PathVariable Long id){
        Contact contactById = contactRepository.findContactById(id);
        if (contactById!=null){
            return new ModelAndView("editContact","contact", contactById);
        }
        return new ModelAndView("redirect:/all");
    }

    @PostMapping(value = "/edit/{id}")
    public String processEdit(@PathVariable Long id, Contact contact){
        contactRepository.save(contact);
        return "redirect:/all";
    }

    @GetMapping(value = "/upload/{id}")
    public ModelAndView uploadFile(@PathVariable Long id){
        Contact contactById = contactRepository.findContactById(id);
        if (contactById!=null){
            return new ModelAndView("uploadDocument","contact", contactById);
        }
        return null;
    }

    @PostMapping(value = "/upload/{id}")
    public String uploadPost(@PathVariable Long id, MultipartFile file){
        String filePath = "src/main/webapp/resources/";
        File fileObj = new File(filePath + file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();
//            fileObj = new File(fileObj.getAbsolutePath());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileObj));
            stream.write(bytes);
            stream.flush();
            stream.close();
            documentRepository.save(new Document(
                    "/resources/pics/" + file.getOriginalFilename(),
                    contactRepository.findContactById(id),
                    file.getOriginalFilename()
            ));
            return "redirect:/docs";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/all";
    }

    @GetMapping(value = "/docs")
    @ResponseBody
    public List docs(){
        return documentRepository.findAll();
    }

}

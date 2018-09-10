package by.itechart.contacts.controller;

import by.itechart.contacts.dao.ContactDao;
import by.itechart.contacts.model.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private ContactDao contactDao;

    public MainController() {
        contactDao = new ContactDao();
    }

    @GetMapping(value = "/addContact")
    public ModelAndView addNewContact(){
        return new ModelAndView("addContact", "contact", new Contact());
    }

    @PostMapping(value = "/addContact")
    public String processAdding(Contact contact){
        contactDao.save(contact);
        return "redirect:/";
    }

    @PutMapping(value = "/edit/{id}")
    @ResponseBody
    public Contact editContact(@PathVariable Long id, Contact contact){
        return contactDao.update(id, contact);
    }

    @GetMapping(value = "/upload/{id}")
    public ModelAndView uploadDocument(@PathVariable Long id){
        return new ModelAndView("uploadDocument", "id", id);
    }

//    @PostMapping(value = "/upload/{id}")
//    public String uploadPost(@PathVariable Long id, MultipartFile file){
//        String filePath = "src/main/webapp/resources/pics/";
//        File fileObj = new File(filePath + file.getOriginalFilename());
//        try {
//            byte[] bytes = file.getBytes();
////            fileObj = new File(fileObj.getAbsolutePath());
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileObj));
//            stream.write(bytes);
//            stream.flush();
//            stream.close();
//            documentRepository.save(new Document(
//                    "/resources/pics/" + file.getOriginalFilename(),
//                    contactRepository.findAddressById(id),
//                    file.getOriginalFilename()
//            ));
//            return "redirect:/docs";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/all";
//    }


}

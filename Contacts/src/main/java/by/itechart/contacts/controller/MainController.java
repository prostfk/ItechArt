package by.itechart.contacts.controller;

import dao.ContactDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private ContactDao contactDao;

    public MainController() {
        contactDao = new ContactDao();
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public List findAll(){
        return contactDao.findAll();
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
//                    contactRepository.findContactById(id),
//                    file.getOriginalFilename()
//            ));
//            return "redirect:/docs";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/all";
//    }


}

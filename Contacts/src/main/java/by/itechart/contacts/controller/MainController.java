package by.itechart.contacts.controller;

import by.itechart.contacts.model.entity.Contact;
import by.itechart.contacts.model.entity.Document;
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

//@Controller
public class MainController {






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

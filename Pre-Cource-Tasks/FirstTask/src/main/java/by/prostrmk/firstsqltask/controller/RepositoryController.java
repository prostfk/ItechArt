package by.prostrmk.firstsqltask.controller;

import by.prostrmk.firstsqltask.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/repo")
public class RepositoryController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/findAll")
    @ResponseBody
    public List findAll(){
        return studentRepository.findAll();
    }


}

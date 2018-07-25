package by.prostrmk.firstsqltask.controller;

import by.prostrmk.firstsqltask.model.dao.StudentsDao;
import by.prostrmk.firstsqltask.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    StudentsDao studentsDao;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView getIndexPage(){
        List<Student> all = studentsDao.findAll();
        return new ModelAndView("index","users", all);
    }

    @RequestMapping(value = "/passed", method = RequestMethod.GET)
    public ModelAndView passedStudents(){
        List<String> studentsWhoPassedExams = studentsDao.findStudentsWhoPassedExams();
        return new ModelAndView("index", "users", studentsWhoPassedExams);
    }

    @RequestMapping(value = "/good",method = RequestMethod.GET)
    @ResponseBody
    public String goodStudents(){
        return "Count of good students: " + studentsDao.findCountOfGoodStudents();
    }

    @RequestMapping(value = "/auto",method = RequestMethod.GET)
    @ResponseBody
    public String studentsWhoPassedAutomatically(){
        return "Count of good students: " + studentsDao.findCountOfStudentWhoPassedExamAutomatically();
    }

    @RequestMapping(value = "/middle",method = RequestMethod.GET)
    @ResponseBody
    public String middleValueOfDBManagement(){
        return "Middle value: " + studentsDao.findMiddleValueOfDataBaseManagment();
    }

}

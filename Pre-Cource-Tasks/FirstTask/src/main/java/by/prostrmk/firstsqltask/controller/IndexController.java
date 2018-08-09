package by.prostrmk.firstsqltask.controller;

import by.prostrmk.firstsqltask.model.dao.StudentsDao;
import by.prostrmk.firstsqltask.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    StudentsDao studentsDao;

    @RequestMapping(value = {"/all", "/"}, method = RequestMethod.GET)
    public ModelAndView getAllStudents() {
        List<Student> all = studentsDao.findAll();
        return new ModelAndView("index", "users", all);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView executeQuery(@PathVariable String id) {
        ModelAndView modelAndView = null;
        switch (id) {
            case "1":
                modelAndView = new ModelAndView("index", "users", Collections.singletonList("Count Of Good Students: " + studentsDao.findCountOfGoodStudents()));
                break;
            case "2":
                modelAndView = new ModelAndView("index", "users", Collections.singletonList("Count Of Good Students: " + studentsDao.findCountOfGoodStudents()));
                break;
            case "3":
                modelAndView = new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Count Of Student Who Passed Exam Automatically: " + studentsDao.findCountOfStudentWhoPassedExamAutomatically())));
                break;
            case "4":
                modelAndView = new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Middle Value Of Data Base Management: " + studentsDao.findMiddleValueOfDataBaseManagement())));
                break;
            case "5":
                modelAndView = new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Students Who Did Not Pass The Exam: " + studentsDao.findStudentsWhoDidNotPassTheExam())));
                break;
            case "6":
                modelAndView = new ModelAndView("index", "users", studentsDao.findTeacherWhoProfessMoreThanOneCourse());
                break;
            case "7":
                modelAndView = new ModelAndView("index", "users", studentsDao.findPeopleWhoRetriedToPassExam());
                break;
            case "8":
                modelAndView = new ModelAndView("index", "users", studentsDao.findFiveSmartest());
                break;
            case "9":
                modelAndView = new ModelAndView("index", "users", studentsDao.findBestTeacher());
                break;
            case "10":
                modelAndView = new ModelAndView("index", "users", studentsDao.findSmartestInMath());
                break;
            default:
                modelAndView = new ModelAndView("index", "users", "error");
                break;
        }
        return modelAndView;
    }



}

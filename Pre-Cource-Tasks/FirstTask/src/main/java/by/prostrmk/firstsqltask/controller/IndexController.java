package by.prostrmk.firstsqltask.controller;

import by.prostrmk.firstsqltask.model.dao.StudentsDao;
import by.prostrmk.firstsqltask.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAllStudents(){
        List<Student> all = studentsDao.findAll();
        return new ModelAndView("index", "users",all);
    }

    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public ModelAndView studentsWhoPassedExams(){
        List<String> all = studentsDao.findStudentsWhoPassedExams();
        return new ModelAndView("index","users", all);
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public ModelAndView countOfGoodStudents(){
        List<String> studentsWhoPassedExams = Collections.singletonList("Count Of Good Students: " + studentsDao.findCountOfGoodStudents());
        return new ModelAndView("index", "users", studentsWhoPassedExams);
    }

    @RequestMapping(value = "/3",method = RequestMethod.GET)
    public ModelAndView countOfStudentWhoPassedExamAutomatically(){
        return new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Count Of Student Who Passed Exam Automatically: " + studentsDao.findCountOfStudentWhoPassedExamAutomatically())));
    }

    @RequestMapping(value = "/4",method = RequestMethod.GET)
    public ModelAndView middleValueOfDataBaseManagement(){
        return new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Middle Value Of Data Base Management: " + studentsDao.findMiddleValueOfDataBaseManagement())));
    }

    @RequestMapping(value = "/5",method = RequestMethod.GET)
    public ModelAndView studentsWhoDidNotPassTheExam(){
        return new ModelAndView("index", "users", new ArrayList<String>(Collections.singleton("Students Who Did Not Pass The Exam: " + studentsDao.findStudentsWhoDidNotPassTheExam())));
    }

    @RequestMapping(value = "/6",method = RequestMethod.GET)
    public ModelAndView teacherWhoProfessMoreThanOneCourse(){
        return new ModelAndView("index", "users",studentsDao.findTeacherWhoProfessMoreThanOneCourse());
    }

    @RequestMapping(value = "/7", method = RequestMethod.GET)
    public ModelAndView peopleWhoRetriedToPassExam(){
        return new ModelAndView("index", "users", studentsDao.findPeopleWhoRetriedToPassExam());
    }

    @RequestMapping(value = "/8", method = RequestMethod.GET)
    public ModelAndView fiveSmartest(){
        return new ModelAndView("index", "users", studentsDao.findFiveSmartest());
    }

    @RequestMapping(value = "/9", method = RequestMethod.GET)
    public ModelAndView bestTeacher(){
        return new ModelAndView("index", "users", studentsDao.findBestTeacher());
    }

    @RequestMapping(value = "/10", method = RequestMethod.GET)
    public ModelAndView smartestInMath(){
        return new ModelAndView("index", "users", studentsDao.findSmartestInMath());
    }


}

package by.prostrmk.firstsqltask.model.dao;

import by.prostrmk.firstsqltask.model.entity.Student;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StudentsDao {

    private Connection connection;

    public StudentsDao(Connection connection) {
        this.connection = connection;
    }

    public StudentsDao(){
        connection = AbstractDao.defaultConnection();
    }

    public List<Student> findAll()  {
        var list = new ArrayList<Student>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students.student");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthDate = simpleDateFormat.parse(resultSet.getString("birth_date"));
                boolean sex = resultSet.getBoolean("sex");
                boolean hostelLive = resultSet.getBoolean("hostel_live");
                Student student = new Student(id, first_name, lastName, birthDate, sex, hostelLive);
                list.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    1
    public List<String> findStudentsWhoPassedExams() {
        var list = new ArrayList<String>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, result FROM students.student JOIN students.exam_result ON student.id = exam_result.student_id WHERE exam_result.result > 2 ORDER BY result DESC");
            while (resultSet.next()){
                list.add(String.format("Name: %s, Last name: %s, Result: %d", resultSet.getString("first_name"),resultSet.getString("last_name"), resultSet.getInt("result")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    2
    public int findCountOfGoodStudents(){
        int count = 0;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT result FROM students.student JOIN students.exam_result ON student.id = exam_result.student_id WHERE exam_result.result > 3");
            resultSet.last();
            count = resultSet.getRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

//    3
    public int findCountOfStudentWhoPassedExamAutomatically(){
        int count = 0;
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT result FROM students.student JOIN students.student_result ON student.id = student_result.student_id WHERE result > 2;");
            resultSet.last();
            count = resultSet.getRow();
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

//    4
    public double findMiddleValueOfDataBaseManagement(){
        double value = .0;
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT AVG(result) FROM students.student_result WHERE training_course_id = 1");
            if (resultSet.next()) {
                value = resultSet.getDouble("AVG(result)");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
//    5 (NO GRAPH ROW)
    public List<String> findStudentsWhoDidNotPassTheExam(){
        var list = new ArrayList<String>();
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT first_name,last_name, students.student_result.result FROM students.student JOIN students.student_result ON student.id = student_result.student_id WHERE result < 3 AND training_course_id = 2;");
            while (resultSet.next()){
                list.add(String.format("Name: %s, Last name: %s, Result: %d", resultSet.getString("first_name"),resultSet.getString("last_name"), resultSet.getInt("result")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    6
    public List<String> findTeacherWhoProfessMoreThanOneCourse(){
        var list = new ArrayList<String>();
        try{
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT students.techer.first_name,students.techer.last_name, students.techer.id FROM students.techer JOIN students.training_course ON techer.id = training_course.teacher_id GROUP BY first_name,last_name HAVING COUNT(*) > 1");
            while (resultSet.next()){
                list.add(String.format("Id: %d, First name: %s, Last name: %s",resultSet.getInt("id"), resultSet.getString("first_name"),resultSet.getString("last_name")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

//    7
    public List<String> findPeopleWhoRetriedToPassExam(){
        ArrayList<String> strings = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT first_name, last_name, student_id FROM students.student JOIN students.exam_result result on student.id = result.student_id GROUP BY first_name, last_name, student_id HAVING COUNT(exam_id) > 1;");
            while (resultSet.next()){
                strings.add(String.format("Id: %d, Name: %s, Surname: %s", resultSet.getInt("student_id"), resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

//    8
    public List<String> findFiveSmartest(){
        List<String> strings = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT first_name, last_name FROM students.student JOIN students.exam_result result on student.id = result.student_id ORDER BY result DESC LIMIT 5;");
            while (resultSet.next()){
                strings.add(String.format("Name: %s, Surname: %s", resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }

//    9
    public List<String> findBestTeacher(){
        List<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT DISTINCT MAX(s.maxres), s.last_name, id FROM (SELECT AVG(result) as maxres, last_name, t.id from students.student_result JOIN students.exam e on student_result.exam_id = e.id JOIN students.techer t on e.teacher_id = t.id group by last_name) as s group by id LIMIT 1");
            if (resultSet.next()){
                list.add(String.format("Id:%d, Last name: %s, Average result: %f", resultSet.getInt("id"), resultSet.getString("last_name"),resultSet.getDouble("MAX(s.maxres)")));
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return list;
    }

    public List<String> findSmartestInMath(){
        List<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT first_name, last_name, date, training_course.name from students.student JOIN students.exam_result on student.id = exam_result.student_id JOIN students.training_course on exam_id = training_course.id JOIN students.exam on exam_result.exam_id = exam.id WHERE training_course.name = 'MATH' order by students.exam.date");
            while(resultSet.next()){
                list.add(String.format("Name: %s, Last name: %s, Date: %s, Subject: %s", resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("date"), resultSet.getString("name")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}

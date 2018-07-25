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
    public double findMiddleValueOfDataBaseManagment(){
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

}

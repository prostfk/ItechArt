package by.prostrmk.firstsqltask.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean sex;
    private boolean hostelLive;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + new SimpleDateFormat("yyyy-MM-dd").format(birthDate)+
                ", sex=" + sex +
                ", hostelLive=" + hostelLive +
                '}';
    }
}

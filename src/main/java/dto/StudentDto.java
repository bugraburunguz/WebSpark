package dto;// Code with ❤

import java.util.List;

//┌───────────────────────────────┐
//│ Created by Buğra Emin BÜRÜNGÜZ│
//│ ───────────────────────────── │
//│ BugraBurunguz@AndroidEdu.IO   │
//│ ───────────────────────────── │
//│ 26.01.2019 - 04:27             │
//└────────────────────────────────
public class StudentDto {

    public List<LessonDto> studentsLessons;
    private String studentName;
    private String studentSurname;
    private String studentDepartment;
    private String degreeType;
    private String studentNumber;


    public StudentDto(String studentName, String studentSurname, String studentDepartment, String degreeType, String studentNumber) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.studentDepartment = studentDepartment;
        this.degreeType = degreeType;
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}

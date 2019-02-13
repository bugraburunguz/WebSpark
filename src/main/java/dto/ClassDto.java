package dto;//┌───────────────────────────────┐

//│ Created by Mehmet Güngörmüş   │
//│ ───────────────────────────── │
//│ themsday@gmail.com            │
//│ ───────────────────────────── │
//│ 02/02/2019 - 02:27             │
//└────────────────────────────────
//ders.java nın ingilizcesi

import java.util.List;

public class ClassDto {
    public String className;
    public int classCredit;
    public int classECTS;
    public String classId;
    public List<StudentDto> cStudents; // students in class

    public ClassDto(String className, int classCredit, int classECTS, String classId) {
        this.className = className;
        this.classCredit = classCredit;
        this.classECTS = classECTS;
        this.classId = classId;

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassCredit() {
        return classCredit;
    }

    public void setClassCredit(int classCredit) {
        this.classCredit = classCredit;
    }

    public int getClassECTS() {
        return classECTS;
    }

    public void setClassECTS(int classECTS) {
        this.classECTS = classECTS;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<StudentDto> getCStudents() {
        return cStudents;
    }

    public void serCStudents(List<StudentDto> cStudents) {
        this.cStudents = cStudents;
    }
}

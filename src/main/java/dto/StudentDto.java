package dto;

import java.util.List;

//┌───────────────────────────────┐
//│ Created by Mehmet Güngörmüş   │
//│ ───────────────────────────── │
//│ themsday@gmail.com            │
//│ ───────────────────────────── │
//│ 02/02/2019 - 03:17             │
//└────────────────────────────────
//ogrenciyi ingilizce yaz ve ekleme yap numara branş gibi
public class StudentDto {

    public String nameSurname;
    public String dep; // department
    public String stuType; // student type
    public int number; //student's number
    public List<Class> stuClass; //student's class

    public StudentDto(String nameSurname,
                      String stuType,
                      String dep,
                      int number) {
        this.nameSurname = nameSurname;
        this.stuType = stuType;
        this.dep = dep;
        this.number = number;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getStuType() {
        return stuType;
    }

    public void setStuType(String stuType) {
        this.stuType = stuType;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ClassDto> getStuClass() {
        return stuClass;
    }

    public void setStuClass(List<StudentDto> stuClass) {
        this.stuClass = stuClass;
    }

    public String getDetailsOfStudent() {
        return "Ad Soyad : " + this.nameSurname + " - " + "Bölüm : " + this.dep + " - " + "Öğrenci Tipi : " + this.stuType + " - " + "Numara : " + this.number;
    }

    public String studentNumber() {
        return String.valueOf(this.number);
    }

    public String studentName() {
        return String.valueOf(this.nameSurname);
    }

    public String studentDep() {
        return String.valueOf(this.dep);
    }

    public String studentType() {
        return String.valueOf(this.stuType);
    }

    public String takeStudentInfo(boolean extraInfo) {
        return "";
    }


}

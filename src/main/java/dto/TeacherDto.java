package dto;

//┌───────────────────────────────┐
//│ Created by Mehmet Güngörmüş   │
//│ ───────────────────────────── │
//│ themsday@gmail.com            │
//│ ───────────────────────────── │
//│ 02/02/2019 - 03:16             │
//└────────────────────────────────
//hoca ekle ve özellikleri numara ders blüm vs
public class TeacherDto {
    public String tNameSurname;
    public String tDep;
    public String tNumber;
    public String tClasses;

    public TeacherDto(String tNameSurname,
                      String tDep,
                      String tNumber,
                      String tClasses) {
        this.tNameSurname = tNameSurname;
        this.tDep = tDep;
        this.tNumber = tNumber;
        this.tClasses = tClasses;
    }

    public String getTNameSurname() {
        return tNameSurname;
    }

    public void setTNameSurname(String tNameSurname) {
        this.tNameSurname = tNameSurname;
    }

    public String gettDep() {
        return tDep;
    }

    public void setTDep(String tDep) {
        this.tDep = tDep;
    }

    public String getTnumber() {
        return tNumber;
    }

    public void setTNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public String getTClasses() {
        return tClasses;
    }

    public void setTClasses(String tClasses) {
        this.tClasses = tClasses;
    }


}

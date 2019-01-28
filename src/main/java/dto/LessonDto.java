package dto;// Code with ❤

import java.util.List;

//┌───────────────────────────────┐
//│ Created by Buğra Emin BÜRÜNGÜZ│
//│ ───────────────────────────── │
//│ BugraBurunguz@AndroidEdu.IO   │
//│ ───────────────────────────── │
//│ 26.01.2019 - 04:27             │
//└────────────────────────────────
public class LessonDto {
    public List<StudentDto> lessonsStudents;
    private String lessonName;
    private int lessonCredit;
    private int lessonAkts;
    private int lessonId;

    public LessonDto(String lessonName, int lessonCredit, int lessonAkts, int lessonId) {
        this.lessonName = lessonName;
        this.lessonCredit = lessonCredit;
        this.lessonAkts = lessonAkts;
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonCredit() {
        return String.valueOf(this.lessonCredit);
    }

    public void setLessonCredit(int lessonCredit) {
        this.lessonCredit = lessonCredit;
    }

    public String getLessonAkts() {
        return String.valueOf(this.lessonAkts);
    }

    public void setLessonAkts(int lessonAkts) {
        this.lessonAkts = lessonAkts;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }
}


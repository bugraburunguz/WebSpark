package main;// Code with ❤

import dto.LessonDto;
import dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerRoute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

//┌───────────────────────────────┐
//│ Created by Buğra Emin BÜRÜNGÜZ│
//│ ───────────────────────────── │
//│ BugraBurunguz@AndroidEdu.IO   │
//│ ───────────────────────────── │
//│ 26.01.2019 - 04:32            │
//└────────────────────────────────
public class MainScreen {

    // TODO: 2019-01-28
    //  ÖĞRENCİYE DERS EKLEME, DERSE ÖĞRENCİ EKLEME YAPILACAK, LİSANS DURUMU BİR ENUMDA TUTULACAK...


    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(MainScreen.class);
        logger.info("Hi guys!!");
        setPort(8080);

        List<StudentDto> studentDtoList = new ArrayList<>();
        List<LessonDto> lessonDtoList = new ArrayList<>();

        studentDtoList.add(new StudentDto("Buğra Emin",
                "Bürüngüz",
                "Software Engeneering",
                "Lisans",
                "B1806090900"));

        studentDtoList.add(new StudentDto("Mehmet",
                "Güngörmüş",
                "Computer Engeneering",
                "Lisans",
                "B1805070800"));

        lessonDtoList.add(new LessonDto("Proje Yönetimi",
                4,
                6,
                12));

        FreeMarkerRoute studentListPageGet = new FreeMarkerRoute("/studentList") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("students", studentDtoList);
                return new ModelAndView(attributes, "studentList.html"); // resources'daki html veya ftl
            }
        };
        get(studentListPageGet);

        FreeMarkerRoute studentAddPageGet = new FreeMarkerRoute("/studentAdd") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("students", studentDtoList);
                return new ModelAndView(attributes, "studentAdd.html"); // resources'daki html veya ftl
            }
        };
        get(studentAddPageGet);

        FreeMarkerRoute studentDetailPageGet = new FreeMarkerRoute("/studentDetail") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("students", studentDtoList);
                return new ModelAndView(attributes, "studentDetail.html"); // resources'daki html veya ftl
            }
        };
        get(studentDetailPageGet);

        FreeMarkerRoute lessonDetailPageGet = new FreeMarkerRoute("/lessonDetail") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("lessonList", lessonDtoList);
                return new ModelAndView(attributes, "lessonDetail.html"); // resources'daki html veya ftl
            }
        };
        get(lessonDetailPageGet);

        FreeMarkerRoute lessonListPageGet = new FreeMarkerRoute("/lessonList") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("lessonList", lessonDtoList);
                return new ModelAndView(attributes, "lessonList.html"); // resources'daki html veya ftl
            }
        };
        get(lessonListPageGet);

        FreeMarkerRoute lessonAddPageGet = new FreeMarkerRoute("/lessonAdd") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("lessonList", lessonDtoList);
                return new ModelAndView(attributes, "lessonAdd.html"); // resources'daki html veya ftl
            }
        };
        get(lessonAddPageGet);
        // ------------------------------------------------------------------------------------- üst kısım hash maplerle alakalı ----------------------------------------------------

        FreeMarkerRoute studentAddPost = new FreeMarkerRoute("/studentAdd") {
            public Object handle(Request req, Response resp) {

                String studentName = req.queryParams("name");
                String studentsurname = req.queryParams("surname");
                String department = req.queryParams("department");
                String degree = req.queryParams("studentDegree");
                String studentNumber = req.queryParams("number");

                studentDtoList.add(new StudentDto(studentName, studentsurname, department, degree, studentNumber));

                resp.redirect("/studentList");
                return null;
            }
        };
        post(studentAddPost);

        FreeMarkerRoute studentDeletePost = new FreeMarkerRoute("/studentList") {
            public Object handle(Request req, Response resp) {

                String selectedStudent = req.queryParams("deletedStudent");
                for (int i = 0; i < studentDtoList.size(); i++) {
                    StudentDto student = studentDtoList.get(i);
                    if (selectedStudent.equals(student.getStudentNumber())) {
                        studentDtoList.remove(i);
                        break;
                    } else {
                        resp.redirect("/studentList");
                    }
                }
                resp.redirect("/studentList");
                return null;
            }
        };
        post(studentDeletePost);

        FreeMarkerRoute selectedLessonDetailPost = new FreeMarkerRoute("/lessonDetail") {
            public Object handle(Request req, Response resp) {
                int lessonIndex = -1;
                int selectedLesson = Integer.parseInt(req.queryParams("lesson_select_radio"));
                for (int i = 0; i < lessonDtoList.size(); i++) {
                    LessonDto lesson = lessonDtoList.get(i);
                    if (selectedLesson == lesson.getLessonId()) {
                        lessonIndex = i;
                        break;
                    }
                }
                LessonDto lessonDetail = lessonDtoList.get(lessonIndex);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("lessonList", lessonDetail);
                return new ModelAndView(attributes, "lessonDetail.html");
            }
        };
        post(selectedLessonDetailPost);

        FreeMarkerRoute selectedStudentsDetailPost = new FreeMarkerRoute("/studentDetail") {
            public Object handle(Request req, Response resp) {
                int studentIndex = -1;
                String selectedStudent = req.queryParams("student_select_checkbox");
                for (int i = 0; i < studentDtoList.size(); i++) {
                    StudentDto student = studentDtoList.get(i);
                    if (selectedStudent.equals(student.getStudentNumber())) {
                        studentIndex = i;
                        break;
                    }
                }
                StudentDto studentDetail = studentDtoList.get(studentIndex);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("students", studentDetail);
                return new ModelAndView(attributes, "studentDetail.html");
            }
        };
        post(selectedStudentsDetailPost);


        FreeMarkerRoute lessonAddPost = new FreeMarkerRoute("/lessonAdd") {
            public Object handle(Request req, Response resp) {

                String lessonName = req.queryParams("lessonName");
                int lessonCredit = Integer.parseInt(req.queryParams("lessonCredit"));
                int lessonAkts = Integer.parseInt(req.queryParams("lessonAkts"));
                int lessonId = Integer.parseInt(req.queryParams("lessonId"));


                for (int i = 0; i < lessonDtoList.size(); i++) {
                    LessonDto dersler = lessonDtoList.get(i);
                    if (lessonId == dersler.getLessonId()) {
                        lessonDtoList.remove(i);
                        lessonDtoList.add(new LessonDto(lessonName, lessonCredit, lessonAkts, lessonId));
                        break;
                    } else {
                        lessonDtoList.add(new LessonDto(lessonName, lessonCredit, lessonAkts, lessonId));
                    }
                }
                resp.redirect("/lessonList");
                return null;
            }
        };
        post(lessonAddPost);
    }
}

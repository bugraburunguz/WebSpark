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

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(MainScreen.class);
        logger.info("Hi guys!!");
        setPort(8080);

        List<StudentDto> studentDtoList = new ArrayList<>();
        List<LessonDto> lessonDtoList = new ArrayList<>();

        studentDtoList.add(new StudentDto("Buğra Emin",
                "Bürüngüz",
                "Software Engeneering",
                "Degree",
                "B1806090900"));

        studentDtoList.add(new StudentDto("Mehmet",
                "Güngörmüş",
                "Computer Engeneering",
                "Degree",
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
                String degree = req.queryParams("degree");
                String studentNumber = req.queryParams("number");
                studentDtoList.add(new StudentDto(studentName, studentsurname, department, degree, studentNumber));

                resp.redirect("/studentList");
                return null;
            }
        };
        post(studentAddPost);

        FreeMarkerRoute ogrencisilPost = new FreeMarkerRoute("/studentList") {
            public Object handle(Request req, Response resp) {

                String selectedStudent = req.queryParams("deletedStudent");
                for (int i = 0; i < studentDtoList.size(); i++) {
                    StudentDto student = studentDtoList.get(i);
                    if (selectedStudent.equals(student.getStudentNumber())) {
                        studentDtoList.remove(i);
                        break;
                    } else {
                        //DOldurulacak
                    }
                }

                resp.redirect("/studentList");
                return null;
            }
        };
        post(ogrencisilPost);

        FreeMarkerRoute dersDetayi = new FreeMarkerRoute("/lessonDetail") {
            public Object handle(Request req, Response resp) {
                int dersIndeksi = -1;
                int secilenDers = Integer.parseInt(req.queryParams("selectedLesson"));
                for (int i = 0; i < lessonDtoList.size(); i++) {
                    LessonDto lessonList = lessonDtoList.get(i);
                    if (secilenDers == lessonList.getLessonId()) {
                        dersIndeksi = i;
                        break;
                    }
                }
                LessonDto lessonDetail = lessonDtoList.get(dersIndeksi);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("lessonDetail", lessonDetail);
                return new ModelAndView(attributes, "lessonDetail.html");

            }
        };
        post(dersDetayi);

        FreeMarkerRoute secilenOgrencininDetayi = new FreeMarkerRoute("/studentDetail") {
            public Object handle(Request req, Response resp) {
                int ogrenciIndeksi = -1;
                String secilenOgrenci = req.queryParams("studentCheckbox");
                for (int i = 0; i < studentDtoList.size(); i++) {
                    StudentDto student = studentDtoList.get(i);
                    if (secilenOgrenci.equals(student.getStudentNumber())) {
                        ogrenciIndeksi = i;
                        break;
                    }
                }
                StudentDto studentDetail = studentDtoList.get(ogrenciIndeksi);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("studentDetail", studentDetail);
                return new ModelAndView(attributes, "studentDetail.html");

            }
        };

        post(secilenOgrencininDetayi);

        FreeMarkerRoute ogrenciyeDersEkleme = new FreeMarkerRoute("/studentDetail") {
            public Object handle(Request req, Response resp) {
                String ogrenciNum = req.queryParams("studentNumber");
                int dersler = Integer.parseInt(req.queryParams("selectLesson"));
                int ogrenciIndeksi = -1;
                for (int i = 0; i < studentDtoList.size(); i++) {
                    StudentDto student = studentDtoList.get(i);
                    if (ogrenciNum.equals(student.getStudentNumber())) {
                        ogrenciIndeksi = i;

                        break;
                    }
                }
                int dersinIndeksi = -1;
                for (int i = 0; i < lessonDtoList.size(); i++) {
                    LessonDto lesson = lessonDtoList.get(i);
                    if (dersler == lesson.getLessonId()) {
                        dersinIndeksi = i;
                        break;
                    }
                }
                StudentDto selectedStudent = studentDtoList.get(ogrenciIndeksi);
                LessonDto selectedLesson = lessonDtoList.get(dersinIndeksi);

            /*
            derse öğrenci öğrenciye ders ekleme kısmı
            selectedStudent.studentsLessons().add(selectedLesson);
            selectedLesson.getDersinOgrencileri().add(selectedStudent);

            */
                return null;

            }
        };

        post(ogrenciyeDersEkleme);

        FreeMarkerRoute lessonAddPost = new FreeMarkerRoute("/dersekle") {
            public Object handle(Request req, Response resp) {

                String dersAdi = req.queryParams("dersAdi");
                int dersKredi = Integer.parseInt(req.queryParams("dersKredi"));
                int dersAKTS = Integer.parseInt(req.queryParams("dersAKTS"));
                int dersId = Integer.parseInt(req.queryParams("dersId"));


                for (int i = 0; i < lessonDtoList.size(); i++) {
                    LessonDto dersler = lessonDtoList.get(i);
                    if (dersId == dersler.getLessonId()) {
                        lessonDtoList.remove(i);
                        lessonDtoList.add(new LessonDto(dersAdi, dersKredi, dersAKTS, dersId));
                        break;
                    } else {
                        lessonDtoList.add(new LessonDto(dersAdi, dersKredi, dersAKTS, dersId));
                    }
                }
                resp.redirect("/dersler");
                return null;
            }
        };
        post(lessonAddPost);
    }
}

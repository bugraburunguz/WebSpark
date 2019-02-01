package main

import dto.LessonDto
import dto.StudentDto
import org.slf4j.LoggerFactory
import spark.ModelAndView
import spark.Request
import spark.Response
import spark.Spark.*
import spark.template.freemarker.FreeMarkerRoute
import java.util.*

//┌───────────────────────────────┐
//│ Created by Buğra Emin BÜRÜNGÜZ│
//│ ───────────────────────────── │
//│ BugraBurunguz@AndroidEdu.IO   │
//│ ───────────────────────────── │
//│ 2019-02-01 - 10:20             │
//└────────────────────────────────
object MainScreen {

    // TODO: 2019-01-28
    //  ÖĞRENCİYE DERS EKLEME, DERSE ÖĞRENCİ EKLEME YAPILACAK, LİSANS DURUMU BİR ENUMDA TUTULACAK...
    //  ÖĞRENCİ VE DERSE UPDATE SİSTEMİ GETİRİLECEK
    //  VERİ TABANI EKLENECEK MUHTEMELEN FIREBASE
    //  KOD DÜZENLENECEK DAHA KISA VE DAHA GÜZEL YAPILABİLİR Mİ BAKILACAK
    //  Ders silme eklenecek

    @JvmStatic
    fun main(args: Array<String>) {

        val logger = LoggerFactory.getLogger(MainScreen::class.java)
        logger.info("Hi guys!!")
        setPort(8080)

        val studentDtoList = ArrayList<StudentDto>()
        val lessonDtoList = ArrayList<LessonDto>()

        studentDtoList.add(StudentDto("Buğra Emin",
                "Bürüngüz",
                "Software Engeneering",
                "Lisans",
                "B1806090900"))

        studentDtoList.add(StudentDto("Mehmet",
                "Güngörmüş",
                "Computer Engeneering",
                "Lisans",
                "B1805070800"))

        lessonDtoList.add(LessonDto("Proje Yönetimi",
                4,
                6,
                12))

        val studentListPageGet = object : FreeMarkerRoute("/studentList") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["students"] = studentDtoList
                return ModelAndView(attributes, "studentList.html") // resources'daki html veya ftl
            }
        }
        get(studentListPageGet)

        val studentAddPageGet = object : FreeMarkerRoute("/studentAdd") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["students"] = studentDtoList
                return ModelAndView(attributes, "studentAdd.html") // resources'daki html veya ftl
            }
        }
        get(studentAddPageGet)

        val studentDetailPageGet = object : FreeMarkerRoute("/studentDetail") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["students"] = studentDtoList
                return ModelAndView(attributes, "studentDetail.html") // resources'daki html veya ftl
            }
        }
        get(studentDetailPageGet)

        val lessonDetailPageGet = object : FreeMarkerRoute("/lessonDetail") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["lessonList"] = lessonDtoList
                return ModelAndView(attributes, "lessonDetail.html") // resources'daki html veya ftl
            }
        }
        get(lessonDetailPageGet)

        val lessonListPageGet = object : FreeMarkerRoute("/lessonList") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["lessonList"] = lessonDtoList
                return ModelAndView(attributes, "lessonList.html") // resources'daki html veya ftl
            }
        }
        get(lessonListPageGet)

        val lessonAddPageGet = object : FreeMarkerRoute("/lessonAdd") {
            override fun handle(req: Request, resp: Response): Any {
                val attributes = HashMap<String, Any>()
                attributes["lessonList"] = lessonDtoList
                return ModelAndView(attributes, "lessonAdd.html") // resources'daki html veya ftl
            }
        }
        get(lessonAddPageGet)
        // ------------------------------------------------------------------------------------- üst kısım hash maplerle alakalı ----------------------------------------------------

        val studentAddPost = object : FreeMarkerRoute("/studentAdd") {
            override fun handle(req: Request, resp: Response): Any? {

                val studentName = req.queryParams("name")
                val studentsurname = req.queryParams("surname")
                val department = req.queryParams("department")
                val degree = req.queryParams("studentDegree")
                val studentNumber = req.queryParams("number")

                studentDtoList.add(StudentDto(studentName, studentsurname, department, degree, studentNumber))

                resp.redirect("/studentList")
                return null
            }
        }
        post(studentAddPost)

        val studentDeletePost = object : FreeMarkerRoute("/studentList") {
            override fun handle(req: Request, resp: Response): Any? {

                val selectedStudent = req.queryParams("deletedStudent")
                for (i in studentDtoList.indices) {
                    val student = studentDtoList[i]
                    if (selectedStudent == student.studentNumber) {
                        studentDtoList.removeAt(i)
                        break
                    } else {
                        resp.redirect("/studentList")
                    }
                }
                resp.redirect("/studentList")
                return null
            }
        }
        post(studentDeletePost)

        val selectedLessonDetailPost = object : FreeMarkerRoute("/lessonDetail") {
            override fun handle(req: Request, resp: Response): Any {
                var lessonIndex = -1
                val selectedLesson = Integer.parseInt(req.queryParams("lesson_select_radio"))
                for (i in lessonDtoList.indices) {
                    val lesson = lessonDtoList[i]
                    if (selectedLesson == lesson.lessonId) {
                        lessonIndex = i
                        break
                    }
                }
                val lessonDetail = lessonDtoList[lessonIndex]
                val attributes = HashMap<String, Any>()
                attributes["lessonList"] = lessonDetail
                return ModelAndView(attributes, "lessonDetail.html")
            }
        }
        post(selectedLessonDetailPost)

        val selectedStudentsDetailPost = object : FreeMarkerRoute("/studentDetail") {
            override fun handle(req: Request, resp: Response): Any {
                var studentIndex = -1
                val selectedStudent = req.queryParams("student_select_checkbox")
                for (i in studentDtoList.indices) {
                    val student = studentDtoList[i]
                    if (selectedStudent == student.studentNumber) {
                        studentIndex = i
                        break
                    }
                }
                val studentDetail = studentDtoList[studentIndex]
                val attributes = HashMap<String, Any>()
                attributes["students"] = studentDetail
                return ModelAndView(attributes, "studentDetail.html")
            }
        }
        post(selectedStudentsDetailPost)


        val lessonAddPost = object : FreeMarkerRoute("/lessonAdd") {
            override fun handle(req: Request, resp: Response): Any? {

                val lessonName = req.queryParams("lessonName")
                val lessonCredit = Integer.parseInt(req.queryParams("lessonCredit"))
                val lessonAkts = Integer.parseInt(req.queryParams("lessonAkts"))
                val lessonId = Integer.parseInt(req.queryParams("lessonId"))


                for (i in lessonDtoList.indices) {
                    val dersler = lessonDtoList[i]
                    if (lessonId == dersler.lessonId) {
                        lessonDtoList.removeAt(i)
                        lessonDtoList.add(LessonDto(lessonName, lessonCredit, lessonAkts, lessonId))
                        break
                    } else {
                        lessonDtoList.add(LessonDto(lessonName, lessonCredit, lessonAkts, lessonId))
                    }
                }
                resp.redirect("/lessonList")
                return null
            }
        }
        post(lessonAddPost)
    }
}

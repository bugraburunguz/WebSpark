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


public class SparkFtl {

    /*--
     * http://taywils.me/2013/11/05/javasparkframeworktutorial/
     */
    public static void main(String[] args) {


        Logger logger = LoggerFactory.getLogger(SparkFtl.class);
        logger.info("Hello World");


        List<Ogrenci> ogrencilistesi = new ArrayList<Ogrenci>();
        List<Ders> derslistesi = new ArrayList<Ders>();


        ogrencilistesi.add(new Ogrenci("Ogrenci Bir", "Yüksek Lisans", "MYO", 1));
        ogrencilistesi.add(new Ogrenci("Ogrenci Iki", "On Lisans", "MYO", 2));
        ogrencilistesi.add(new Ogrenci("Ogrenci Uc", "On Lisans", "MYO", 3));
        derslistesi.add(new Ders("Bilişim Hukuku", 15, 12, 1235));

        setPort(8080);

        FreeMarkerRoute ogrencilerGet = new FreeMarkerRoute("/ogrenciler") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("ogrenciler", ogrencilistesi);
                return new ModelAndView(attributes, "studentList.html"); // resources'daki html veya ftl
            }
        };
        get(ogrencilerGet);

        FreeMarkerRoute ogrenciekleGet = new FreeMarkerRoute("/ogrenciekle") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("ogrenciler", ogrencilistesi);
                return new ModelAndView(attributes, "studentAdd.html"); // resources'daki html veya ftl
            }
        };
        get(ogrenciekleGet);

        FreeMarkerRoute ogrencidetayGet = new FreeMarkerRoute("/ogrencigoruntule") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("ogrenciler", ogrencilistesi);
                return new ModelAndView(attributes, "ogrencigoruntule.html"); // resources'daki html veya ftl
            }
        };
        get(ogrencidetayGet);
        FreeMarkerRoute dersdetayGet = new FreeMarkerRoute("/dersgoruntule") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("dersler", derslistesi);
                return new ModelAndView(attributes, "dersgoruntule.html"); // resources'daki html veya ftl
            }
        };
        get(dersdetayGet);

        FreeMarkerRoute derslerGet = new FreeMarkerRoute("/dersler") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("dersler", derslistesi);
                return new ModelAndView(attributes, "lessonList.html"); // resources'daki html veya ftl
            }
        };
        get(derslerGet);

        FreeMarkerRoute dersEkleGet = new FreeMarkerRoute("/dersekle") {
            public Object handle(Request req, Response resp) {
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("dersler", derslistesi);
                return new ModelAndView(attributes, "dersekle.html"); // resources'daki html veya ftl
            }
        };
        get(dersEkleGet);
        // ------------------------------------------------------------------------------------- üst kısım hash maplerle alakalı ----------------------------------------------------
        FreeMarkerRoute ogrencieklePost = new FreeMarkerRoute("/ogrenciekle") {
            public Object handle(Request req, Response resp) {
                String adSoyad = req.queryParams("adSoyad");
                String ogrenciTipi = req.queryParams("ogrenciTipi");
                String bolum = req.queryParams("bolum");
                int numara = Integer.parseInt(req.queryParams("numara"));
                ogrencilistesi.add(new Ogrenci(adSoyad, ogrenciTipi, bolum, numara));

                resp.redirect("/ogrenciler");
                return null;
            }
        };
        post(ogrencieklePost);

        FreeMarkerRoute ogrencisilPost = new FreeMarkerRoute("/ogrenciler") {
            public Object handle(Request req, Response resp) {
                int silinecekOgrencininIndexi = -1;
                int secilenOgrenci = Integer.parseInt(req.queryParams("OgrenciSildirme"));
                for (int i = 0; i < ogrencilistesi.size(); i++) {
                    Ogrenci student = ogrencilistesi.get(i);
                    if (secilenOgrenci == student.numara) {
                        silinecekOgrencininIndexi = i;
                        break;
                    }
                }
                ogrencilistesi.remove(silinecekOgrencininIndexi);
                resp.redirect("/ogrenciler");
                return null;
            }
        };
        post(ogrencisilPost);

        FreeMarkerRoute dersDetayi = new FreeMarkerRoute("/dersgoruntule") {
            public Object handle(Request req, Response resp) {
                int dersIndeksi = -1;
                int secilenDers = Integer.parseInt(req.queryParams("secilenDers"));
                for (int i = 0; i < derslistesi.size(); i++) {
                    Ders dersler = derslistesi.get(i);
                    if (secilenDers == dersler.dersId) {
                        dersIndeksi = i;
                        break;
                    }
                }
                Ders dersdetayi = derslistesi.get(dersIndeksi);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("dersdetay", dersdetayi);
                return new ModelAndView(attributes, "dersgoruntule.html");

            }
        };
        post(dersDetayi);

        FreeMarkerRoute secilenOgrencininDetayi = new FreeMarkerRoute("/ogrencigoruntule") {
            public Object handle(Request req, Response resp) {

                int ogrenciIndeksi = -1;
                int secilenOgrenci = Integer.parseInt(req.queryParams("OgrenciCheckbox"));
                for (int i = 0; i < ogrencilistesi.size(); i++) {
                    Ogrenci student = ogrencilistesi.get(i);
                    if (secilenOgrenci == student.numara) {
                        ogrenciIndeksi = i;
                        break;
                    }
                }
                Ogrenci ogrencidetayi = ogrencilistesi.get(ogrenciIndeksi);
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("ogrencidetay", ogrencidetayi);
                return new ModelAndView(attributes, "ogrencigoruntule.html");

            }
        };

        post(secilenOgrencininDetayi);

        FreeMarkerRoute ogrenciyeDersEkleme = new FreeMarkerRoute("/ogrencigoruntule") {
            public Object handle(Request req, Response resp) {
                int ogrenciNum = Integer.parseInt(req.queryParams("ogrenciNum"));
                int dersler = Integer.parseInt(req.queryParams("DersSecim"));
                int ogrenciIndeksi = -1;
                for (int i = 0; i < ogrencilistesi.size(); i++) {
                    Ogrenci student = ogrencilistesi.get(i);
                    if (ogrenciNum == student.numara) {
                        ogrenciIndeksi = i;

                        break;
                    }
                }
                int dersinIndeksi = -1;
                for (int i = 0; i < derslistesi.size(); i++) {
                    Ders lesson = derslistesi.get(i);
                    if (dersler == lesson.dersId) {
                        dersinIndeksi = i;
                        break;
                    }
                }
                Ogrenci secilenOgrenci = ogrencilistesi.get(ogrenciIndeksi);
                Ders secilenDers = derslistesi.get(dersinIndeksi);

                secilenOgrenci.getOgrencininDersleri().add(secilenDers);
                secilenDers.getDersinOgrencileri().add(secilenOgrenci);

                return null;

            }
        };

        post(ogrenciyeDersEkleme);

        FreeMarkerRoute dersEklePost = new FreeMarkerRoute("/dersekle") {
            public Object handle(Request req, Response resp) {

                String dersAdi = req.queryParams("dersAdi");
                int dersKredi = Integer.parseInt(req.queryParams("dersKredi"));
                int dersAKTS = Integer.parseInt(req.queryParams("dersAKTS"));
                int dersId = Integer.parseInt(req.queryParams("dersId"));
                int aynıidliders = -1;

                for (int i = 0; i < derslistesi.size(); i++) {
                    Ders dersler = derslistesi.get(i);
                    if (dersId == dersler.getDersId()) {
                        aynıidliders = i;
                        derslistesi.remove(aynıidliders);
                        break;
                    }
                }
                derslistesi.add(new Ders(dersAdi, dersKredi, dersAKTS, dersId));

                resp.redirect("/dersler");
                return null;
            }
        };
        post(dersEklePost);

    }


}

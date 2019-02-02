import java.util.List;

/**
 * Created by TeORNeK on 19.05.2017.
 */
public class Ders {

    public String dersAdi;
    public int dersKredi;
    public int dersAKTS;
    public String dersId;
    public List<Ogrenci> dersinOgrencileri;

    public Ders(String dersAdi, int dersKredi, int dersAKTS, String dersId) {
        this.dersAdi = dersAdi;
        this.dersKredi = dersKredi;
        this.dersAKTS = dersAKTS;
        this.dersId = dersId;


    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public int getDersKredi() {
        return dersKredi;
    }

    public void setDersKredi(int dersKredi) {
        this.dersKredi = dersKredi;
    }

    public int getDersAKTS() {
        return dersAKTS;
    }

    public void setDersAKTS(int dersAKTS) {
        this.dersAKTS = dersAKTS;
    }

    public int getDersId() {
        return dersId;
    }

    public void setDersId(int dersId) {
        this.dersId = dersId;
    }

    public List<Ogrenci> getDersinOgrencileri() {
        return dersinOgrencileri;
    }

    public void setDersinOgrencileri(List<Ogrenci> dersinOgrencileri) {
        this.dersinOgrencileri = dersinOgrencileri;
    }



}

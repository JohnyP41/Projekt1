package hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "Kolory")
public class Kolor {
    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "kolornazwa")
    private String kolor;

    @Column(name = "kolorwhex")
    private String kolorH;

    @Column(name = "kolorwRGB")
    private String kolorRGB;

    public Kolor() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public String getKolorH() {
        return kolorH;
    }

    public void setKolorH(String kolorH) {
        this.kolorH = kolorH;
    }

    public String getKolorRGB() {
        return kolorRGB;
    }

    public void setKolorRGB(String kolorRGB) {
        this.kolorRGB = kolorRGB;
    }
}

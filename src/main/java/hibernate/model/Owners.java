package hibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "Owners")
public class Owners {

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "author_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "miejscezamieszkania")
    private String miejscezam;

    @Column(name = "kodpocztowy", length = 6, nullable = false)
    private String kod;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String roku;

    public Owners() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getMiejscezam() {
        return miejscezam;
    }

    public void setMiejscezam(String miejscezam) {
        this.miejscezam = miejscezam;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getRoku() {
        return roku;
    }

    public void setRoku(String roku) {
        this.roku = roku;
    }
}
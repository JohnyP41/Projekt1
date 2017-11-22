package hibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "refId", scope = Cars.class)
@Entity
@Table(name = "Cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "marka"})})
public class Cars {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "marka")
    private String marka;

    @Column(name = "model")
    private String model;

    @Column(name = "cena")
    private int cena;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private String rocznik;

    @Column(name = "LiczbaKM", unique = true)
    private int konie;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Wlasciciel", referencedColumnName = "id")
    Owners owners;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Kolorid", referencedColumnName = "id")
    Kolor kolors;

    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    private List<Cars> wlasciciel = new ArrayList<Cars>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cars> cars = new ArrayList<Cars>();

    public Cars() {
        this.marka = marka;
        this.model = model;
        this.cena = cena;
        this.rocznik = rocznik;
        this.konie = konie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }


    public String getRocznik() {
        return rocznik;
    }

    public void setRocznik(String rocznik) {
        this.rocznik = rocznik;
    }

    public int getKonie() {
        return konie;
    }

    public void setKonie(int konie) {
        this.konie = konie;
    }
}
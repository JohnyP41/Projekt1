package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.model.Cars;
import hibernate.model.Kolor;
import hibernate.model.Owners;
import org.apache.log4j.BasicConfigurator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.util.List;


class Manager {

    public static List<Cars> getAllCarsByPage(int pagenr) {

        EntityManager entityManager = null;
        //calculate total number
        Query queryTotal = entityManager.createQuery
                ("Select count(f) from Cars f");
        long countResult = (long) queryTotal.getSingleResult();

        //create query
        Query query = entityManager.createQuery("Select e FROM Cars e");
        //set pageSize
        int pageSize = 10;
        //calculate number of pages
        int pageNumber = (int) ((countResult / pageSize) + 1);

        if (pagenr > pageNumber) pagenr = pageNumber;
        query.setFirstResult((pagenr - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        System.out.println("Start");

        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;

        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");

            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            //add 1

            Cars car = new Cars();
            car.setMarka("BMW");
            car.setModel("X10");
            car.setCena(30000);
            car.setRocznik("1990-10-23");
            car.setKonie(140);

            entityManager.merge(car);

            //add 2

            Cars car1 = new Cars();
            car1.setMarka("Toyota");
            car1.setModel("Corolla");
            car1.setCena(20000);
            car1.setRocznik("2004-10-23");
            car1.setKonie(110);

            entityManager.merge(car1);

            //add 3

            Cars car2 = new Cars();
            car2.setMarka("Toyota");
            car2.setModel("Carina");
            car2.setCena(23000);
            car2.setRocznik("2010-01-03)");
            car2.setKonie(100);

            entityManager.merge(car2);

            //add 4

            Cars car3 = new Cars();
            car3.setMarka("Mitsubishi");
            car3.setModel("Carisma");
            car3.setCena(12000);
            car3.setRocznik("2013-04-03");
            car3.setKonie(135);

            entityManager.merge(car3);

            //add 5

            Cars car4 = new Cars();
            car4.setMarka("Honda");
            car4.setModel("Acord");
            car4.setCena(16300);
            car4.setRocznik("2016-04-03");
            car4.setKonie(95);

            entityManager.merge(car4);

            Owners o = new Owners();
            o.setImie("Marcin");
            o.setNazwisko("Kwiatkowski");
            o.setMiejscezam("Krakow");
            o.setKod("23-043");
            o.setRoku("1993-01-12");

            entityManager.merge(o);

            Kolor k = new Kolor();
            k.setKolor("niebieski");
            k.setKolorRGB("0, 0, 255");
            k.setKolorH("#0000FF");

            entityManager.merge(k);

            //Deserializacja z jsona
            ObjectMapper mapperx = new ObjectMapper();
            List<Cars> xd = mapperx.readValue(new File("j:\\Desktop\\xd.json"), new TypeReference<List<Cars>>() {});

            Cars car7 = new Cars();
            for (int i = 0; i < xd.size(); i++) {
                car7.setMarka(xd.get(i).getMarka());
                car7.setModel(xd.get(i).getModel());
                car7.setCena(xd.get(i).getCena());
                car7.setRocznik(xd.get(i).getRocznik());
                car7.setKonie(xd.get(i).getKonie());
            }

            entityManager.merge(car7);

            //Deserializacja z xml
            XmlMapper pl = new XmlMapper();
            List<Cars> mama = pl.readValue(new File("j:\\Desktop\\xd.xml"), new TypeReference<List<Cars>>() {});

            Cars car8 = new Cars();
            for (int i = 0; i < mama.size(); i++) {
                car8.setMarka(mama.get(i).getMarka());
                car8.setModel(mama.get(i).getModel());
                car8.setCena(mama.get(i).getCena());
                car8.setRocznik(mama.get(i).getRocznik());
                car8.setKonie(mama.get(i).getKonie());
            }

            entityManager.merge(car8);

            //zakoncz transakcje
            entityManager.getTransaction().commit();
            System.out.println("Done");


            //wypisz wyszystko
            System.out.println("Dane samochodów:");
            Query query = entityManager.createQuery("SELECT k FROM Cars k");
            List<Cars> Carx = query.getResultList();
            for (int i = 0; i < Carx.size(); i++) {
                System.out.println(Carx.get(i).getId());
                System.out.println(Carx.get(i).getMarka());
                System.out.println(Carx.get(i).getModel());
                System.out.println(Carx.get(i).getCena());
                System.out.println(Carx.get(i).getRocznik());
                System.out.println(Carx.get(i).getKonie());
            }

            //zapisz wszystko do jsona - serializacja
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File("j:\\Desktop\\wszystko.json"), Carx);

            //zapisz wszystko do xmla - serializacja
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File("j:\\Desktop\\wszystko.xml"), Carx);

            //wypisz ceny samochodow o liczbie KM=135
            System.out.println("Ceny samochodów o liczbie KM=135:");
            Query query1 = entityManager.createQuery("SELECT k FROM Cars k WHERE k.konie=135");
            List<Cars> Car1 = query1.getResultList();
            for (int i = 0; i < Car1.size(); i++) {
                System.out.println(Car1.get(i).getCena());
            }
            //wypisz ceny samochodow o id=1
            System.out.println("Ceny samochodów o id=1:");
            Query query2 = entityManager.createQuery("SELECT k FROM Cars k WHERE k.id=1");
            List<Cars> Car2 = query2.getResultList();
            for (int i = 0; i < Car2.size(); i++) {
                System.out.println(Car2.get(i).getCena());
            }

            //wypisz wszystko o samochodzie BMW
            System.out.println("Wszystko o samochodzie ktorego marka to BMW:");
            Query query3 = entityManager.createQuery("SELECT k FROM Cars k WHERE k.marka='BMW'");
            List<Cars> Car3 = query3.getResultList();
            for (int i = 0; i < Car3.size(); i++) {
                System.out.println(Car3.get(i).getId());
                System.out.println(Car3.get(i).getMarka());
                System.out.println(Car3.get(i).getModel());
                System.out.println(Car3.get(i).getCena());
                System.out.println(Car3.get(i).getRocznik());
                System.out.println(Car3.get(i).getKonie());
            }

            System.out.println("Test");
                    for(int i=0;i<=getAllCarsByPage(3).size();i++)
                    {
                        getAllCarsByPage(3).get(i).getRocznik();
                    }

            entityManager.close();


        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
        } finally {
            entityManagerFactory.close();
        }

    }
}
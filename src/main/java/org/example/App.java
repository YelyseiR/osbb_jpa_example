package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.data.FileWriter;
import org.example.data.OsbbCrud;
import org.example.data.OwnerInfoResult;

import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("osbb");
        EntityManager em = emf.createEntityManager();
        OsbbCrud osbbCrud = new OsbbCrud(em);
        List<OwnerInfoResult> residents = osbbCrud.getOwnersWithoutCarPermissionAndLessThanTwoApartments();
        System.out.println(residents);
        FileWriter fileWriter = new FileWriter();
        fileWriter.saveResultToFile(residents, "result.txt");
        em.close();
        emf.close();
    }
}
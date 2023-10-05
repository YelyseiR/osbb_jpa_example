package org.example.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class FileWriter {
    public void saveResultToFile(List<OwnerInfoResult> owners, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath))) {
            for (OwnerInfoResult owner : owners) {
                String line = String.format(
                        "Ім'я: %s, Прізвище: %s, Email: %s, Номер будинку: %s, Номер квартири: %s, Площа квартири: %.2f, Адреса будинку: %s%n",
                        owner.name(),
                        owner.surname(),
                        owner.email(),
                        owner.buildingNumber(),
                        owner.apartmentNumber(),
                        owner.area(),
                        owner.address()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

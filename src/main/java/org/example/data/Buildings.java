package org.example.data;


import jakarta.persistence.*;

@Entity
@Table(name = "buildings")
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "number_of_apartments")
    private int numberOfApartments;

    @Column(name = "adress")
    private String address;

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getAddress() {
        return address;
    }
}

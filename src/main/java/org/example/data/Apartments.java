package org.example.data;

import jakarta.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "apartment_number")
    private int apartmentNumber;

    @Column(name = "floor")
    private int floor;

    @Column(name = "area")
    private float area;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    private Buildings buildingId;

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public float getArea() {
        return area;
    }

    public Buildings getBuildingId() {
        return buildingId;
    }
}

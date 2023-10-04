package org.example.data;

import jakarta.persistence.*;

@Entity
@Table(name = "ownership_rights")
public class OwnershipRights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Residents memberId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private Apartments apartmentId;

    public Residents getMemberId() {
        return memberId;
    }

    public Apartments getApartmentId() {
        return apartmentId;
    }
}

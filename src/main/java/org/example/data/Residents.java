package org.example.data;

import jakarta.persistence.*;

@Entity
@Table(name = "rc_residents")
public class Residents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "car_permission")
    private int carPermission;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private OsbbMembers memberId;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OsbbMembers getMemberId() {
        return memberId;
    }
}

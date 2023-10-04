package org.example.data;

import jakarta.persistence.*;

@Entity
@Table(name = "osbb_members")
public class OsbbMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }
}

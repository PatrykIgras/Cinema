package com.example.Cinema.domain;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "room_id")
    private Long id;
    private String name;
    private Long capacity;
    private String description;

    public Room() {
    }

    public Room(Long id, String name, Long capacity, String dscription) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = dscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getDscription() {
        return description;
    }

    public void setDscription(String dscription) {
        this.description = dscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room other = (Room) o;

        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}

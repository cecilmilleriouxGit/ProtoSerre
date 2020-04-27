package com.proto.serre.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Set;

@Entity
public class DateOfSave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private java.util.Date date;
    @OneToMany(mappedBy = "dateOfSave", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Dht22> dht22Set;

    public DateOfSave() {
    }

    public DateOfSave(java.util.Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Set<Dht22> getDht22Set() {
        return dht22Set;
    }

    public void setDht22Set(Set<Dht22> dht22Set) {
        this.dht22Set = dht22Set;
    }
}

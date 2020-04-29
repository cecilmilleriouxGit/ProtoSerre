package com.proto.serre.entities;

import javax.persistence.*;


@Entity
public class Dht22 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float temperature;
    private float humidity;
    @ManyToOne
    private DateOfSave dateOfSave;

    public Dht22() {
    }

    public Dht22(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public DateOfSave getDateOfSave() {
        return dateOfSave;
    }

    public void setDateOfSave(DateOfSave dateOfSave) {
        this.dateOfSave = dateOfSave;
    }
}

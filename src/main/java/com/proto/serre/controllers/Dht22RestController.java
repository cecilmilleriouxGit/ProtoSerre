package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.TimeZone;

@RestController
public class Dht22RestController {

    @Autowired
    DateOfSaveRepository dateOfSaveRepository;
    @Autowired
    Dht22Repository dht22Repository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/dht22/store")
    public void storeData(@RequestParam float temperature,
                          @RequestParam float humidity){
        DateOfSave dateOfSave = new DateOfSave();
        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("GMT");
        cal.setTimeZone(tz);
        dateOfSave.setDate(cal.getTime());
        dateOfSaveRepository.save(dateOfSave);
        Dht22 dht22 = new Dht22();
        dht22.setDateOfSave(dateOfSave);
        dht22.setTemperature(temperature);
        dht22.setHumidity(humidity);
        dht22Repository.save(dht22);
    }
}

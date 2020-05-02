package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AllDataForLastWeekController {

    @Autowired
    DateOfSaveRepository dateOfSaveRepository;
    @Autowired
    Dht22Repository dht22Repository;

    @GetMapping("/allDataForLastWeek")
    public String allDataForLastWeek(Model model) {
        // All temperature for seven days
        List<DateOfSave> allDateOfSaveForSevenDays = dateOfSaveRepository.findByDateForSevenDays();
        ArrayList globalTemperatureListForSevenDays = new ArrayList();

        for (DateOfSave dateOfSaveToArrayList : allDateOfSaveForSevenDays) {
            ArrayList arrayListDateAndTemperature = new ArrayList();
            SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
            ArrayList arrayListTemperatureSorted = new ArrayList();
            for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                arrayListTemperatureSorted.add(Math.round(dht22.getTemperature()));
            }
            Collections.sort(arrayListTemperatureSorted);
            for (Object dht22 : arrayListTemperatureSorted) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfSaveToArrayList.getDate());
                cal.add(Calendar.MONTH, -1);
                Date dateAjust = cal.getTime();
                arrayListDateAndTemperature.add("{ x: new Date"+ formater.format(dateAjust) + ", y:" + dht22+"},");
            }
            for (Object arrToFinal: arrayListDateAndTemperature){
                globalTemperatureListForSevenDays.add(arrToFinal);
            }
        }
        model.addAttribute("globalTemperatureListForSevenDays", globalTemperatureListForSevenDays);
        // All humidity for seven days
        ArrayList globalHumidityListForSevenDays = new ArrayList();
        for (DateOfSave dateOfSaveToArrayList : allDateOfSaveForSevenDays) {
            ArrayList arrayListDateAndTemperature = new ArrayList();
            SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
            ArrayList arrayListSorted = new ArrayList();
            for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                arrayListSorted.add(Math.round(dht22.getHumidity()));
            }
            Collections.sort(arrayListSorted);
            for (Object dht22 : arrayListSorted) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfSaveToArrayList.getDate());
                cal.add(Calendar.MONTH, -1);
                Date dateAjust = cal.getTime();
                arrayListDateAndTemperature.add("{ x: new Date"+ formater.format(dateAjust) + ", y:" + dht22+"},");
            }
            for (Object arrToFinal: arrayListDateAndTemperature){
                globalHumidityListForSevenDays.add(arrToFinal);
            }
        }
        model.addAttribute("globalHumidityListForSevenDays", globalHumidityListForSevenDays);


        return "allDataForLastWeek";
    }
}


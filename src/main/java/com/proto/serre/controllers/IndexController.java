package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    DateOfSaveRepository dateOfSaveRepository;
    @Autowired
    Dht22Repository dht22Repository;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<DateOfSave> allDateOfSave = dateOfSaveRepository.findByDate();
        ArrayList globalTemperatureList = new ArrayList();
        // allDateOfSave =  date for 7 days
        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            ArrayList arrayListDateAndTemperature = new ArrayList();
            SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
            // arr.add(formater.format(dateOfSaveToArrayList.getDate()));
            ArrayList arrayListSorted = new ArrayList();
            for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                arrayListSorted.add(Math.round(dht22.getTemperature()));
            }
            Collections.sort(arrayListSorted);
            for (Object dht22 : arrayListSorted) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfSaveToArrayList.getDate());
                cal.add(Calendar.MONTH, -1);
                Date dateAjust = cal.getTime();
                arrayListDateAndTemperature.add("{ x: new Date"+ formater.format(dateAjust)+ ", y:" + dht22+"},");
            }
            for (Object arrToFinal: arrayListDateAndTemperature){
                globalTemperatureList.add(arrToFinal);
            }
        }
        model.addAttribute("globalTemperatureList", globalTemperatureList);

        ArrayList globalHumidityList = new ArrayList();
        // allDateOfSave =  date for 7 days
        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            ArrayList arrayListDateAndTemperature = new ArrayList();
            SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
            // arr.add(formater.format(dateOfSaveToArrayList.getDate()));
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
                arrayListDateAndTemperature.add("{ x: new Date"+ formater.format(dateAjust)+ ", y:" + dht22+"},");
            }
            for (Object arrToFinal: arrayListDateAndTemperature){
                globalHumidityList.add(arrToFinal);
            }
        }
        model.addAttribute("globalHumidityList", globalHumidityList);
        return "index";
    }
}


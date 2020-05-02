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
public class MinMaxTempForLastWeekController {

    @Autowired
    DateOfSaveRepository dateOfSaveRepository;
    @Autowired
    Dht22Repository dht22Repository;

    @GetMapping("/minMaxDataForLastWeek")
    public String minMaxDataForLastWeek(Model model) {
        // All temperature for seven days
        List<DateOfSave> allDateOfSaveForSevenDays = dateOfSaveRepository.findByDateForSevenDays();
        ArrayList globalTemperatureMinMaxListForSevenDays = new ArrayList();
        for (DateOfSave dateOfSaveToArrayList : allDateOfSaveForSevenDays) {
            // Min and Max temperature for seven days
            ArrayList arrayListDateAndTemperatureMinMax = new ArrayList();
            Integer min = Integer.MAX_VALUE;
            Integer max = Integer.MIN_VALUE;
            String hourMin = "";
            String hourMax = "";
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateOfSaveToArrayList.getDate());
            Date dateAjust = cal.getTime();
            SimpleDateFormat formaterMinMax = new SimpleDateFormat("E");
            for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                if(min > Math.round(dht22.getTemperature())){
                    min = Math.round(dht22.getTemperature());
                    hourMin = dht22.getHour();
                }
                if(max < Math.round(dht22.getTemperature())) {
                    max = Math.round(dht22.getTemperature());
                    hourMax = dht22.getHour();
                }
            }
            arrayListDateAndTemperatureMinMax.add("{ label: \""+formaterMinMax.format(dateAjust)+"\", y: ["+min+", "+max+"], hourMin: \""+hourMin+"\", hourMax:\""+hourMax+"\" },");
            for (Object arrToFinal: arrayListDateAndTemperatureMinMax){
                globalTemperatureMinMaxListForSevenDays.add(arrToFinal);
            }
        }
        model.addAttribute("globalTemperatureMinMaxListForSevenDays", globalTemperatureMinMaxListForSevenDays);
        return "minMaxTempForLastWeek";
    }
}
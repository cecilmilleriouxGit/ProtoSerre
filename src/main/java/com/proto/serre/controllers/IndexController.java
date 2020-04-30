package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        // All temperature for seven days
        List<DateOfSave> allDateOfSaveForSevenDays = dateOfSaveRepository.findByDateForSevenDays();
        ArrayList globalTemperatureListForSevenDays = new ArrayList();
        ArrayList globalTemperatureMinMaxListForSevenDays = new ArrayList();

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

            // Min and Max temperature for seven days
            ArrayList arrayListDateAndTemperatureMinMax = new ArrayList();
            Integer min = Integer.MAX_VALUE;
            Integer max = Integer.MIN_VALUE;
            String hourMin = "";
            String hourMax = "";
            for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                if(min > Math.round(dht22.getTemperature())){
                    min = Math.round(dht22.getTemperature());
                    hourMin = dht22.getHour();
                }
                if(max < Math.round(dht22.getTemperature())) {
                    max = Math.round(dht22.getTemperature());
                    hourMax = dht22.getHour();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfSaveToArrayList.getDate());
//                cal.add(Calendar.MONTH, -1);
//                cal.add(Calendar.DAY_OF_WEEK, -1);
                Date dateAjust = cal.getTime();
//                Calendar c = Calendar.getInstance();
//                c.setTime(dateAjust);
//                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                SimpleDateFormat formaterMinMax = new SimpleDateFormat("E");
                arrayListDateAndTemperatureMinMax.add("{ label: \""+formaterMinMax.format(dateAjust)+"\", y: ["+min+", "+max+"], hourMin: \""+hourMin+"\", hourMax:\""+hourMax+"\" },");
            }
            for (Object arrToFinal: arrayListDateAndTemperatureMinMax){
                globalTemperatureMinMaxListForSevenDays.add(arrToFinal);
            }
        }
        model.addAttribute("globalTemperatureMinMaxListForSevenDays", globalTemperatureMinMaxListForSevenDays);
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


        return "index";
    }
}


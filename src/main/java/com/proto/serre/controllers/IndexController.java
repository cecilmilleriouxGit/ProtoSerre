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
        ArrayList arrList = new ArrayList();
        int countTemperature = 0;
        // import temperature
        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            if (countTemperature < 7) {
                ArrayList arr = new ArrayList();
                arrList.add(arr);
                SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
//                arr.add(formater.format(dateOfSaveToArrayList.getDate()));
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
                    arr.add(formater.format(dateAjust)+ ", y:" + dht22);
                }
                countTemperature += 1;
                model.addAttribute("arrayListTemperature"+countTemperature, arr);

            }
        }

        int countHumidity = 0;
        // import humidity
        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            if (countHumidity < 7) {
                ArrayList arr = new ArrayList();
                arrList.add(arr);
                SimpleDateFormat formater = new SimpleDateFormat("(yyyy, MM, dd)");
//                arr.add(formater.format(dateOfSaveToArrayList.getDate()));
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
                    arr.add(formater.format(dateAjust)+ ", y:" + dht22);                }
                countHumidity += 1;
                model.addAttribute("arrayListHumidity"+countHumidity, arr);
            }
        }
        return "index";
    }
}


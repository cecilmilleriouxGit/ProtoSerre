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
import java.util.ArrayList;
import java.util.List;

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
                for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                    arr.add(formater.format(dateOfSaveToArrayList.getDate())+ ", y:" + dht22.getTemperature());
                }
                countTemperature += 1;
                model.addAttribute("arrayListTemperature"+countTemperature, arr);
//                System.out.println("arrayListTemperature"+countTemperature);
//                System.out.println(arr);
            }
        }
//        model.addAttribute("arrayListTemperature1", arrList.get(0));

        System.out.println(arrList.get(0));
        int countHumidity = 0;
        // import humidity
        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            if (countHumidity < 7) {
                ArrayList arr = new ArrayList();
                arrList.add(arr);
                SimpleDateFormat formater = new SimpleDateFormat("(dd, MM, yy)");
//                arr.add(formater.format(dateOfSaveToArrayList.getDate()));
                for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                    arr.add(formater.format(dateOfSaveToArrayList.getDate()) + ", y:" + dht22.getHumidity());
                }
                countHumidity += 1;
                model.addAttribute("arrayListHumidity"+countHumidity, arr);
//                System.out.println("arrayListHumidity"+countHumidity);
//                System.out.println(arr);
            }
        }
        return "index";
    }
}


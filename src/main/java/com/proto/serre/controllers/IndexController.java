package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.Arrays;
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

        int count = 0;


        for (DateOfSave dateOfSaveToArrayList : allDateOfSave) {
            if (count < 7) {
                ArrayList arr = new ArrayList();
                arrList.add(arr);

                arr.add(dateOfSaveToArrayList.getDate());
                for (Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()) {
                    arr.add(dht22.getTemperature());
                    arr.add(dht22.getHumidity());
                }
                count += 1;
                model.addAttribute("arrayList"+count, arr);
                System.out.println("arrayList"+count);
                System.out.println(arr);
            }
        }
        return "index";
    }
}


package com.proto.serre.controllers;

import com.proto.serre.entities.DateOfSave;
import com.proto.serre.entities.Dht22;
import com.proto.serre.repositories.DateOfSaveRepository;
import com.proto.serre.repositories.Dht22Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    DateOfSaveRepository dateOfSaveRepository;
    @Autowired
    Dht22Repository dht22Repository;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model){
        List<DateOfSave> dateOfSaveForSevenDays = dateOfSaveRepository.findByDateForSevenDays();
        System.out.println(dateOfSaveForSevenDays);
        ArrayList arrList = new ArrayList();
        int count = 0;
        ArrayList localDateList = new ArrayList();
        for(DateOfSave dateOfSaveToArrayList : dateOfSaveForSevenDays){
            if(count == 0){
                arrList.add(count, localDateList);
                List<DateOfSave> dateOfSaves = dateOfSaveRepository.findByDate(dateOfSaveToArrayList.getDate());
                for(DateOfSave dateOfSave : dateOfSaves){
                    localDateList.add(dateOfSave.getDate());/*.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))*/
                    for(Dht22 dht22 : dateOfSave.getDht22Set()){
                        localDateList.add(dht22.getTemperature());
                        localDateList.add(dht22.getHumidity());
                    }
//                    count += 1;
                }
            }
        }
        System.out.println(arrList);
        System.out.println(localDateList.size());

        return "index";
    }

}

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
        List<DateOfSave> allDateOfSave = dateOfSaveRepository.findAll();
        ArrayList arrList = new ArrayList();
        ArrayList localDateList = new ArrayList();
        for(int count = 1; count < 7; count++){
            for(DateOfSave dateOfSaveToArrayList : allDateOfSave){
                if(arrList.isEmpty()){
                    arrList.add(localDateList);
                }
//                if(!localDateList.isEmpty()){
//                    if(!dateOfSaveToArrayList.equals(localDateList.get(count-1))){
//                        localDateList.add(dateOfSaveToArrayList.getDate());/*.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))*/
//                        for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
//                            localDateList.add(dht22.getTemperature());
//                            localDateList.add(dht22.getHumidity());
//                        }
//                    }
//                }
                if(localDateList.isEmpty()){
                    localDateList.add(dateOfSaveToArrayList.getDate());/*.format(DateTimeFormatter.ofPattern("dd-MM-yyy")))*/
                    for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
                        localDateList.add(dht22.getTemperature());
                        localDateList.add(dht22.getHumidity());
                    }
                }
//                if(dateOfSaveToArrayList.equals(localDateList.get(count))){
//                    for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
//                        localDateList.add(dht22.getTemperature());
//                        localDateList.add(dht22.getHumidity());
//                    }
                }
            }


        System.out.println(arrList);
        System.out.println(localDateList.size());

        return "index";
    }

}

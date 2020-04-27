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
        ArrayList localDateList1 = new ArrayList();
        ArrayList localDateList2 = new ArrayList();
        ArrayList localDateList3 = new ArrayList();
        ArrayList localDateList4 = new ArrayList();
        ArrayList localDateList5 = new ArrayList();
        ArrayList localDateList6 = new ArrayList();
        ArrayList localDateList7 = new ArrayList();
        int count = 0;
            for(DateOfSave dateOfSaveToArrayList : allDateOfSave){
                if(count < 7){
                    arrList.add(localDateList1);

                        localDateList1.add(dateOfSaveToArrayList.getDate());
                        for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
                            localDateList1.add(dht22.getTemperature());
                            localDateList1.add(dht22.getHumidity());
                        }
                        count += 1;

//                    if(!localDateList.isEmpty()){
//                        if(count > 1){
//                            localDateList.add(dateOfSaveToArrayList.getDate());
//                            for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
//                                localDateList.add(dht22.getTemperature());
//                                localDateList.add(dht22.getHumidity());
//                            }
//                            count += 1;
//                        }
//                    }
                }
            }
        //                if(dateOfSaveToArrayList.equals(localDateList.get(count))){
//                    for(Dht22 dht22 : dateOfSaveToArrayList.getDht22Set()){
//                        localDateList.add(dht22.getTemperature());
//                        localDateList.add(dht22.getHumidity());
//                    }



        System.out.println(arrList);
        System.out.println(localDateList1.size());

        return "index";
    }

}

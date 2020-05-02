package com.proto.serre.repositories;

import com.proto.serre.entities.Dht22;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface Dht22Repository extends JpaRepository <Dht22, Long> {

//    @Query(
//            value= "select * from protoserre.dht22 WHERE dht22.date BETWEEN current_date AND current_timestamp - 7",
//            nativeQuery = true
//    )
//    @Query(
//            value= "select * from protoserre.dht22 WHERE dht22.date BETWEEN current_date AND DATE_SUB(CURDATE(), INTERVAL 1 DAY)",
//            nativeQuery = true
//    )
//    List<Dht22> findByDateForWeek();

//    List<Dht22> findByDate(LocalDateTime date);
}
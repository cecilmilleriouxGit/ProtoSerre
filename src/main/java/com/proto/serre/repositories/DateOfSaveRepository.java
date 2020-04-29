package com.proto.serre.repositories;

import com.proto.serre.entities.DateOfSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface DateOfSaveRepository extends JpaRepository <DateOfSave, Long> {

    @Query(
            value = "SELECT * FROM Protoserre.date_of_save WHERE date_of_save.date BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW();",
            nativeQuery = true
    )
    List<DateOfSave> findByDate();


    DateOfSave findByDate(Date date);

    List<DateOfSave> findAll();
}

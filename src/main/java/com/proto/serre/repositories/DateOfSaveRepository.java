package com.proto.serre.repositories;

import com.proto.serre.entities.DateOfSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface DateOfSaveRepository extends JpaRepository <DateOfSave, Long> {

    @Query(
            value = "select * from Protoserre.date_of_save WHERE date_of_save.date BETWEEN current_date() AND current_date()- interval '7' day",
            nativeQuery = true
    )
    List<DateOfSave> findByDateForSevenDays();


    DateOfSave findByDate(Date date);

    List<DateOfSave> findAll();
}

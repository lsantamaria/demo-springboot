package com.demosproject.springboot.carsbackendmvc.domain.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    private List<User> users;
    private String name;
    private Date startDate;
}

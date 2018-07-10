package com.demosproject.springboot.carsbackend.mvc.domain.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Race {

    private String id;

    @DBRef(lazy = true)
    private List<User> users;
    private String name;
    private Date startDate;


}

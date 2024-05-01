package com.example.allendigital.modal.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class Deal {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dealId;
    private int numberOfItems;

    private int product;
    private ZonedDateTime dealExpiry;


}

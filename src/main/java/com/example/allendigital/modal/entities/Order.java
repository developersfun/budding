package com.example.allendigital.modal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity(name = "user_orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    private long dealId;
    private long userId;
    private long numberOfItems;
    private ZonedDateTime time;
    private boolean isSuccessful;

}

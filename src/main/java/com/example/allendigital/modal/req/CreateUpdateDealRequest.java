package com.example.allendigital.modal.req;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class CreateUpdateDealRequest {

    private int productId;
    private ZonedDateTime expiry;
    private Integer numberOfItems;

}

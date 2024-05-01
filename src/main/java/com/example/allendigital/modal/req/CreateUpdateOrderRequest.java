package com.example.allendigital.modal.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.ZonedDateTime;

@Data
public class CreateUpdateOrderRequest {

    private long dealId;
    private Integer numberOfItems;

}

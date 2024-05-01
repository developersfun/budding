package com.example.allendigital.modal.res;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class CreateUpdateDealResponse {

    private long dealId;
    private boolean successful;

    public boolean isSuccessful() {
        return successful;
    }
}

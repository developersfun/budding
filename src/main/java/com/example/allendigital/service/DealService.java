package com.example.allendigital.service;

import com.example.allendigital.modal.res.CreateUpdateDealResponse;
import com.example.allendigital.modal.req.CreateUpdateDealRequest;

public interface DealService {

    CreateUpdateDealResponse createDeal(CreateUpdateDealRequest createDealRequest);

    CreateUpdateDealResponse updateDeal(long dealId,
                                        CreateUpdateDealRequest updateDealRequest);

    CreateUpdateDealResponse closeDeal(long dealId);
}

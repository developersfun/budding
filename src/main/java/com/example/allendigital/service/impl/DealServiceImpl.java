package com.example.allendigital.service.impl;

import com.example.allendigital.modal.entities.Deal;
import com.example.allendigital.modal.res.CreateUpdateDealResponse;
import com.example.allendigital.modal.req.CreateUpdateDealRequest;
import com.example.allendigital.repo.DealRepo;
import com.example.allendigital.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {


    private final DealRepo dealRepo;

    @Value("${expiry.default.hours}")
    private int defaultHours;

    @Override
    public CreateUpdateDealResponse createDeal(CreateUpdateDealRequest createDealRequest) {

        Deal deal = createDealFromRequest(createDealRequest);

        dealRepo.save(deal);

        return CreateUpdateDealResponse.builder()
                .dealId(deal.getDealId())
                .successful(true)
                .build();
    }

    private Deal createDealFromRequest(CreateUpdateDealRequest createDealRequest) {
        //ModelMapper
        Deal deal = new Deal();
        deal.setProduct(createDealRequest.getProductId());
        if(createDealRequest.getExpiry()==null){
            deal.setDealExpiry(ZonedDateTime.now().plusHours(defaultHours));
        } else {
            deal.setDealExpiry(createDealRequest.getExpiry());
        }
        deal.setNumberOfItems(createDealRequest.getNumberOfItems());
        return deal;
    }

    @Override
    public CreateUpdateDealResponse updateDeal(long dealId,
                                               CreateUpdateDealRequest updateDealRequest) {


        Optional<Deal> dealOptional = dealRepo.findById(dealId);

        if (dealOptional.isPresent()){
            Deal deal = dealOptional.get();
            //Model Mapper for null check
            //
            //
            if(updateDealRequest.getExpiry()!=null){
                deal.setDealExpiry(updateDealRequest.getExpiry());
            }

            if(updateDealRequest.getNumberOfItems()!=null){
                deal.setNumberOfItems(updateDealRequest.getNumberOfItems());
            }

            dealRepo.save(deal);

            return CreateUpdateDealResponse.builder()
                    .dealId(deal.getDealId())
                    .successful(true)
                    .build();
        }


        return CreateUpdateDealResponse.builder()
                .dealId(-99)
                .successful(false)
                .build();
    }

    @Override
    public CreateUpdateDealResponse closeDeal(long dealId) {


        Optional<Deal> dealOptional = dealRepo.findById(dealId);

        if (dealOptional.isPresent()){
            Deal deal = dealOptional.get();
            deal.setDealExpiry(ZonedDateTime.now());
            dealRepo.save(deal);

            return CreateUpdateDealResponse.builder()
                    .dealId(deal.getDealId())
                    .successful(true)
                    .build();
        }


        return CreateUpdateDealResponse.builder()
                .dealId(-99)
                .successful(false)
                .build();
    }
}

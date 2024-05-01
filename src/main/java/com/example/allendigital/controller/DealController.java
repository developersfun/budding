package com.example.allendigital.controller;


import com.example.allendigital.modal.res.CreateUpdateDealResponse;
import com.example.allendigital.modal.req.CreateUpdateDealRequest;
import com.example.allendigital.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deal")
public class DealController {

    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("")
    public ResponseEntity<CreateUpdateDealResponse> createDeal(
            @RequestBody CreateUpdateDealRequest createDealRequest){

        CreateUpdateDealResponse response = dealService.createDeal(createDealRequest);

        if (!response.isSuccessful()){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateUpdateDealResponse> updateDeal(
            @PathVariable(name = "id") Long dealId,
            @RequestBody CreateUpdateDealRequest updateDealRequest){
        CreateUpdateDealResponse response = dealService.updateDeal(dealId, updateDealRequest);
        if (!response.isSuccessful()){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CreateUpdateDealResponse> closeDeal(
            @PathVariable(name = "id") Long dealId){

        CreateUpdateDealResponse response = dealService.closeDeal(dealId);
        if (!response.isSuccessful()){
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.example.allendigital.controller;


import com.example.allendigital.modal.req.CreateUpdateDealRequest;
import com.example.allendigital.modal.req.CreateUpdateOrderRequest;
import com.example.allendigital.modal.res.CreateUpdateDealResponse;
import com.example.allendigital.service.DealService;
import com.example.allendigital.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> createOrder(
            @PathVariable(name = "userId") Long userId,
            @RequestBody CreateUpdateOrderRequest createOrderRequest){

        String response = orderService.createOrder(userId, createOrderRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

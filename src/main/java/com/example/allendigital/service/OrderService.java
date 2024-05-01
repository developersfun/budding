package com.example.allendigital.service;

import com.example.allendigital.modal.req.CreateUpdateOrderRequest;

public interface OrderService {
    String createOrder(Long userId, CreateUpdateOrderRequest createOrderRequest);
}

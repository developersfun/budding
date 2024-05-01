package com.example.allendigital.service.impl;

import com.example.allendigital.modal.entities.Deal;
import com.example.allendigital.modal.req.CreateUpdateOrderRequest;
import com.example.allendigital.repo.DealRepo;
import com.example.allendigital.repo.OrderRepo;
import com.example.allendigital.service.OrderService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;
import java.util.Optional;

import static com.example.allendigital.service.impl.OrderServiceImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock private DealRepo dealRepo;
    @Mock private OrderRepo orderRepo;
    @MockBean
    private OrderServiceImpl orderServiceImpl;

    private CreateUpdateOrderRequest createUpdateOrderRequest;
    private Deal deal;

    @BeforeEach
    public void setUp(){
        createUpdateOrderRequest = new CreateUpdateOrderRequest();
        createUpdateOrderRequest.setDealId(1);
        createUpdateOrderRequest.setNumberOfItems(10);

        orderServiceImpl = new OrderServiceImpl(dealRepo, orderRepo);

        deal = new Deal();
        deal.setDealId(1);
        deal.setDealExpiry(ZonedDateTime.now().plusHours(2));
        deal.setProduct(1);
        deal.setNumberOfItems(10);
    }

    @Test
    public void testNoUser(){
        String response = orderServiceImpl.createOrder(null, createUpdateOrderRequest);
        assertEquals(NO_USER_PRESENT, response);
    }

    @Test
    public void testExpired(){
        when(dealRepo.findById(any())).thenReturn(Optional.of(deal));

        deal.setDealExpiry(ZonedDateTime.now().minusMinutes(10));
        String response = orderServiceImpl.createOrder(1L, createUpdateOrderRequest);
        assertEquals(DEAL_ALREADY_EXPIRED, response);
    }

    @Test
    public void emptyDeal(){
        when(dealRepo.findById(any())).thenReturn(Optional.empty());
        String response = orderServiceImpl.createOrder(1L, createUpdateOrderRequest);
        assertEquals(DEAL_NOT_PRESENT_OR_ACTIVE, response);
    }
}
package com.example.allendigital.service.impl;

import com.example.allendigital.modal.entities.Deal;
import com.example.allendigital.modal.entities.Order;
import com.example.allendigital.modal.req.CreateUpdateOrderRequest;
import com.example.allendigital.repo.DealRepo;
import com.example.allendigital.repo.OrderRepo;
import com.example.allendigital.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    public static final String NO_ORDER_DETAILS_PRESENT = "No Order Details Present";
    public static final String NO_USER_PRESENT = "No User Present";
    public static final String DEAL_NOT_PRESENT_OR_ACTIVE = "Deal Not Present or Active";
    public static final String DEAL_ALREADY_EXPIRED = "Deal already expired";
    public static final String ITEMS_UNAVAILABLE = "Items Unavailable";
    private final DealRepo dealRepo;
    private final OrderRepo orderRepo;

    @Override
    public String createOrder(Long userId, CreateUpdateOrderRequest createOrderRequest) {

        if(createOrderRequest==null){
            return NO_ORDER_DETAILS_PRESENT;
        }
        if(userId == null){
            return NO_USER_PRESENT;
        }

        Optional<Deal> dealOptional = dealRepo.findById(createOrderRequest.getDealId());

        if (!dealOptional.isPresent()){
            return DEAL_NOT_PRESENT_OR_ACTIVE;
        }

        Deal deal = dealOptional.get();

        if(deal.getDealExpiry().isBefore(ZonedDateTime.now())){
            return DEAL_ALREADY_EXPIRED;
        }

        if(deal.getNumberOfItems()< createOrderRequest.getNumberOfItems()){
            return ITEMS_UNAVAILABLE;
        }

        deal.setNumberOfItems(deal.getNumberOfItems()- createOrderRequest.getNumberOfItems());

        Order order = persistOrder(userId, createOrderRequest, deal);
        return "Order Id : " + order.getOrderId();
    }

    @Transactional
    public Order persistOrder(Long userId, CreateUpdateOrderRequest createOrderRequest, Deal deal) {
        dealRepo.save(deal);

        Order order = new Order();
        order.setDealId(deal.getDealId());
        order.setUserId(userId);
        order.setSuccessful(true);
        order.setTime(ZonedDateTime.now());
        order.setNumberOfItems(createOrderRequest.getNumberOfItems());

        return orderRepo.save(order);
    }
}

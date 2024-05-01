package com.example.allendigital.repo;

import com.example.allendigital.modal.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}

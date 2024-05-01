package com.example.allendigital.repo;

import com.example.allendigital.modal.entities.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepo extends JpaRepository<Deal, Long> {
}

package com.example.deli.repository;

import com.example.deli.domain.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordering, Long> {
}

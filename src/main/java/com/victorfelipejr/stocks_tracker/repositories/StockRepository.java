package com.victorfelipejr.stocks_tracker.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.victorfelipejr.stocks_tracker.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
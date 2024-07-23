package com.victorfelipejr.stocks_tracker.repositories;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}

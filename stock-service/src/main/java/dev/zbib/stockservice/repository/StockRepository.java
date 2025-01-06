package dev.zbib.stockservice.repository;

import dev.zbib.stockservice.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}

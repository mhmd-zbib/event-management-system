package dev.zbib.stockservice.service;

import dev.zbib.stockservice.dto.CreateStockRequest;
import dev.zbib.stockservice.entity.Stock;
import dev.zbib.stockservice.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    private StockRepository stockRepository;

    public void createStock(CreateStockRequest req) {
        Stock stock = Stock.builder()
                .id(req.getId())
                .isAvailable(req.isAvailable())
                .totalCapacity(req.getTotalCapacity())
                .availableCapacity(req.getTotalCapacity())
                .thresholdCapacity(req.getThresholdCapacity())
                .build();
        stockRepository.save(stock);
    }

    public Stock getStock(String id) {
        Stock stock = stockRepository.findById(id).orElseThrow(null);
        return StockRepository.
    }

}

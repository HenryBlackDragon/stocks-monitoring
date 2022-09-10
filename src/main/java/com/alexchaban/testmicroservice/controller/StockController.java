package com.alexchaban.testmicroservice.controller;

import com.alexchaban.testmicroservice.model.Stock;
import com.alexchaban.testmicroservice.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
public class StockController {

  private final StockService stockService;

  @GetMapping("/stocks/{ticker}")
  public Stock getStock(String ticker) {
    return stockService.getStockByTicker(ticker);
  }
}

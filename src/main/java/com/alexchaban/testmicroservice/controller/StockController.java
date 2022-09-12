package com.alexchaban.testmicroservice.controller;

import com.alexchaban.testmicroservice.model.Stock;
import com.alexchaban.testmicroservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;

  @GetMapping("/stocks/{ticker}")
  public Stock getStock(String ticker) {
    return stockService.getStockByTicker(ticker);
  }
}

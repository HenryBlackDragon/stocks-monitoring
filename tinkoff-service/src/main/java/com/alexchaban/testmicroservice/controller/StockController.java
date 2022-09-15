package com.alexchaban.testmicroservice.controller;

import com.alexchaban.testmicroservice.dto.FigiesDto;
import com.alexchaban.testmicroservice.dto.StockPricesDto;
import com.alexchaban.testmicroservice.dto.StocksDto;
import com.alexchaban.testmicroservice.dto.TickersDto;
import com.alexchaban.testmicroservice.model.Stock;
import com.alexchaban.testmicroservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {

  private final StockService stockService;

  @GetMapping("/stocks/{ticker}")
  public Stock getStock(@PathVariable String ticker) {
    return stockService.getStockByTicker(ticker);
  }

  @PostMapping("/stocks/getStocksByTickers")
  public StocksDto getStocksByTicker(@RequestBody TickersDto tickersDto) {
    return stockService.getStocksByTickers(tickersDto);
  }

  @PostMapping("/prices")
  public StockPricesDto getPrices(@RequestBody FigiesDto figiesDto) {
    return stockService.getPrices(figiesDto);
  }
}

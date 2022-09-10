package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.model.Stock;
import lombok.AllArgsConstructor;
import ru.tinkoff.invest.openapi.OpenApi;

@AllArgsConstructor
public class TinkoffStockService implements StockService {

  private final OpenApi openApi;
  @Override
  public Stock getStockByTicker(String ticker) {
    return null;
  }
}

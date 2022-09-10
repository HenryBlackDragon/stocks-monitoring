package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.model.Stock;

public interface StockService {

  Stock getStockByTicker(String ticker);
}

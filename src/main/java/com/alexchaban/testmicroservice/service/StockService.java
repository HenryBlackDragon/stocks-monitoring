package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.dto.StocksDto;
import com.alexchaban.testmicroservice.dto.TickersDto;
import com.alexchaban.testmicroservice.model.Stock;

public interface StockService {

  Stock getStockByTicker(String ticker);

  StocksDto getStocksByTickers(TickersDto tickers);
}

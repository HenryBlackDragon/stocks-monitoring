package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.dto.FigiesDto;
import com.alexchaban.testmicroservice.dto.StockPrice;
import com.alexchaban.testmicroservice.dto.StockPricesDto;
import com.alexchaban.testmicroservice.dto.StocksDto;
import com.alexchaban.testmicroservice.dto.TickersDto;
import com.alexchaban.testmicroservice.model.Stock;

public interface StockService {

  Stock getStockByTicker(String ticker);

  StocksDto getStocksByTickers(TickersDto tickers);

  StockPricesDto getPrices(FigiesDto figies);

  StockPrice getPrice(String figi);
}

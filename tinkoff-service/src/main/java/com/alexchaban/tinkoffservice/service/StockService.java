package com.alexchaban.tinkoffservice.service;

import com.alexchaban.tinkoffservice.dto.FigiesDto;
import com.alexchaban.tinkoffservice.dto.StockPrice;
import com.alexchaban.tinkoffservice.dto.StockPricesDto;
import com.alexchaban.tinkoffservice.dto.StocksDto;
import com.alexchaban.tinkoffservice.dto.TickersDto;
import com.alexchaban.tinkoffservice.model.Stock;

public interface StockService {

  Stock getStockByTicker(String ticker);

  StocksDto getStocksByTickers(TickersDto tickers);

  StockPricesDto getPrices(FigiesDto figies);

  StockPrice getPrice(String figi);
}

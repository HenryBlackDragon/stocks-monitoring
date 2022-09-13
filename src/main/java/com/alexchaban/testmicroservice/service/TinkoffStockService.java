package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.dto.StocksDto;
import com.alexchaban.testmicroservice.dto.TickersDto;
import com.alexchaban.testmicroservice.exception.StockNotFoundException;
import com.alexchaban.testmicroservice.model.Currency;
import com.alexchaban.testmicroservice.model.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

@RequiredArgsConstructor
@Service
public class TinkoffStockService implements StockService {

  private final OpenApi openApi;

  @Override
  public Stock getStockByTicker(String ticker) {
    var context = openApi.getMarketContext();
    var listCF = context.searchMarketInstrumentsByTicker(ticker);
    var list = listCF.join().getInstruments();

    if (list.isEmpty()) {
      throw new StockNotFoundException(String.format("Stock %S not found.", ticker));
    }

    var item = list.get(0);

    return new Stock(
        item.getTicker(),
        item.getFigi(),
        item.getName(),
        item.getType().getValue(),
        Currency.valueOf(item.getCurrency().getValue()),
        "TINKOFF"
    );
  }

  @Override
  @Async
  public StocksDto getStocksByTickers(TickersDto tickers) {
    var context = openApi.getMarketContext();
    final List<CompletableFuture<MarketInstrumentList>> markerInstrument = new ArrayList<>();

    tickers.getTickers()
        .forEach(ticker -> markerInstrument.add(context.searchMarketInstrumentsByTicker(ticker)));
    final List<Stock> stocks = markerInstrument.stream()
        .map(CompletableFuture::join)
        .map(mi -> {
          if (!mi.getInstruments().isEmpty()) {
            return mi.getInstruments().get(0);
          }
          return null;
        })
        .filter(Objects::nonNull)
        .map(mi -> new Stock(
            mi.getTicker(),
            mi.getFigi(),
            mi.getName(),
            mi.getType().getValue(),
            Currency.valueOf(mi.getCurrency().getValue()),
            "TINKOFF"
        ))
        .toList();

    return new StocksDto(stocks);
  }
}

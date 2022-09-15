package com.alexchaban.tinkoffservice.service;

import com.alexchaban.tinkoffservice.dto.FigiesDto;
import com.alexchaban.tinkoffservice.dto.StockPrice;
import com.alexchaban.tinkoffservice.dto.StockPricesDto;
import com.alexchaban.tinkoffservice.dto.StocksDto;
import com.alexchaban.tinkoffservice.dto.TickersDto;
import com.alexchaban.tinkoffservice.exception.StockNotFoundException;
import com.alexchaban.tinkoffservice.model.Currency;
import com.alexchaban.tinkoffservice.model.Stock;
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

    return new Stock(item.getTicker(), item.getFigi(), item.getName(), item.getType().getValue(),
        Currency.valueOf(item.getCurrency().getValue()), "TINKOFF");
  }

  @Override
  @Async
  public StocksDto getStocksByTickers(TickersDto tickers) {
    var context = openApi.getMarketContext();
    final List<CompletableFuture<MarketInstrumentList>> markerInstrument = new ArrayList<>();

    tickers.getTickers()
        .forEach(ticker -> markerInstrument.add(context.searchMarketInstrumentsByTicker(ticker)));
    final List<Stock> stocks = markerInstrument.stream().map(CompletableFuture::join).map(mi -> {
      if (!mi.getInstruments().isEmpty()) {
        return mi.getInstruments().get(0);
      }
      return null;
    }).filter(Objects::nonNull).map(
        mi -> new Stock(mi.getTicker(), mi.getFigi(), mi.getName(), mi.getType().getValue(),
            Currency.valueOf(mi.getCurrency().getValue()), "TINKOFF")).toList();

    return new StocksDto(stocks);
  }

  @Override
  public StockPrice getPrice(String figi) {
    var orderBook = openApi.getMarketContext().getMarketOrderbook(figi, 0).join().get();
    return new StockPrice(figi, orderBook.getLastPrice().doubleValue());
  }

  @Override
  public StockPricesDto getPrices(FigiesDto figies) {
    var stockPrices = figies.getFigies().stream().map(this::getPrice).toList();
    return new StockPricesDto(stockPrices);
  }
}

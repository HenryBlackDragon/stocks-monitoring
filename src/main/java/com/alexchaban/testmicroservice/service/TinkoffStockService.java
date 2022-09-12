package com.alexchaban.testmicroservice.service;

import com.alexchaban.testmicroservice.exception.StockNotFoundException;
import com.alexchaban.testmicroservice.model.Currency;
import com.alexchaban.testmicroservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;

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
}

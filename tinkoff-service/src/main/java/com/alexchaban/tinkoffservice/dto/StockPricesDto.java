package com.alexchaban.tinkoffservice.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class StockPricesDto {

  private List<StockPrice> prices;
}

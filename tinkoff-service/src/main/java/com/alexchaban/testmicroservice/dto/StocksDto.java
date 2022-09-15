package com.alexchaban.testmicroservice.dto;

import com.alexchaban.testmicroservice.model.Stock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StocksDto {

  private List<Stock> stocks;
}

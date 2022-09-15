package com.alexchaban.testmicroservice.exception;

public class StockNotFoundException extends RuntimeException {

  public StockNotFoundException(String message) {
    super(message);
  }
}

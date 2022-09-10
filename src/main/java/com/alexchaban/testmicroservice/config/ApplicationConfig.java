package com.alexchaban.testmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
public class ApplicationConfig {

  @Bean
  public OpenApi api() {
    final String ssoToken = System.getenv("ssoToken");
    return new OkHttpOpenApi(ssoToken, true);
  }
}

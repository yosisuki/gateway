package com.tiket.tix.gateway.outbound.impl.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiket.tix.gateway.entity.configuration.GatewayApiConfiguration;
import com.tiket.tix.gateway.libraries.configuration.CommonEndPointService;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@ConditionalOnClass(Retrofit.class)
public class RetrofitConfiguration {

  @Bean
  public Retrofit retrofitGateway(GatewayApiConfiguration gatewayApiConfiguration, OkHttpClient
      okHttpClient) {
    Retrofit.Builder builder = new Retrofit.Builder();
    if (okHttpClient != null) {
      builder.client(okHttpClient);
    }
    builder.baseUrl(gatewayApiConfiguration.getHost());
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
    builder.addConverterFactory(JacksonConverterFactory.create(objectMapper));
    return builder.build();
  }

  @Bean
  public CommonEndPointService retrofitService(Retrofit
      retrofitGateway) {
    return retrofitGateway.create(CommonEndPointService.class);
  }
}
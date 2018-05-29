package com.tiket.tix.gateway.outbound.impl;

import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.libraries.configuration.CommonEndPointService;
import com.tiket.tix.gateway.outbound.api.GatewayOutboundService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayOutboundServiceImpl implements GatewayOutboundService{

  @Autowired
  private CommonEndPointService gatewayEndPointService;

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestGetAll(String url, Map<String, String> header,
      Map<String, String> requestParams) throws
      IOException {
    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          GatewayBaseResponse<Object> output = this.gatewayEndPointService.forwardRequestGetAll
              (url, header, requestParams).execute()
              .body();

          singleEmitter.onSuccess(output);

        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestGet(String
      url, Map<String, String> header)
      throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          GatewayBaseResponse<Object> output = this.gatewayEndPointService.forwardRequestGet
              (url, header).execute()
              .body();

          singleEmitter.onSuccess(output);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestPost(String url, Map<String, String>
      header, Object object)
      throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          GatewayBaseResponse<Object> output = this.gatewayEndPointService.forwardRequestPost
              (url, header, object)
              .execute().body();
          singleEmitter.onSuccess(output);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestPut(String
      url, Map<String, String> header,
      Object object) throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          GatewayBaseResponse<Object> output = this.gatewayEndPointService.forwardRequestPut
              (url, header, object).execute().body();
          singleEmitter.onSuccess(output);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestDelete(String url, Map<String, String>
      header) throws
      IOException {
    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          GatewayBaseResponse<Object> output = this.gatewayEndPointService.forwardRequestDelete
              (url, header).execute().body();
          singleEmitter.onSuccess(output);
        }).subscribeOn(Schedulers.io());


  }
}

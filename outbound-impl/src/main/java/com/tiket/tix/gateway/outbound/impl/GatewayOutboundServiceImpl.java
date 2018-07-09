package com.tiket.tix.gateway.outbound.impl;

import com.tiket.tix.common.libraries.JSONHelper;
import com.tiket.tix.common.rest.web.model.response.BaseResponse;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.libraries.configuration.CommonEndPointService;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.libraries.utility.OutboundHelper;
import com.tiket.tix.gateway.outbound.api.GatewayOutboundService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

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
          Response<Object> output = this.gatewayEndPointService.forwardRequestGetAll
              (url, header, requestParams)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestGet(String
      url, Map<String, String> header)
      throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {

          Response<Object> output = this.gatewayEndPointService.forwardRequestGet
              (url, header)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestPost(String url, Map<String, String>
      header, Object object)
      throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {

          Response<Object> output = this.gatewayEndPointService.forwardRequestPost
              (url, header, object)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestPut(String
      url, Map<String, String> header,
      Object object) throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {

          Response<Object> output = this.gatewayEndPointService.forwardRequestPut
              (url, header, object)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestDelete(String url, Map<String, String>
      header) throws
      IOException {
    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {
          Response<Object> output = this.gatewayEndPointService.forwardRequestDelete
              (url, header)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());


  }

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequestPutWithoutBody(String
      url, Map<String, String> header) throws IOException {

    return Single.<GatewayBaseResponse<Object>>create(
        singleEmitter -> {

          Response<Object> output = this.gatewayEndPointService.forwardRequestPutWithoutBody
              (url, header)
              .execute();

          OutboundHelper.checkStatusResponse(output);

          GatewayBaseResponse<Object> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setData(output.body());

          singleEmitter.onSuccess(gatewayBaseResponse);
        }).subscribeOn(Schedulers.io());
  }

}

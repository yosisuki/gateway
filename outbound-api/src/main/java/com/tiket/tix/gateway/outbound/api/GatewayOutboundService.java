package com.tiket.tix.gateway.outbound.api;

import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import io.reactivex.Single;
import java.io.IOException;
import java.util.Map;

public interface GatewayOutboundService {

  Single<GatewayBaseResponse<Object>> forwardRequestGetAll(String
      endPoint, Map<String, String> header, Map<String, String> requestParams) throws
      IOException;

  Single<GatewayBaseResponse<Object>> forwardRequestGet(String
      endPoint, Map<String, String> header) throws
      IOException;
  Single<GatewayBaseResponse<Object>> forwardRequestPost(String endPoint, Map<String, String> header, Object
      object) throws
      IOException;
  Single<GatewayBaseResponse<Object>> forwardRequestPut(String endPoint,Map<String, String> header,
      Object object) throws
      IOException;

  Single<GatewayBaseResponse<Object>> forwardRequestDelete(String endPoint, Map<String, String>
      header) throws
      IOException;
}
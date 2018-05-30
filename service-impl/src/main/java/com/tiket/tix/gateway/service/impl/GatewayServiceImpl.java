package com.tiket.tix.gateway.service.impl;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.constant.enums.RequestMethods;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.outbound.api.GatewayOutboundService;
import com.tiket.tix.gateway.service.api.GatewayService;
import com.tiket.tix.gateway.service.api.PrivilegeService;
import io.reactivex.Single;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayServiceImpl implements GatewayService {

  @Autowired
  private GatewayOutboundService gatewayOutboundService;

  @Autowired
  private PrivilegeService privilegeService;

  @Override
  public Single<GatewayBaseResponse<Object>> forwardRequest(MandatoryRequest mandatoryRequest,
      String url, String param,
      Object object,
      RequestMethods requestMethods,
      Map<String, String> requestParams,
      String requiredPrivilege, String groupName, String privileges, SessionData sessionData) {

      Map<String, String> header = new HashMap<>();
      header.put("storeId", mandatoryRequest.getStoreId());
      header.put("channelId", mandatoryRequest.getChannelId());
      header.put("username", mandatoryRequest.getUsername());
      header.put("requestId", mandatoryRequest.getRequestId());
      header.put("serviceId", mandatoryRequest.getServiceId());

      return this.privilegeService.checkAuthorized(requiredPrivilege, privileges)
          .flatMap(
              authorized -> {
        try {
          if(requestMethods.getValue().equals(RequestMethods.GET.getValue()) && !isExistParam(param)){
            return this.gatewayOutboundService.forwardRequestGetAll(url, header,
                requestParams);
          } else if(requestMethods.getValue().equals(RequestMethods.GET.getValue()) && isExistParam(param)){
            return this.gatewayOutboundService.forwardRequestGet(url, header);
          } else if(requestMethods.getValue().equals(RequestMethods.POST.getValue())
              && isExistBody(object) && !isExistParam(param)){
            return this.gatewayOutboundService.forwardRequestPost(url, header,
                object);
          } else if(requestMethods.getValue().equals(RequestMethods.PUT.getValue()) &&
              isExistBody(object) &&
              isExistParam(param)){
            return this.gatewayOutboundService.forwardRequestPut(url + "/" + param, header ,
                object);
          } else {
            return this.gatewayOutboundService.forwardRequestDelete(url + "/" + param, header);
          }
        } catch (IOException e) {
          throw new BusinessLogicException(ResponseCode.REQUEST_NOT_VALID.getCode(), ResponseCode
              .REQUEST_NOT_VALID.getMessage());
        }
      }).map(gatewayBaseResponse -> {
            gatewayBaseResponse.setSessionData(sessionData);
            return gatewayBaseResponse;
          })
          .flatMap(gatewayBaseResponse -> privilegeService.getAuthorizedPrivileges(mandatoryRequest
              , privileges)
              .map(privilegeResponses -> {
                gatewayBaseResponse.setPrivileges(privilegeResponses);
                return gatewayBaseResponse;
          }))
          .flatMap(gatewayBaseResponse -> privilegeService.getAuthorizedPrivilegesInCurrentSlug(mandatoryRequest
              , privileges, groupName)
              .map(privilegeResponses -> {
                gatewayBaseResponse.setCurrentSlugPrivileges(privilegeResponses);
                return gatewayBaseResponse;
              }));
  }

  private boolean isExistBody(Object object) {
    return object != null;
  }

  private Boolean isExistParam(String param){
    return param != null;
  }
}

package com.tiket.tix.gateway.service.impl;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.MonolithSession;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import com.tiket.tix.gateway.libraries.utility.CommonHelper;
import com.tiket.tix.gateway.service.api.PrivilegeService;
import com.tiket.tix.gateway.service.api.SessionService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiket.tix.gateway.outbound.api.MonolithOutboundService;

@Service
public class SessionServiceImpl implements SessionService {

  @Autowired
  private PrivilegeService privilegeService;

  @Autowired
  private MonolithOutboundService monolithOutboundService;

  @Override
  public Single<GatewayBaseResponse<String>> getSessionData(
      MandatoryRequest mandatoryRequest,
      String privilegeToCheck,
      String roles,
      SessionData sessionData
  ) {

    return this
        .privilegeService.getAuthorizedPrivileges(mandatoryRequest, privilegeToCheck)
        .map(privileges -> {
          GatewayBaseResponse<String> gatewayBaseResponse = new GatewayBaseResponse<>();
          gatewayBaseResponse.setPrivileges(privileges);
          gatewayBaseResponse.setRoles(CommonHelper.convertStringToListInteger(roles, ","));
          gatewayBaseResponse.setSessionData(sessionData);
          gatewayBaseResponse.setCode(ResponseCode.SUCCESS.getCode());
          gatewayBaseResponse.setMessage(ResponseCode.SUCCESS.getMessage());
          gatewayBaseResponse.setServerTime(CommonHelper.newDate());
          return gatewayBaseResponse;
        }).subscribeOn(Schedulers.io());
  }

  @Override
  public MonolithSession getMonolithSession(String sessionId) {
    return monolithOutboundService.findMonolithSession(sessionId);
  }

}

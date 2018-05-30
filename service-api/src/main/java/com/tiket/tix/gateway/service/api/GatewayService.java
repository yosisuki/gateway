package com.tiket.tix.gateway.service.api;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.constant.enums.RequestMethods;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import io.reactivex.Single;
import java.util.Map;

public interface GatewayService {
  Single<GatewayBaseResponse<Object>> forwardRequest(MandatoryRequest mandatoryRequest, String slug,
      String param, Object
      object,
      RequestMethods
      requestMethods, Map<String, String> requestParams,
      String requiredPrivilege, String groupName, String privileges, SessionData sessionData);
}
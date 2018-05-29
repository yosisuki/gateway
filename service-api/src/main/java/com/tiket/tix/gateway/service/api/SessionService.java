package com.tiket.tix.gateway.service.api;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import io.reactivex.Single;

public interface SessionService {
  Single<GatewayBaseResponse<String>> getSessionData(
      MandatoryRequest mandatoryRequest,
      String privilegeToCheck,
      String roles,
      SessionData sessionData
  );
}

package com.tiket.tix.gateway.outbound.impl;

import com.tiket.tix.gateway.entity.MonolithSession;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.outbound.api.MonolithOutboundService;
import com.tiket.tix.gateway.outbound.impl.configuration.MonolithEndPointService;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class MonolithOutboundServiceImpl implements MonolithOutboundService {

  @Autowired
  private MonolithEndPointService monolithEndPointService;

  private static String TIX_SESSION = "tixsession";

  @Override
  public MonolithSession findMonolithSession(String sessionId) {
    Validate.notNull(sessionId);

    try {
      Map<String, String> headerMap = new HashMap<>();
      headerMap.put(TIX_SESSION, sessionId);
      Response<MonolithSession> monolithSessionResponse = this
          .monolithEndPointService.findMonolithSession(headerMap)
          .execute();
      return monolithSessionResponse.body();

    } catch (Exception e){
      throw new BusinessLogicException(ResponseCode.SYSTEM_ERROR.getCode(), ResponseCode
          .SYSTEM_ERROR.getMessage());

    }
  }
}
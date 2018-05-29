package com.tiket.tix.gateway.entity.constant.unit.test;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequestBuilder;
import com.tiket.tix.common.rest.web.model.response.BaseResponse;
import com.tiket.tix.common.rest.web.model.response.CommonResponse;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.constant.fields.MandatoryFields;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponseBuilder;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponse;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponseBuilder;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import com.tiket.tix.gateway.entity.outbound.SessionDataBuilder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface CommonTestVariable {

  String STORE_ID = "TIKETCOM";
  String REQUEST_ID = "REQUEST_ID";
  String SERVICE_ID = "LOGIN";
  String CHANNEL_ID = "iOS";
  String USERNAME = "testuser";
  String ROLES = "1,2,3";
  String CONTENT_TYPE = "application/json";
  public static String MM_MODULE = "207";
  String TODAY = "2018-02-13";
  List<Integer> LIST_INTEGER = Arrays.asList(1,2,3);

  MandatoryRequest MANDATORY_REQUEST = new MandatoryRequestBuilder()
      .withStoreId(STORE_ID)
      .withRequestId(REQUEST_ID)
      .withServiceId(SERVICE_ID)
      .withChannelId(CHANNEL_ID)
      .withUsername(USERNAME)
      .build();

  SessionData SESSION_DATA = new SessionDataBuilder()
      .withUsername(USERNAME).build();

  SessionData SESSION_DATA_WITH_LANG = new SessionDataBuilder()
      .withUsername(USERNAME).withLang("en").build();

  BaseResponse BASE_RESPONSE = CommonResponse.constructResponse(ResponseCode.SUCCESS.getCode(),
      ResponseCode.SUCCESS.getMessage(), null, null);

  @SuppressWarnings("unchecked")
  GatewayBaseResponse GATEWAY_BASE_RESPONSE = new GatewayBaseResponseBuilder()
      .withCode(ResponseCode.SUCCESS.getCode())
      .withMessage(ResponseCode.SUCCESS.getMessage())
      .withErrors(null)
      .withData(null)
      .build();

  static Map<String, String> getMandatoryRequestAsMap() {
    Map<String, String> mandatoryRequestMap = new HashMap<>();
    mandatoryRequestMap.put(MandatoryFields.STORE_ID, STORE_ID);
    mandatoryRequestMap.put(MandatoryFields.CHANNEL_ID, CHANNEL_ID);
    mandatoryRequestMap.put(MandatoryFields.REQUEST_ID, REQUEST_ID);
    mandatoryRequestMap.put(MandatoryFields.SERVICE_ID, SERVICE_ID);
    mandatoryRequestMap.put(MandatoryFields.USERNAME, USERNAME);

    return mandatoryRequestMap;
  }

  static Map<String, String> getMandatoryRequestAsMapWithLang() {
    Map<String, String> mandatoryRequestMap = new HashMap<>();
    mandatoryRequestMap.put(MandatoryFields.STORE_ID, STORE_ID);
    mandatoryRequestMap.put(MandatoryFields.CHANNEL_ID, CHANNEL_ID);
    mandatoryRequestMap.put(MandatoryFields.REQUEST_ID, REQUEST_ID);
    mandatoryRequestMap.put(MandatoryFields.SERVICE_ID, SERVICE_ID);
    mandatoryRequestMap.put(MandatoryFields.USERNAME, USERNAME);
    mandatoryRequestMap.put("lang", "en");

    return mandatoryRequestMap;
  }

  static List<PrivilegeResponse> getPrivilegeResponse(){
    List<PrivilegeResponse> privilegeResponses = new ArrayList<>();
    privilegeResponses.add(
        new PrivilegeResponseBuilder()
            .withPrivilegeId("320")
            .withPrivilegeName("coba")
            .build());

    privilegeResponses.add(
        new PrivilegeResponseBuilder()
            .withPrivilegeId("321")
            .withPrivilegeName("coba1")
            .build());
    return privilegeResponses;
  }

  List<PrivilegeResponse> PRIVILEGE_RESPONSE = getPrivilegeResponse();
  String PRIVILEGES = "1,2,3,4,5,6,7";
  static Date getMockDate() {
    Calendar cal = Calendar.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    LocalDate localDate = LocalDate.parse(TODAY, formatter);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    cal.setTime(date);
    return cal.getTime();
  }

  Date MOCK_DATE = getMockDate();
}

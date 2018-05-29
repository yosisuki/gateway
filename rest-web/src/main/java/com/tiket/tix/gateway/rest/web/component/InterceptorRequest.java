package com.tiket.tix.gateway.rest.web.component;

import com.github.ooxi.phparser.SerializedPhpParser;
import com.tiket.tix.common.libraries.JSONHelper;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequestBuilder;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import com.tiket.tix.gateway.entity.constant.fields.LanguageFields;
import com.tiket.tix.gateway.entity.constant.fields.MandatoryFields;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.service.api.CacheService;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class InterceptorRequest extends HandlerInterceptorAdapter {

  @Autowired
  private CacheService cacheService;

  private static final String STORE_ID = "TIKETCOM";
  private static final String CHANNEL_ID = "WEB";
  private static final String USERNAME = "guest";
  private static final String SERVICE_ID = "gateway";

  private static final String SESSION_NAME = "PHPSESSID";
  private static final String KEY_PRIVILEGE = "priv";
  private static final String KEY_ROLE = "role";

  private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorRequest.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if(request.getRequestURL().toString().split("/")[3].equals("swagger-resources")){
      return true;
    }

    String session = this.validateSession(request);
    if (session.equals("")) {
      return true;
    }

    LOGGER.info("InterceptorRequest preHandle : PHPREDIS_SESSION:{}", session);

    String sessionData = this.cacheService.findCacheByKey("PHPREDIS_SESSION:" + session, String
        .class);

    LOGGER.info("InterceptorRequest preHandle : SESSION_DATA:{}", sessionData);
    if (!isExistSessionData(sessionData)) {
      throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
          .NOT_AUTHORIZED.getMessage());
    }
    String sessionId = this.getSessionId(sessionData);
    if (!isExistSessionId(sessionId)) {
      throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
          .NOT_AUTHORIZED.getMessage());
    }
    String authenticationData = this.cacheService.findCacheByKey(sessionId, String.class);
    if (!isExistAuthenticationData(authenticationData)) {
      throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
          .NOT_AUTHORIZED.getMessage());
    }
    SerializedPhpParser phparser = new SerializedPhpParser(authenticationData);
    String result = (String) phparser.parse();
    Map<String, String> parsedJson = JSONHelper.convertJsonInStringToObject(result, Map.class);

    MDC.put(BaseMongoFields.STORE_ID, STORE_ID);
    MDC.put(BaseMongoFields.CHANNEL_ID, CHANNEL_ID);
    MDC.put(BaseMongoFields.USERNAME, "guest");

    if(isExistUsername(parsedJson.get("username")) && !"".equals(parsedJson.get("username"))) {
      MDC.put(BaseMongoFields.USERNAME, parsedJson.get("username"));
      MDC.put(BaseMongoFields.BUSINESS_ID, parsedJson.get("business_id"));
      MDC.put(BaseMongoFields.PRIVILEGES, parsedJson.get(KEY_PRIVILEGE));
    }

    MDC.put(LanguageFields.LANG, this.getUserlang(request));
    String langQueryParam = request.getHeader("lang");
    if(langQueryParam != null && !langQueryParam.isEmpty()){
      MDC.put(LanguageFields.LANG, langQueryParam.toLowerCase());
    }

    Random rand = new Random();
    String nextInt = STORE_ID + rand.nextInt(10000) + 1;
    MDC.put(BaseMongoFields.REQUEST_ID, nextInt);
    MDC.put(BaseMongoFields.SERVICE_ID, SERVICE_ID);
    MDC.put(BaseMongoFields.PRIVILEGES, parsedJson.get(KEY_PRIVILEGE));
    MDC.put(BaseMongoFields.ROLES, parsedJson.get(KEY_ROLE));

    MandatoryRequest newMandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId(STORE_ID)
        .withChannelId(CHANNEL_ID)
        .withUsername(parsedJson.get("username"))
        .withRequestId(nextInt)
        .withServiceId(SERVICE_ID)
        .build();

    MDC.put(MandatoryFields.MANDATORY_REQUEST, newMandatoryRequest);
    request.setAttribute(MandatoryFields.MANDATORY_REQUEST, newMandatoryRequest);

    return true;
  }

  private boolean isExistUsername(String username) {
    return username != null;
  }

  private String validateSession(HttpServletRequest request){
    if(!request.getRequestURL().toString().split("/")[3].equals("swagger-resources")){
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        String cookieString = "";
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals(SESSION_NAME)) {
            cookieString = cookie.getValue();

            try {
              String[] cookieStrings = cookieString.split("~");
              cookieString = cookieStrings[cookieStrings.length - 1];
            } catch (Exception e) {
            }
            LOGGER.info("Cookies {}", cookieString);
            break;
          }
        }

        if (cookieString.equals("")) {
          throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
              .NOT_AUTHORIZED.getMessage());
        }
        return cookieString;

      } else {
        throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
            .NOT_AUTHORIZED.getMessage());
      }
    }
    return "";
  }

  private String getUserlang(HttpServletRequest request){
    String userlang = this.getValueFromCookie(request, "userlang");
    if(!isExistUserlang(userlang)){
      userlang = "id";
    }
    return userlang;
  }

  private Boolean isExistUserlang(String userlang){
    return !userlang.equals("");
  }

  private String getValueFromCookie(HttpServletRequest request, String param){
    String cookieString = "";
    Cookie[] cookies = request.getCookies();
    if(!isExistCookies(cookies)) {
      return "";
    }
    for(Cookie cookie : cookies){
      if(cookie.getName().equals(param)){
        cookieString = cookie.getValue();

        try{
          String[] cookieStrings = cookieString.split("~");
          cookieString = cookieStrings[cookieStrings.length - 1];
        } catch (Exception e){
          return "Failed to get value from cookie.";
        }

        break;
      }
    }
    return cookieString;
  }

  private Boolean isExistCookies(Cookie[] cookies){
    return cookies != null;
  }

  private Boolean isExistSessionData(String sessionData){
    return sessionData != null;
  }

  private Boolean isExistSessionId(String sessionId) {
    return sessionId != null;
  }

  private Boolean isExistAuthenticationData(String authenticationData) {
    return authenticationData != null;
  }

  private String getSessionId(String sessionData) {
    String sessionId = sessionData.split(";")[0];
    Integer index = sessionId.indexOf(":\"");
    sessionId = sessionId.substring(index + 2, index + 42);

    return sessionId;
  }
}
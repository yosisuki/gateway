package com.tiket.tix.gateway.rest.web.component;

import com.github.ooxi.phparser.SerializedPhpParser;
import com.tiket.tix.common.libraries.JSONHelper;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequestBuilder;
import com.tiket.tix.gateway.entity.MonolithSession;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import com.tiket.tix.gateway.entity.constant.fields.LanguageFields;
import com.tiket.tix.gateway.entity.constant.fields.MandatoryFields;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.service.api.CacheService;
import com.tiket.tix.gateway.service.api.SessionService;
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

  @Autowired
  private SessionService sessionService;

  private static final String STORE_ID = "TIKETCOM";
  private static final String CHANNEL_ID = "WEB";
  private static final String USERNAME = "guest";
  private static final String SERVICE_ID = "gateway";

  private static final String SESSION_NAME = "PHPSESSID";
  private static final String USERLANG = "userlang";
  private static final String LANG = "lang";

  private static final Logger LOGGER = LoggerFactory.getLogger(InterceptorRequest.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String session = this.validateSession(request);
    if (session.equals("")) {
      return true;
    }

    String langQueryParam = request.getHeader(LANG);
    if(langQueryParam != null && !langQueryParam.isEmpty()){
      langQueryParam = langQueryParam.toLowerCase();
    } else {
      langQueryParam = this.getUserlang(request);
    }

    MonolithSession monolithSession = this.sessionService.getMonolithSession(session);

    Random rand = new Random();
    String nextInt = STORE_ID + rand.nextInt(10000) + 1;

    MDC.put(BaseMongoFields.STORE_ID, STORE_ID);
    MDC.put(BaseMongoFields.CHANNEL_ID, CHANNEL_ID);
    MDC.put(BaseMongoFields.USERNAME, monolithSession.getUsername());
    MDC.put(BaseMongoFields.BUSINESS_ID, monolithSession.getBusinessId());
    MDC.put(BaseMongoFields.PRIVILEGES, monolithSession.getPriv());
    MDC.put(LanguageFields.LANG, langQueryParam.toLowerCase());
    MDC.put(BaseMongoFields.REQUEST_ID, nextInt);
    MDC.put(BaseMongoFields.SERVICE_ID, SERVICE_ID);
    MDC.put(BaseMongoFields.ROLES, monolithSession.getRole());

    MandatoryRequest newMandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId(STORE_ID)
        .withChannelId(CHANNEL_ID)
        .withUsername(monolithSession.getUsername())
        .withRequestId(nextInt)
        .withServiceId(SERVICE_ID)
        .build();

    MDC.put(MandatoryFields.MANDATORY_REQUEST, newMandatoryRequest);
    request.setAttribute(MandatoryFields.MANDATORY_REQUEST, newMandatoryRequest);

    return true;
  }

  private String validateSession(HttpServletRequest request){
    if(!request.getRequestURL().toString().split("/")[3].equals("swagger-resources")){
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        String cookieString = "";
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals(SESSION_NAME)) {
            cookieString = cookie.getValue();

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

  private String getValueFromCookie(HttpServletRequest request){
    String cookieString = "";
    Cookie[] cookies = request.getCookies();
    if(!isExistCookies(cookies)) {
      return "";
    }
    for(Cookie cookie : cookies){
      if(cookie.getName().equals(USERLANG)){
        return cookie.getValue();
      }
    }
    return cookieString;
  }

  private String getUserlang(HttpServletRequest request){
    String userlang = this.getValueFromCookie(request);
    if(!isExistUserlang(userlang)){
      userlang = "id";
    }
    return userlang;
  }

  private Boolean isExistUserlang(String userlang){
    return !userlang.equals("");
  }

  private Boolean isExistCookies(Cookie[] cookies){
    return cookies != null;
  }

}
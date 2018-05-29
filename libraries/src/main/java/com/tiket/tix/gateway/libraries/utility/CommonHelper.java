package com.tiket.tix.gateway.libraries.utility;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import com.tiket.tix.gateway.entity.constant.fields.LanguageFields;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.log4j.MDC;
import org.springframework.data.domain.Sort.Direction;

public class CommonHelper {
  private CommonHelper(){}

  public static Direction convertToSortDirection(String dir){
    Direction direction;

    switch (dir){
      case "desc":
      case "descending":
        direction = Direction.DESC;
        break;
      case "asc":
      case "ascending":
      default:
        direction = Direction.ASC;
        break;
    }

    return direction;
  }

  public static Map<String, String> convertMandatoryRequestToMap(MandatoryRequest mandatoryRequest){
    Map<String, String> headerMap = new HashMap<>();

    headerMap.put("storeId", mandatoryRequest.getStoreId());
    headerMap.put("channelId", mandatoryRequest.getChannelId());
    headerMap.put("requestId", mandatoryRequest.getRequestId());
    headerMap.put("serviceId", mandatoryRequest.getServiceId());
    headerMap.put("username", mandatoryRequest.getUsername());

    return headerMap;
  }

  public static SessionData getSessionData(){
    SessionData sessionData = new SessionData();
    sessionData.setUsername((String) MDC.get(BaseMongoFields.USERNAME));
    sessionData.setBusinessId((String)MDC.get(BaseMongoFields.BUSINESS_ID));
    sessionData.setLang((String)MDC.get(LanguageFields.LANG));

    return sessionData;
  }

  public static Date newDate(){
    return new Date();
  }

  public static List<Integer> convertStringToListInteger(String value, String separator){
    String valueToCheck = value == null ? "":value;
    Pattern pattern = Pattern.compile(separator);

    return pattern.splitAsStream(valueToCheck).map(Integer::valueOf).collect(
        Collectors.toList());
  }
}

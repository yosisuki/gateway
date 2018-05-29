package com.tiket.tix.gateway.libraries.utility;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import java.util.HashMap;
import java.util.Map;

public class MandatoryRequestHelper {

  private MandatoryRequestHelper(){}

  public static Map<String,String> buildMandatoryRequestHeader(MandatoryRequest mandatoryRequest){
    Map<String,String> returnedMandatoryRequest = new HashMap<>();
    returnedMandatoryRequest.put(BaseMongoFields.STORE_ID, mandatoryRequest.getStoreId());
    returnedMandatoryRequest.put(BaseMongoFields.CHANNEL_ID, mandatoryRequest.getChannelId());
    returnedMandatoryRequest.put(BaseMongoFields.SERVICE_ID, mandatoryRequest.getServiceId());
    returnedMandatoryRequest.put(BaseMongoFields.REQUEST_ID, mandatoryRequest.getRequestId());
    returnedMandatoryRequest.put(BaseMongoFields.USERNAME, mandatoryRequest.getUsername());
    return returnedMandatoryRequest;
  }
}

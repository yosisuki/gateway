package com.tiket.tix.gateway.libraries.utility;

import com.tiket.tix.common.libraries.JSONHelper;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

public class OutboundHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(OutboundHelper.class);

  private OutboundHelper(){}

  public static void checkStatusResponse(Response<Object> output) {
    Integer errorStatus = Integer.valueOf(output.raw().code());
    Boolean responseCode = errorStatus.equals(200);
    if( ! responseCode ) {
      try {
        String errorResponse = output.errorBody().string();

        LOGGER.info("Error Service Response - Response {} ", errorResponse);

        if(errorStatus.equals(404))
        {
          throw new BusinessLogicException(ResponseCode.PAGE_NOT_FOUND.getCode(), ResponseCode.PAGE_NOT_FOUND.getMessage());
        }

        Map<String, Object> baseResponse = (Map<String, Object>) JSONHelper
            .convertJsonInStringToObject(errorResponse, Object.class);
        throw new BusinessLogicException(String.valueOf(baseResponse.get("code")), String.valueOf(baseResponse.get("message")));
      } catch (IOException e) {
        LOGGER.info("IOException - Error {} ", e);
        throw new BusinessLogicException(ResponseCode.IO_EXCEPTION.getCode(), ResponseCode.IO_EXCEPTION.getMessage());
      }
    }

  }

}

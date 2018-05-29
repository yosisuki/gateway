package com.tiket.tix.gateway.rest.web.controller;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequestBuilder;
import com.tiket.tix.gateway.entity.constant.ApiPath;
import com.tiket.tix.gateway.entity.constant.enums.RequestMethods;
import com.tiket.tix.gateway.entity.constant.fields.BaseMongoFields;
import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import com.tiket.tix.gateway.entity.outbound.SessionData;
import com.tiket.tix.gateway.libraries.utility.CommonHelper;
import com.tiket.tix.gateway.service.api.GatewayEndPointService;
import com.tiket.tix.gateway.service.api.GatewayService;
import com.tiket.tix.gateway.service.api.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import retrofit2.http.POST;

@RestController
@RequestMapping(value = "")
@Api(value = "GatewayOutboundServiceImpl")
public class GatewayController {

  @Autowired
  private SessionService sessionService;

  @Autowired
  private GatewayService gatewayService;

  @Autowired
  private GatewayEndPointService endPointService;

  private static final String USERNAME = "username";
  private static final String GET_ALL_ACTION = "getAll";
  private static final String GET_ACTION = "get";
  private static final String POST_ACTION = "post";
  private static final String PUT_ACTION = "put";
  private static final String DELETE_ACTION = "delete";

  private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);

  @GetMapping(path = "/sessions")
  public DeferredResult<GatewayBaseResponse<String>> getSessionData() {

    SessionData sessionData = CommonHelper.getSessionData();

    DeferredResult<GatewayBaseResponse<String>> deferred = new DeferredResult<>();
    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String) MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String) MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String) MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String) MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String) MDC.get(BaseMongoFields.USERNAME))
        .build();

    this.sessionService
        .getSessionData(mandatoryRequest, (String) MDC.get(BaseMongoFields.PRIVILEGES),
            (String) MDC.get(BaseMongoFields.ROLES), sessionData)
        .subscribe(deferred::setResult, deferred::setErrorResult);

    return deferred;
  }

  @ApiOperation(value = "Receive Request and Forward to Desired API (Get Request)", notes = "Put "
      + "all mandatory "
      + "parameter")
  @RequestMapping(path = ApiPath.END_POINT, method = RequestMethod.GET)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardGetAll(
      @PathVariable("endPoint") String endPoint,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    LOGGER.info("receiveAndForwardGetAll MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint, GET_ALL_ACTION);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), null, null,
        RequestMethods.GET, requestParams,gatewayEndPoint.getPrivilegeId(), (String)MDC.get(BaseMongoFields
            .PRIVILEGES)).subscribe
        (deferred::setResult,
        deferred::setErrorResult);

    return deferred;
  }

  @ApiOperation(value = "Receive Request and Forward to Desired API (Get Request with Param)",
      notes =
          "Put all mandatory "
              + "parameter")
  @RequestMapping(path = ApiPath.END_POINT + ApiPath.PARAM, method = RequestMethod.GET)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardGet(
      @PathVariable("endPoint") String endPoint,
      @PathVariable("param") String param,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint, GET_ACTION);

    LOGGER.info("receiveAndForwardGet MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), param, null,
        RequestMethods.GET, null, gatewayEndPoint.getPrivilegeId(),(String)MDC.get(BaseMongoFields
            .PRIVILEGES))
        .subscribe(deferred::setResult, deferred::setErrorResult);

    return deferred;
  }

  @ApiOperation(value = "Receive Request and Forward to Desired API (Post Request with Param)",
      notes =
          "Put all mandatory "
              + "parameter")
  @RequestMapping(path = ApiPath.END_POINT, method = RequestMethod.POST)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardPost(
      @PathVariable("endPoint") String endPoint,
      @RequestBody Object object,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint, POST_ACTION);

    LOGGER.info("receiveAndForwardPost MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), null, object,
        RequestMethods.POST, null, gatewayEndPoint.getPrivilegeId(), (String)MDC.get
            (BaseMongoFields
        .PRIVILEGES))
        .subscribe(deferred::setResult, deferred::setErrorResult);

    return deferred;
  }

  @RequestMapping(path = ApiPath.END_POINT + ApiPath.PARAM, method = RequestMethod.PUT)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardPut(
      @PathVariable("endPoint") String endPoint,
      @PathVariable("param") String param,
      @RequestBody Object object,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    LOGGER.info("receiveAndForwardPut MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint, PUT_ACTION);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), param, object,
        RequestMethods.PUT, requestParams, gatewayEndPoint.getPrivilegeId(),(String)MDC.get
            (BaseMongoFields
            .PRIVILEGES))
        .subscribe(deferred::setResult, deferred::setErrorResult);

    return deferred;
  }

  @RequestMapping(path = ApiPath.END_POINT + ApiPath.ACTION + ApiPath.PARAM, method = RequestMethod
      .PUT)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardPutWithAction(
      @PathVariable("endPoint") String endPoint,
      @PathVariable("action") String action,
      @PathVariable("param") String param,
      @RequestBody Object object,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    LOGGER.info("receiveAndForwardPut MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint + "/" +  action, PUT_ACTION);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), param, object,
        RequestMethods.PUT, requestParams, gatewayEndPoint.getPrivilegeId(),(String)MDC.get
            (BaseMongoFields
                .PRIVILEGES))
        .subscribe(deferred::setResult, deferred::setErrorResult);

    return deferred;
  }

  @RequestMapping(path = ApiPath.END_POINT + ApiPath.PARAM, method = RequestMethod.DELETE)
  public DeferredResult<GatewayBaseResponse<Object>>
  receiveAndForwardDelete(
      @PathVariable("endPoint") String endPoint,
      @PathVariable("param") String param,
      @RequestParam Map<String, String> requestParams) {

    DeferredResult<GatewayBaseResponse<Object>> deferred = new DeferredResult<>();

    MandatoryRequest mandatoryRequest = new MandatoryRequestBuilder()
        .withStoreId((String)MDC.get(BaseMongoFields.STORE_ID))
        .withChannelId((String)MDC.get(BaseMongoFields.CHANNEL_ID))
        .withServiceId((String)MDC.get(BaseMongoFields.SERVICE_ID))
        .withRequestId((String)MDC.get(BaseMongoFields.REQUEST_ID))
        .withUsername((String)MDC.get(BaseMongoFields.USERNAME))
        .build();

    SessionData sessionData = new SessionData();
    sessionData.setUsername((String)MDC.get(USERNAME));

    LOGGER.info("receiveAndForwardPut MandatoryRequest = {}, GatewayEndPoint = {}",
        mandatoryRequest, endPoint);

    GatewayEndPoint gatewayEndPoint = this.endPointService.findEndpointBySlug(mandatoryRequest,
        endPoint, DELETE_ACTION);

    this.gatewayService.forwardRequest(mandatoryRequest,gatewayEndPoint.getUrl(), param, null,
        RequestMethods.DELETE, null, gatewayEndPoint.getPrivilegeId(), (String)MDC.get
            (BaseMongoFields.PRIVILEGES))
        .subscribe(deferred::setResult, deferred::setErrorResult);
    return deferred;
  }
}

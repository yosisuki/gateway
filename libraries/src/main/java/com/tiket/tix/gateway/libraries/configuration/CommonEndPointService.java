package com.tiket.tix.gateway.libraries.configuration;

import com.tiket.tix.gateway.entity.outbound.GatewayBaseResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface CommonEndPointService {

  @GET
  Call<Object> forwardRequestGetAll(
      @Url String url,
      @HeaderMap Map<String, String> headerMap,
      @QueryMap Map<String, String> requestParams);

  @GET
  Call<Object> forwardRequestGet(
      @Url String url, @HeaderMap
      Map<String, String> headerMap);

  @POST
  Call<Object> forwardRequestPost(@Url String url, @HeaderMap
      Map<String, String> headerMap, @Body Object object);

  @PUT
  Call<Object> forwardRequestPut(@Url String url, @HeaderMap
      Map<String, String> headerMap, @Body Object object);

  @PUT
  Call<Object> forwardRequestPutWithoutBody(@Url String url, @HeaderMap
      Map<String, String> headerMap);

  @DELETE
  Call<Object> forwardRequestDelete(@Url String url, @HeaderMap
      Map<String, String> headerMap);
}

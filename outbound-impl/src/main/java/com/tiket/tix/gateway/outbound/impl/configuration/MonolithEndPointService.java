package com.tiket.tix.gateway.outbound.impl.configuration;

import com.tiket.tix.gateway.entity.MonolithSession;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface MonolithEndPointService {
  @GET("/get_session")
  Call<MonolithSession> findMonolithSession(@HeaderMap Map<String, String> header);
}

package com.tiket.tix.gateway.service.api;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import java.util.List;

public interface GatewayEndPointService {
  GatewayEndPoint findEndpointBySlug(MandatoryRequest mandatoryRequest, String slug, String action);
  List<GatewayEndPoint> findEndpointByGroupName(MandatoryRequest mandatoryRequest, String groupName);
}

package com.tiket.tix.gateway.service.impl;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.dao.api.GatewayEndPointRepository;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.service.api.GatewayEndPointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatewayEndPointServiceImpl implements GatewayEndPointService {

  @Autowired
  private GatewayEndPointRepository gatewayEndPointRepository;

  @Override
  public GatewayEndPoint findEndpointBySlug(MandatoryRequest mandatoryRequest, String slug,
      String action) {
    GatewayEndPoint endPoint = this.gatewayEndPointRepository.findGatewayEndPointByStoreIdAndSlugAndActionAndIsDeleted
        (mandatoryRequest
        .getStoreId(),slug, action, 0);

    if(endPoint == null){
      throw new BusinessLogicException(ResponseCode.PAGE_NOT_FOUND.getCode(), ResponseCode
          .PAGE_NOT_FOUND.getMessage());
    }

    return endPoint;
  }

  @Override
  public List<GatewayEndPoint> findEndpointByGroupName(MandatoryRequest mandatoryRequest, String
      groupName) {

    return this.gatewayEndPointRepository
        .findByStoreIdAndGroupNameAndIsDeleted
            (mandatoryRequest.getStoreId(),groupName, 0);
  }
}

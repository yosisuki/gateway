package com.tiket.tix.gateway.service.impl;

import com.tiket.tix.common.libraries.BeanMapper;
import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.dao.api.PrivilegeRepository;
import com.tiket.tix.gateway.entity.constant.enums.ResponseCode;
import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import com.tiket.tix.gateway.entity.dao.Privilege;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponse;
import com.tiket.tix.gateway.libraries.exception.BusinessLogicException;
import com.tiket.tix.gateway.service.api.GatewayEndPointService;
import com.tiket.tix.gateway.service.api.PrivilegeService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

  @Autowired
  private PrivilegeRepository privilegeRepository;

  @Autowired
  private GatewayEndPointService gatewayEndPointService;

  private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

  @Override
  public Single<Boolean> checkAuthorized(String privilege, String privilegeToCheck) {

    LOGGER.info("createImages request = privilege {}, "
        + "privilegeToCheck {}", privilege,  privilegeToCheck);

    return Single.<Boolean>create(singleEmitter -> {
      if(!isExistPrivilege(privilegeToCheck) || !privilegeToCheck.contains(privilege)){
        throw new BusinessLogicException(ResponseCode.NOT_AUTHORIZED.getCode(), ResponseCode
            .NOT_AUTHORIZED.getMessage());
      }
      singleEmitter.onSuccess(true);
    }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<List<PrivilegeResponse>> getAuthorizedPrivileges(MandatoryRequest mandatoryRequest,
      String privilegeToCheck) {

    LOGGER.info("getAuthorizedPrivileges request = mandatoryRequest {}, "
        + "privilegeToCheck {}", mandatoryRequest,  privilegeToCheck);

    return Single.<List<PrivilegeResponse>>create(singleEmitter -> {
      List<Privilege> privileges = this.privilegeRepository.findByStoreId(mandatoryRequest
          .getStoreId());
      String[] privilegeStrings = privilegeToCheck.split(",");
      List<Privilege> allowedPrivileges = new ArrayList<>();
      for(String privString : privilegeStrings){
        for(Privilege priv : privileges){
          if(privString.equals(priv.getPrivilegeId())){
            allowedPrivileges.add(priv);
            break;
          }
        }
      }

      LOGGER.info("getAuthorizedPrivileges response : result {} ", BeanMapper.mapAsList(allowedPrivileges, PrivilegeResponse.class));
      singleEmitter.onSuccess(BeanMapper.mapAsList(allowedPrivileges, PrivilegeResponse.class));
    }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<List<PrivilegeResponse>> getAuthorizedPrivilegesInCurrentSlug(
      MandatoryRequest mandatoryRequest,
      String privilegeToCheck, String groupName) {

    LOGGER.info("getAuthorizedPrivilegesInCurrentSlug request = mandatoryRequest {}, "
        + "privilegeToCheck {}", mandatoryRequest,  privilegeToCheck);

    return getAuthorizedPrivileges(mandatoryRequest, privilegeToCheck)
        .map(privilegeResponses -> {
          List<PrivilegeResponse> filteredPrivilegeResponses = new ArrayList<>();
          List<GatewayEndPoint> groupNameGatewayEndPoints = this.gatewayEndPointService
              .findEndpointByGroupName
              (mandatoryRequest, groupName);

          Map<String, PrivilegeResponse> privilegeResponseMap = new HashMap<>();
          for(PrivilegeResponse privilegeResponse : privilegeResponses){
            privilegeResponseMap.put(privilegeResponse.getPrivilegeId(), privilegeResponse);
          }

          for(GatewayEndPoint gatewayEndPoint : groupNameGatewayEndPoints){
            filteredPrivilegeResponses.add(privilegeResponseMap.get(gatewayEndPoint
                .getPrivilegeId()));
          }

          return filteredPrivilegeResponses;
        }).subscribeOn(Schedulers.io());
  }

  private Boolean isExistPrivilege(String privilegeToCheck){
    return privilegeToCheck != null;
  }
}

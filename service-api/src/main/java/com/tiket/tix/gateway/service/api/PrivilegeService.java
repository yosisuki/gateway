package com.tiket.tix.gateway.service.api;

import com.tiket.tix.common.rest.web.model.request.MandatoryRequest;
import com.tiket.tix.gateway.entity.outbound.PrivilegeResponse;
import io.reactivex.Single;
import java.util.List;

public interface PrivilegeService {
  Single<Boolean> checkAuthorized(String privilege, String privilegeToCheck);

  Single<List<PrivilegeResponse>> getAuthorizedPrivileges(MandatoryRequest mandatoryRequest, String
      privilegeToCheck);

  Single<List<PrivilegeResponse>> getAuthorizedPrivilegesInCurrentSlug(MandatoryRequest
      mandatoryRequest,
      String
      privilegeToCheck, String groupName);
}

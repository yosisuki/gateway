package com.tiket.tix.gateway.entity.outbound;

import com.tiket.tix.common.rest.web.model.response.BaseResponse;
import java.util.List;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class GatewayBaseResponse<T> extends BaseResponse<T> {
  private SessionData sessionData;

  private List<PrivilegeResponse> privileges;

  private List<PrivilegeResponse> currentSlugPrivileges;

  private List<Integer> roles;

  public SessionData getSessionData() {
    return sessionData;
  }

  public void setSessionData(SessionData sessionData) {
    this.sessionData = sessionData;
  }

  public List<PrivilegeResponse> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(
      List<PrivilegeResponse> privileges) {
    this.privileges = privileges;
  }

  public List<PrivilegeResponse> getCurrentSlugPrivileges() {
    return currentSlugPrivileges;
  }

  public void setCurrentSlugPrivileges(
      List<PrivilegeResponse> currentSlugPrivileges) {
    this.currentSlugPrivileges = currentSlugPrivileges;
  }

  public List<Integer> getRoles() {
    return roles;
  }

  public void setRoles(List<Integer> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "GatewayBaseResponse{" +
        "sessionData=" + sessionData +
        ", privileges=" + privileges +
        ", currentSlugPrivileges=" + currentSlugPrivileges +
        ", roles=" + roles +
        '}';
  }
}

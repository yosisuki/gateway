package com.tiket.tix.gateway.entity.outbound;

import com.tiket.tix.common.entity.CommonModel;
import java.io.Serializable;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder
public class SessionData extends CommonModel implements Serializable {
  private String username;
  private String businessId;
  private String lang;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  @Override
  public String toString() {
    return "SessionData{" +
        "username='" + username + '\'' +
        ", businessId='" + businessId + '\'' +
        ", lang='" + lang + '\'' +
        '}';
  }
}

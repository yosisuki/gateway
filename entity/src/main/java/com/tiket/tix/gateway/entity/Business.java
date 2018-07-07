package com.tiket.tix.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Business {

  @JsonProperty("account_username")
  private String accountUsername;
  @JsonProperty("business_id")
  private String businessId;
  @JsonProperty("business_country")
  private String businessCountry;
  @JsonProperty("business_name")
  private String businessName;
  @JsonProperty("business_active")
  private String businessActive;
  @JsonProperty("business_lat")
  private String businessLat;
  @JsonProperty("business_long")
  private String businessLong;
  @JsonProperty("business_live")
  private String businessLive;
  @JsonProperty("account_id")
  private String accountId;
  @JsonProperty("role_id")
  private String roleId;
  @JsonProperty("business_uri")
  private String businessUri;
  public void setAccountUsername(String accountUsername) {
    this.accountUsername = accountUsername;
  }
  public String getAccountUsername() {
    return accountUsername;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }
  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessCountry(String businessCountry) {
    this.businessCountry = businessCountry;
  }
  public String getBusinessCountry() {
    return businessCountry;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }
  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessActive(String businessActive) {
    this.businessActive = businessActive;
  }
  public String getBusinessActive() {
    return businessActive;
  }

  public void setBusinessLat(String businessLat) {
    this.businessLat = businessLat;
  }
  public String getBusinessLat() {
    return businessLat;
  }

  public void setBusinessLong(String businessLong) {
    this.businessLong = businessLong;
  }
  public String getBusinessLong() {
    return businessLong;
  }

  public void setBusinessLive(String businessLive) {
    this.businessLive = businessLive;
  }
  public String getBusinessLive() {
    return businessLive;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
  public String getAccountId() {
    return accountId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
  public String getRoleId() {
    return roleId;
  }

  public void setBusinessUri(String businessUri) {
    this.businessUri = businessUri;
  }
  public String getBusinessUri() {
    return businessUri;
  }

  @Override
  public String toString() {
    return "Business{" +
        "accountUsername='" + accountUsername + '\'' +
        ", businessId='" + businessId + '\'' +
        ", businessCountry='" + businessCountry + '\'' +
        ", businessName='" + businessName + '\'' +
        ", businessActive='" + businessActive + '\'' +
        ", businessLat='" + businessLat + '\'' +
        ", businessLong='" + businessLong + '\'' +
        ", businessLive='" + businessLive + '\'' +
        ", accountId='" + accountId + '\'' +
        ", roleId='" + roleId + '\'' +
        ", businessUri='" + businessUri + '\'' +
        '}';
  }
}
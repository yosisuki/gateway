package com.tiket.tix.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tiket.tix.common.entity.CommonModel;
import java.io.Serializable;

public class UserData extends CommonModel implements Serializable{

  @JsonProperty("no_gc")
  private int noGc;
  private String referrer;
  private String username;
  @JsonProperty("user_mobile")
  private String userMobile;
  @JsonProperty("logged_in")
  private boolean loggedIn;
  private String id;
  @JsonProperty("profile_id")
  private String profileId;
  private String status;
  @JsonProperty("account_created")
  private String accountCreated;
  private String firstname;
  private String lastname;
  private String uri;
  @JsonProperty("login_source")
  private String loginSource;
  private String group;
  private String role;
  private String priv;
  @JsonProperty("isMM")
  private boolean ismm;
  private String photo;
  @JsonProperty("business_active")
  private String businessActive;
  @JsonProperty("business_id")
  private String businessId;
  private int isadmin;
  private Business business;

  @JsonProperty("login_as")
  @JsonIgnoreProperties(ignoreUnknown = true)
  private String loginAs;

  public int getNoGc() {
    return noGc;
  }

  public void setNoGc(int noGc) {
    this.noGc = noGc;
  }

  public String getReferrer() {
    return referrer;
  }

  public void setReferrer(String referrer) {
    this.referrer = referrer;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUserMobile() {
    return userMobile;
  }

  public void setUserMobile(String userMobile) {
    this.userMobile = userMobile;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProfileId() {
    return profileId;
  }

  public void setProfileId(String profileId) {
    this.profileId = profileId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAccountCreated() {
    return accountCreated;
  }

  public void setAccountCreated(String accountCreated) {
    this.accountCreated = accountCreated;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getLoginSource() {
    return loginSource;
  }

  public void setLoginSource(String loginSource) {
    this.loginSource = loginSource;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPriv() {
    return priv;
  }

  public void setPriv(String priv) {
    this.priv = priv;
  }

  public boolean isIsmm() {
    return ismm;
  }

  public void setIsmm(boolean ismm) {
    this.ismm = ismm;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getBusinessActive() {
    return businessActive;
  }

  public void setBusinessActive(String businessActive) {
    this.businessActive = businessActive;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public int getIsadmin() {
    return isadmin;
  }

  public void setIsadmin(int isadmin) {
    this.isadmin = isadmin;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }

  public String getLoginAs() {
    return loginAs;
  }

  public void setLoginAs(String loginAs) {
    this.loginAs = loginAs;
  }

  @Override
  public String toString() {
    return "UserData{" +
        "noGc=" + noGc +
        ", referrer='" + referrer + '\'' +
        ", username='" + username + '\'' +
        ", userMobile='" + userMobile + '\'' +
        ", loggedIn=" + loggedIn +
        ", id='" + id + '\'' +
        ", profileId='" + profileId + '\'' +
        ", status='" + status + '\'' +
        ", accountCreated='" + accountCreated + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", uri='" + uri + '\'' +
        ", loginSource='" + loginSource + '\'' +
        ", group='" + group + '\'' +
        ", role='" + role + '\'' +
        ", priv='" + priv + '\'' +
        ", ismm=" + ismm +
        ", photo='" + photo + '\'' +
        ", businessActive='" + businessActive + '\'' +
        ", businessId='" + businessId + '\'' +
        ", isadmin=" + isadmin +
        ", business=" + business +
        ", loginAs='" + loginAs + '\'' +
        '}';
  }
}
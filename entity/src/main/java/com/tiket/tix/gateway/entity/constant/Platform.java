package com.tiket.tix.gateway.entity.constant;

public enum Platform {
  WEB("WEB", "WEB"),
  MOBILE("MOBILE", "MOBILE"),
  ANDROID("ANDROID", "ANDROID"),
  IOS("IOS", "IOS"),
  WEB_LOGIN("WEB_LOGIN", "WEB_LOGIN"),
  MOBILE_LOGIN("MOBILE_LOGIN", "MOBILE_LOGIN"),
  ANDROID_LOGIN("ANDROID_LOGIN", "ANDROID_LOGIN"),
  IOS_LOGIN("IOS_LOGIN", "IOS_LOGIN");

  private String code;
  private String name;

  Platform(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}

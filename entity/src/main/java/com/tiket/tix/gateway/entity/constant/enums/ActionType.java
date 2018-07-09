package com.tiket.tix.gateway.entity.constant.enums;

public enum ActionType {
  GET_ALL_ACTION("GET_ALL_ACTION","getAll"),
  GET_ACTION("GET_ACTION","get"),
  POST_ACTION("POST_ACTION","post"),
  PUT_ACTION("PUT_ACTION","put"),
  DELETE_ACTION("DELETE_ACTION","delete");

  private String code;
  private String value;

  ActionType(String code, String value) {
    this.code = code;
    this.value = value;
  }

  public String getCode() {
    return code;
  }

  public String getValue() {
    return value;
  }

}

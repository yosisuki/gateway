package com.tiket.tix.gateway.entity.constant;

public interface ApiPath {

  String BASE_PATH = "/tix-gateway";
  String SYSTEM_PARAMETER = BASE_PATH + "/systemParameters";
  String ID = "/{id}";
  String END_POINT = BASE_PATH + "/{endPoint}";
  String ACTION = "/{action}";
  String ACTION_UPDATE = "/{actionStatus}";
  String PARAM = "/{param}";
}

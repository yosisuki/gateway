package com.tiket.tix.gateway.entity.constant;

import java.util.Arrays;
import java.util.List;

public class HotelDevaultValues {

  public static final String STR_PAGE = "0";
  public static final String STR_PAGE_SIZE = "20";
  public static final Integer PAGE = 0;
  public static final Integer PAGE_SIZE = 20;
  public static final String SORT_METHOD = "desc";
  public static final Integer DEFAULT_MIN_STAY = 1;
  public static final Integer DEFAULT_HOTEL_TONIGHT_MIN_STAY = 0;
  public static final List<Integer> ALL_DAY_NUMBERS = Arrays.asList(1,2,3,4,5,6,7);
  public static final List<String> ALL_PLATFORMS = Arrays.asList(
      Arrays.stream(Platform.class.getEnumConstants()).map(Enum::name).toArray(String[]::new));
  public static final List<String> APPS = Arrays.asList("ANDROID", "IOS");
  public static final List<String> APPS_DEAL = Arrays.asList("WEB_LOGIN","MOBILE_LOGIN","ANDROID", "IOS","ANDROID_LOGIN", "IOS_LOGIN");

}

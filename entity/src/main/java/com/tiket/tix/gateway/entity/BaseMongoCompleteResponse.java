package com.tiket.tix.gateway.entity;

import com.tiket.tix.common.entity.CommonModel;
import java.io.Serializable;
import java.util.Date;

public class BaseMongoCompleteResponse extends CommonModel implements Serializable {

  private String id;
  private Long version;
  private Date createdDate;
  private String createdBy;
  private Date updatedDate;
  private String updatedBy;
  private String storeId;
  private Integer isDeleted = 0;
  private String channelId;
  private String username;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public Integer getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Integer isDeleted) {
    this.isDeleted = isDeleted;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "BaseMongoCompleteResponse{" +
        "id='" + id + '\'' +
        ", version=" + version +
        ", createdDate=" + createdDate +
        ", createdBy='" + createdBy + '\'' +
        ", updatedDate=" + updatedDate +
        ", updatedBy='" + updatedBy + '\'' +
        ", storeId='" + storeId + '\'' +
        ", isDeleted=" + isDeleted +
        ", channelId='" + channelId + '\'' +
        ", username='" + username + '\'' +
        '}' + super.toString();
  }
}

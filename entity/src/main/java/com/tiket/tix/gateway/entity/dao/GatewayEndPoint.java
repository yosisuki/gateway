package com.tiket.tix.gateway.entity.dao;

import com.tiket.tix.gateway.entity.constant.CollectionName;
import com.tiket.tix.gateway.entity.constant.fields.GatewayEndPointFields;
import com.tiket.tix.gateway.entity.constant.fields.PrivilegeFields;
import com.tiket.tix.gateway.entity.dao.common.BaseMongo;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@GeneratePojoBuilder
@Document(collection = CollectionName.GATEWAY_END_POINT)
public class GatewayEndPoint extends BaseMongo {

  @Field(value = GatewayEndPointFields.SLUG)
  private String slug;

  @Field(value = GatewayEndPointFields.URL)
  private String url;

  @Field(value = PrivilegeFields.ACTION)
  private String action;

  @Field(value = PrivilegeFields.PRIVILEGE_ID)
  private String privilegeId;

  @Field(value = PrivilegeFields.GROUP_NAME)
  private String groupName;

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getPrivilegeId() {
    return privilegeId;
  }

  public void setPrivilegeId(String privilegeId) {
    this.privilegeId = privilegeId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  @Override
  public String toString() {
    return "GatewayEndPoint{" +
        "slug='" + slug + '\'' +
        ", url='" + url + '\'' +
        ", action='" + action + '\'' +
        ", privilegeId='" + privilegeId + '\'' +
        ", groupName='" + groupName + '\'' +
        '}';
  }
}


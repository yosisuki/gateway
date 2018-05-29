package com.tiket.tix.gateway.dao.api;

import com.tiket.tix.gateway.entity.dao.GatewayEndPoint;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GatewayEndPointRepository extends MongoRepository<GatewayEndPoint, String> {
  GatewayEndPoint findGatewayEndPointByStoreIdAndSlugAndActionAndIsDeleted(String storeId, String
      slug, String action, Integer isDeleted);

  List<GatewayEndPoint> findByStoreIdAndGroupNameAndIsDeleted(String storeId,
      String groupName, Integer isDeleted);
}

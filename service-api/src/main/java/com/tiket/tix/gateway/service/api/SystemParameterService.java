package com.tiket.tix.gateway.service.api;

import com.tiket.tix.gateway.entity.dao.SystemParameter;
import org.springframework.data.domain.Page;

public interface SystemParameterService {

  Page<SystemParameter> findAllSystemParametersByStoreId(String storeId, Integer page,
      Integer size);

  SystemParameter findSystemParameterById(String id);

  SystemParameter findSystemParameterByStoreIdAndVariable(String storeId, String variable);

  SystemParameter createSystemParameter(SystemParameter systemParameter);

  SystemParameter updateSystemParameter(SystemParameter systemParameter, String id);

  Boolean deleteSystemParameterById(String id);
}

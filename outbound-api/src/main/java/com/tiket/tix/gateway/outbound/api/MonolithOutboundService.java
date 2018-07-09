package com.tiket.tix.gateway.outbound.api;

import com.tiket.tix.gateway.entity.MonolithSession;

@FunctionalInterface
public interface MonolithOutboundService {

  MonolithSession findMonolithSession(String sessionId);
}
package dev.example.bettingstake.service;

import dev.example.bettingstake.model.Session;

public interface SessionService {
    String getSessionKeyByCustomerId(Integer customerId);

    Session getSessionBySessionKey(String sessionKey);
}

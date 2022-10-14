package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {


    private static Map<Integer, Session> customerSession = new ConcurrentHashMap<>();

    @Override
    public String getSessionKeyByCustomerId(Integer customerId) {
        Session session = customerSession.get(customerId);

        if (null == session) {
            session = generateNewSession(customerId);
            customerSession.put(customerId, session);
        } else {
            if (session.getExpireTime() < new Date().getTime()) {
                session.setExpireTime();
            }
        }

        return session.getSessionKey();
    }

    @Override
    public Session getSessionBySessionKey(String sessionKey) {

        Optional<Session> session = customerSession.values().stream().findFirst();

        if (session.isPresent()) {
            return session.get();
        }
        return null;
    }

    @Override
    public void clearExpiredSession() {
        //remove the sessions that expired for ten minutes
        List<Session> expiredSession = customerSession.values().stream().filter(x -> (x.getExpireTime() + 10 * 60 * 1000) < new Date().getTime()).collect(Collectors.toList());
        expiredSession.forEach(session -> customerSession.remove(session.getCustomerId()));
    }

    private Session generateNewSession(Integer customerId) {
        String sessionKey = getGUID() + customerId;
        Session session = new Session();
        session.setSessionKey(sessionKey);
        session.setCustomerId(customerId);
        session.setExpireTime();
        return session;
    }


    private String getGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

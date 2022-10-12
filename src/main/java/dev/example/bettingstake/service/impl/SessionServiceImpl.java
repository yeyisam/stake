package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {


    private  static Map<Integer, Session> customerSession=new ConcurrentHashMap<>();

    @Override
    public String getSessionKeyByCustomerId(Integer customerId) {
        Session session=customerSession.get(customerId);

        if(null==session){
            session=GenerateNewSession(customerId);
            customerSession.put(customerId,session);
        }
        else{
            Date expireTime= session.getExpireTime();
            Date now=new Date();
            if(expireTime.compareTo(now)<0){
                session.setExpireTime();
            }
        }

        return session.getSessionKey();
    }

    @Override
   public Session getSessionBySessionKey(String sessionKey){
        Session session=customerSession.values().stream().filter(l ->l.getSessionKey().equals(sessionKey)).findFirst().orElse(null);
         return  session;
    }

    private  Session GenerateNewSession(Integer customerId){
        String sessionKey= GetGUID()+customerId;
        Session session=new  Session();
        session.setSessionKey(sessionKey);
        session.setCustomerId(customerId);
        session.setExpireTime();
        return session;
    }


    private String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

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

    private  static ConcurrentSkipListSet<Session> customerSession=new ConcurrentSkipListSet<>();

    @Override
    public String getSessionKeyByCustomerId(Integer customerId) {
         List<Session> sessions=customerSession.stream().filter(l ->l.getCustomerId()==customerId).collect(Collectors.toList());

        if(sessions.isEmpty()){
            Session session=GenerateNewSession(customerId);
            customerSession.add(session);
            return session.getSessionKey();
        }
        else{
            Session session=sessions.get(0);
            Date expireTime= session.getExpireTime();
            Date now=new Date();
            if(expireTime.compareTo(now)<0){
                session=GenerateNewSession(customerId);
            }
            return  session.getSessionKey();
        }
    }

    @Override
   public Session getSessionBySessionKey(String sessionKey){
        Session session=customerSession.stream().filter(l ->l.getSessionKey().equals(sessionKey)).findFirst().orElse(null);
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

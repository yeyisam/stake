package dev.example.bettingstake.service.impl;

import dev.example.bettingstake.model.Session;
import dev.example.bettingstake.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionServiceImpl implements SessionService {

    private  static Map<Integer, Session> customerSession=new ConcurrentHashMap<Integer,Session>();

    @Override
    public String getSessionKeyByCustomerId(Integer customerId) {
        Session session=customerSession.get(customerId);

        if(null==session){
            session=GenerateNewSession(customerId);
            customerSession.put(customerId.intValue(),session);
        }
        else{
            Date expireTime= session.getExpireTime();
            Date now=new Date();
            if(expireTime.compareTo(now)>0){
                return  session.getSessionKey();
            }
            else{
                session.setExpireTime();
                session.setSessionKey(GetGUID()+customerId);
                customerSession.put(customerId.intValue(),session);
            }
        }

        return session.getSessionKey();
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

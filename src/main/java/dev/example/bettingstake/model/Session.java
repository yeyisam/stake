package dev.example.bettingstake.model;

import java.util.Calendar;
import java.util.Date;

public class Session {

    private String sessionKey;
    private  Integer customerId;
    private Long expireTime;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime() {
        Date currentDate = new Date();

        this.expireTime = currentDate.getTime()+ 10 * 60 *1000;
    }
 

}

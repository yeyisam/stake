package dev.example.bettingstake.model;

import java.util.Calendar;
import java.util.Date;

public class Session {

    private String sessionKey;
    private  Integer customerId;
    private Date expireTime;

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

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime() {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MINUTE, 10);
        Date expireTime = c.getTime();
        this.expireTime = expireTime;
    }
}
